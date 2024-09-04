package co.com.pruebatecnica.apirest.stepdefinitions;

import co.com.pruebatecnica.apirest.models.token.CreateTokenDTO;
import co.com.pruebatecnica.apirest.questions.StatusCode;
import co.com.pruebatecnica.apirest.tasks.post.ConsumePost;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;

import static co.com.pruebatecnica.apirest.utils.Constantes.*;

public class TokenStepDefinitions {

    @When("envío una solicitud POST a {string} con las credenciales:")
    public void createPostToken(String endpoint, DataTable table) {
        List<CreateTokenDTO> data = table.asList(CreateTokenDTO.class);
        OnStage.theActor(ACTOR).attemptsTo(
                ConsumePost.with(BASE_URL.replace(TYPE_ENVIRONMENT, ENV_QA), data.get(0), endpoint, String.valueOf(ContentType.JSON))
        );
    }

}
