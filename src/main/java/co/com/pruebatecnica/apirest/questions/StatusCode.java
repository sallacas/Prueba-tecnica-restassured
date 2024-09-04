package co.com.pruebatecnica.apirest.questions;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static co.com.pruebatecnica.apirest.utils.Constantes.RESPONSE_REQUEST;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

@AllArgsConstructor
public class StatusCode implements Question<Boolean> {
    private final int code;

    @Override
    @Subject("{0} valida el codigo de estado #code ")
    public Boolean answeredBy(Actor actor) {
        actor.should(seeThatResponse(RESPONSE_REQUEST,
                rs -> rs.statusCode(code)));
        return true;
    }

    public static StatusCode is(int code) {
        return new StatusCode(code);
    }
}
