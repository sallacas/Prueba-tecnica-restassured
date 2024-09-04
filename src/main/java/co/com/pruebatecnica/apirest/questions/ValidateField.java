package co.com.pruebatecnica.apirest.questions;

import co.com.pruebatecnica.apirest.utils.Constantes;
import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

@AllArgsConstructor
public class ValidateField implements Question<Boolean> {

    private final String field;
    private final String value;

    @Override
    @Subject("{0} Valida que el campo #field sea igual al valor #value")
    public Boolean answeredBy(Actor actor) {
        actor.should(seeThatResponse(Constantes.STATUS,
                rs -> rs.body(field, equalTo(value))));
        return true;
    }

    public static ValidateField isEqualTo(String field, String value) {
        return new ValidateField(field, value);
    }

}
