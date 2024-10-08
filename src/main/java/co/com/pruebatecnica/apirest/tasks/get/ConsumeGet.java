package co.com.pruebatecnica.apirest.tasks.get;

import co.com.pruebatecnica.apirest.interactions.get.GetRequest;
import co.com.pruebatecnica.apirest.utils.Endpoints;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

@AllArgsConstructor
@Slf4j
public class ConsumeGet implements Task {
    private final String baseUrl;
    private final String requestUri;
    private final String typeContent;

    @Override
    public <T extends Actor> void performAs(T actor) {
        String url = Serenity.environmentVariables().getProperty(baseUrl);
        String endpoint = Endpoints.valueOf(requestUri).getEndpoint();
        actor.attemptsTo(
                GetRequest.with(url, endpoint, typeContent)
        );
    }

    public static ConsumeGet with(String baseUrl, String requestUri, String typeContent) {
        return Tasks.instrumented(ConsumeGet.class, baseUrl, requestUri, typeContent);
    }
}
