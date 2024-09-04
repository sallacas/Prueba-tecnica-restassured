package co.com.pruebatecnica.apirest.questions;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.pruebatecnica.apirest.utils.Constantes.RESPONSE_REQUEST;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

@AllArgsConstructor
public class StatusLine implements Question<Boolean> {
    private final String line;

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.should(seeThatResponse(RESPONSE_REQUEST, rs -> rs.statusLine(line)));
        return true;
    }

    public static StatusLine is(String line) {
        return new StatusLine(line);
    }
}
