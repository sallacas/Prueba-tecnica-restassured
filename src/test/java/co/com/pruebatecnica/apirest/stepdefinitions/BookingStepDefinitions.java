package co.com.pruebatecnica.apirest.stepdefinitions;

import co.com.pruebatecnica.apirest.models.booking.CreateBookingDTO;
import co.com.pruebatecnica.apirest.questions.ValidateField;
import co.com.pruebatecnica.apirest.tasks.post.ConsumePost;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;

import static co.com.pruebatecnica.apirest.utils.Constantes.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;


public class BookingStepDefinitions {
    @When("envío una solicitud POST a {string} con los siguientes datos de reserva:")
    public void createBooking(String endpoint, DataTable table) {
        List<CreateBookingDTO> data = table.asList(CreateBookingDTO.class);

        OnStage.theActor(ACTOR).attemptsTo(
                ConsumePost.with(BASE_URL.replace(TYPE_ENVIRONMENT, ENV_QA), data.get(0), endpoint, String.valueOf(ContentType.JSON))
        );
    }

    @And("el campo {string} en la respuesta debería ser {string} para la reserva")
    public void validateFieldFirstName(String field, String value) {
        OnStage.theActorInTheSpotlight().should(seeThat(ValidateField.isEqualTo(field, value)));
    }
}
