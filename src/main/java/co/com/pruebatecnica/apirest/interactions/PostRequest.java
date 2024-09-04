package co.com.pruebatecnica.apirest.interactions;

import co.com.pruebatecnica.apirest.models.token.CreateTokenDTO;
import co.com.pruebatecnica.apirest.utils.Constantes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;

@Slf4j
@AllArgsConstructor
public class PostRequest implements Interaction {
    private final String url;
    private final Object body;
    private final String endpoint;
    private final String typeContent;
    @Override
    @Subject("{0} #nameActor obtiene urlBase: #baseUrl - segun su ambiente: #typeContent, y consumen el servicio")
    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(url));
        actor.attemptsTo(
                Post.to(endpoint).with(request -> request
                        .header("Content-Type",typeContent)
                        .body(body)
                        .relaxedHTTPSValidation()
                )
        );
        log.info(Constantes.MESSAGE_GENERAL);
        SerenityRest.lastResponse().body().prettyPrint();
    }

    public static PostRequest params (String baseUrl, Object bodyRequest, String requestUri, String typeContent){
        return Tasks.instrumented(PostRequest.class, baseUrl, bodyRequest, requestUri, typeContent);
    }
}
