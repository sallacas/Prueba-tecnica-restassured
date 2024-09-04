package co.com.pruebatecnica.apirest.interactions.get;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.Map;

import static co.com.pruebatecnica.apirest.utils.Constantes.MESSAGE_GENERAL;

@Slf4j
@AllArgsConstructor
public class GetQueryParamsRequest implements Interaction {
    private final String baseUrl;
    private final String request;
    private final Object data;
    private final String typeContent;

    @Override
    @Subject("{0} El usuario obtiene urlBase: #baseUrl - consume el servicio #request con los siguientes parametros: #data")
    public <T extends Actor> void performAs(T actor) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = mapper.convertValue(data, new TypeReference<Map<String, String>>() {
        });
        actor.whoCan(CallAnApi.at(baseUrl));
        actor.attemptsTo(
                Get.resource(request).with(
                        request -> request
                                .queryParams(params)
                                .accept(typeContent)
                                .relaxedHTTPSValidation()
                )
        );
        log.info(MESSAGE_GENERAL);
        SerenityRest.lastResponse().body().prettyPrint();
    }

    public static GetQueryParamsRequest with(String baseUrl, String request, Object data, String typeContent) {
        return new GetQueryParamsRequest(baseUrl, request, data, typeContent);
    }
}
