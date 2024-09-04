package co.com.pruebatecnica.apirest.tasks.delete;

import co.com.pruebatecnica.apirest.interactions.DeleteRequest;
import co.com.pruebatecnica.apirest.utils.Endpoints;
import lombok.AllArgsConstructor;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

@AllArgsConstructor
public class ConsumeDelete implements Task {
    private final String baseUrl;
    private final String requestUri;
    private final String param;

    @Override
    public <T extends Actor> void performAs(T actor) {
        String url = Serenity.environmentVariables().getProperty(baseUrl);
        String endpoint = Endpoints.valueOf(requestUri).getEndpoint();
        String token = actor.recall("token").toString();
        actor.attemptsTo(
                DeleteRequest.with(url, endpoint, token, param)
        );
    }

    public static ConsumeDelete with(String baseUrl, String requestUri, String param) {
        return Tasks.instrumented(ConsumeDelete.class, baseUrl, requestUri, param);
    }
}
