package co.com.pruebatecnica.apirest.stepdefinitions;

import co.com.pruebatecnica.apirest.questions.StatusCode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.actors.OnStage;

import static co.com.pruebatecnica.apirest.utils.Constantes.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

@Slf4j
public class BaseStepDefinitions {
    @Given("el {string} obtiene la url de la api")
    public void elObtieneLaUrlDeLaApi(String actor) {
        log.info(String.format(
                FORMAT_THREE,
                actor,
                CALL_SERVICE,
                BASE_URL.replace(TYPE_ENVIRONMENT, ENV_QA)
        ));
    }

    @Then("debería recibir un código de respuesta {int} OK")
    public void validateStatusCode(int code) {
        OnStage.theActorInTheSpotlight().should(seeThat(StatusCode.is(code)));
    }
}
