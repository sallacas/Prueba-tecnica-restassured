package co.com.pruebatecnica.apirest.interactions;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static co.com.pruebatecnica.apirest.utils.Constantes.MESSAGE_GENERAL;

@Slf4j
@AllArgsConstructor
public class DeleteRequest implements Interaction {
    private String baseUrl;
    private String endpoint;
    private String token;
    private String param;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(baseUrl));
        actor.attemptsTo(
                Delete.from(endpoint).with(
                        request -> request
                                .header("Content-Type", "application/json")
                                .header("Cookie", token)
                                .pathParam("id", param)
                                .relaxedHTTPSValidation()
                )
        );
        log.info(MESSAGE_GENERAL);
        SerenityRest.lastResponse().body().prettyPrint();
    }

    public static DeleteRequest with(String baseUrl, String endpoint, String token, String param) {
        return Tasks.instrumented(DeleteRequest.class, baseUrl, endpoint, token, param);
    }
}
