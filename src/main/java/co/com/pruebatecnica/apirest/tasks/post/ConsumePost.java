package co.com.pruebatecnica.apirest.tasks.post;

import co.com.pruebatecnica.apirest.interactions.PostRequest;
import co.com.pruebatecnica.apirest.utils.Endpoints;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
@Slf4j
@AllArgsConstructor
public class ConsumePost implements Task {
    private final String baseUrl;
    private final Object bodyRequest;
    private final String requestUri;
    private final String typeContent;
    @Override
    public <T extends Actor> void performAs(T actor) {
        String url = Serenity.environmentVariables().getProperty(baseUrl);
        String endpoint = Endpoints.valueOf(requestUri).getEndpoint();
        actor.remember("REQUEST_BODY",bodyRequest);
        actor.attemptsTo(
                PostRequest.params(url, bodyRequest, endpoint, typeContent)
        );
    }
    public static ConsumePost with (String baseUrl, Object bodyRequest, String requestUri, String typeContent){
        return Tasks.instrumented(ConsumePost.class, baseUrl, bodyRequest, requestUri, typeContent);
    }
}
