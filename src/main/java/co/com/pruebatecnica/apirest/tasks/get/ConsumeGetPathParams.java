package co.com.pruebatecnica.apirest.tasks.get;

import co.com.pruebatecnica.apirest.interactions.get.GetPathParamsRequest;
import co.com.pruebatecnica.apirest.utils.Endpoints;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static co.com.pruebatecnica.apirest.utils.Constantes.OBTAINED_INFO;

@Slf4j
@AllArgsConstructor
public class ConsumeGetPathParams implements Task {
    private final String baseUrl;
    private final String requestUri;
    private final String param;

    @Override
    public <T extends Actor> void performAs(T actor) {
        String url = Serenity.environmentVariables().getProperty(baseUrl);
        String endpoint = Endpoints.valueOf(requestUri).getEndpoint();
        log.info(OBTAINED_INFO);
        actor.attemptsTo(
                GetPathParamsRequest.with(url, endpoint, param)
        );
    }

    public static ConsumeGetPathParams with(String baseUrl, String requestUri, String param) {
        return Tasks.instrumented(ConsumeGetPathParams.class, baseUrl, requestUri, param);
    }
}
