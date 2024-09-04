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
public class GetPathParamsRequest implements Interaction {
    private final String baseUrl;
    private final String endpoint;
    private final String param;

    @Override
    @Subject("{0} El usuario obtiene urlBase: #baseUrl - consume el servicio #endpoint con el parametro: #param")
    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(baseUrl));
        actor.attemptsTo(
                Get.resource(endpoint).with(
                        request -> request
                                .pathParam("id", param)
                                .relaxedHTTPSValidation()
                )
        );
        log.info(MESSAGE_GENERAL);
        SerenityRest.lastResponse().body().prettyPrint();
    }

    public static GetPathParamsRequest with(String baseUrl, String endpoint, String param) {
        return Tasks.instrumented(GetPathParamsRequest.class, baseUrl, endpoint, param);
    }
}
