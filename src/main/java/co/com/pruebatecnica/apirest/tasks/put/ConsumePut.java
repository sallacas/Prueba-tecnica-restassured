package co.com.pruebatecnica.apirest.tasks.put;

import co.com.pruebatecnica.apirest.interactions.PutRequest;
import co.com.pruebatecnica.apirest.utils.Endpoints;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

@Slf4j
@AllArgsConstructor
public class ConsumePut implements Task {
    private final String baseUrl;
    private final Object bodyRequest;
    private final String requestUri;
    private final String typeContent;
    private final String param;

    @Override
    public <T extends Actor> void performAs(T actor) {
        String url = Serenity.environmentVariables().getProperty(baseUrl);
        String endpoint = Endpoints.valueOf(requestUri).getEndpoint();
        String token = actor.recall("token").toString();
        log.info(typeContent);
        actor.attemptsTo(
                PutRequest.with(url, bodyRequest, endpoint, typeContent, token, param)
        );
    }

    public static ConsumePut with(String baseUrl, Object bodyRequest, String requestUri, String typeContent, String param) {
        return Tasks.instrumented(ConsumePut.class, baseUrl, bodyRequest, requestUri, typeContent, param);
    }
}
