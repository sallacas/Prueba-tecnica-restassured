package co.com.pruebatecnica.apirest.interactions;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.annotations.Subject;

@Slf4j
@AllArgsConstructor
public class GetRequest implements Interaction {
    private final String baseUrl;
    private final String request;
    private final String typeContent;
    @Override
    @Subject("{0} El usuario obtiene urlBase: #baseUrl - segun su ambiente: #typeContent, y consumen el servicio #request")
    public <T extends Actor> void performAs(T actor) {

    }
}
