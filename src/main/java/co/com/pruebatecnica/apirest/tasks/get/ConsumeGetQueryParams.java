package co.com.pruebatecnica.apirest.tasks.get;

import co.com.pruebatecnica.apirest.interactions.get.GetQueryParamsRequest;
import co.com.pruebatecnica.apirest.utils.Endpoints;
import lombok.AllArgsConstructor;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

@AllArgsConstructor
public class ConsumeGetQueryParams implements Task {
    private final String baseUrl;
    private final String requestUri;
    private final Object data;
    private final String typeContent;

    @Override
    public <T extends Actor> void performAs(T actor) {
        String url = Serenity.environmentVariables().getProperty(baseUrl);
        String endpoint = Endpoints.valueOf(requestUri).getEndpoint();
        actor.attemptsTo(
                GetQueryParamsRequest.with(url, endpoint, data, typeContent)
        );
    }

    public static ConsumeGetQueryParams with(String baseUrl, String requestUri, Object data, String typeContent) {
        return Tasks.instrumented(ConsumeGetQueryParams.class, baseUrl, requestUri, data, typeContent);
    }
}
