package co.com.pruebatecnica.apirest.interactions;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static co.com.pruebatecnica.apirest.utils.Constantes.MESSAGE_GENERAL;

@Slf4j
@AllArgsConstructor
public class PutRequest implements Interaction {
    private String baseUrl;
    private Object bodyRequest;
    private String endpoint;
    private String typeContent;
    private String token;
    private String param;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(baseUrl));
        actor.attemptsTo(
                Put.to(endpoint).with(
                        request -> request
                                .header("Content-Type", typeContent)
                                .header("Accept", "application/json")
                                .header("Cookie", token)
                                .pathParam("id", param)
                                .body(bodyRequest)
                                .relaxedHTTPSValidation()
                )
        );
        log.info(MESSAGE_GENERAL);
        SerenityRest.lastResponse().body().prettyPrint();
    }

    public static PutRequest with(String baseUrl, Object bodyRequest, String endpoint, String typeContent, String token, String param) {
        return Tasks.instrumented(PutRequest.class, baseUrl, bodyRequest, endpoint, typeContent, token, param);
    }
}
