package co.com.pruebatecnica.apirest.interactions.get;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static co.com.pruebatecnica.apirest.utils.Constantes.MESSAGE_GENERAL;

@Slf4j
@AllArgsConstructor
public class GetRequest implements Interaction {
    private final String baseUrl;
    private final String request;
    private final String typeContent;

    @Override
    @Subject("{0} El usuario obtiene urlBase: #baseUrl - segun su ambiente: #typeContent, y consumen el servicio #request")
    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(baseUrl));
        actor.attemptsTo(
                Get.resource(request).with(
                        request -> request
                                .accept(typeContent)
                                .relaxedHTTPSValidation()
                )
        );
        log.info(MESSAGE_GENERAL);
        SerenityRest.lastResponse().body().prettyPrint();
    }

    public static GetRequest with(String baseUrl, String request, String typeContent) {
        return Tasks.instrumented(GetRequest.class, baseUrl, request, typeContent);
    }
}
