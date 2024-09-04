package co.com.pruebatecnica.apirest.stepdefinitions;

import co.com.pruebatecnica.apirest.models.booking.CreateBookingDTO;
import co.com.pruebatecnica.apirest.models.booking.GetBookingParamsDTO;
import co.com.pruebatecnica.apirest.models.booking.UpdateBookingDTO;
import co.com.pruebatecnica.apirest.models.token.CreateTokenDTO;
import co.com.pruebatecnica.apirest.questions.ValidateField;
import co.com.pruebatecnica.apirest.tasks.get.ConsumeGetPathParams;
import co.com.pruebatecnica.apirest.tasks.get.ConsumeGetQueryParams;
import co.com.pruebatecnica.apirest.tasks.post.ConsumePost;
import co.com.pruebatecnica.apirest.tasks.put.ConsumePut;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.Assert;

import java.util.List;

import static co.com.pruebatecnica.apirest.utils.Constantes.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

@Slf4j
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

    @When("envio la solicitud las reservas en {string} con los siguientes query params:")
    public void getBookingIds(String endpoint, DataTable table) {
        List<GetBookingParamsDTO> data = table.asList(GetBookingParamsDTO.class);
        OnStage.theActor(ACTOR).attemptsTo(
                ConsumeGetQueryParams.with(BASE_URL.replace(TYPE_ENVIRONMENT, ENV_QA), endpoint, data.get(0), String.valueOf(ContentType.JSON))
        );
    }


    @And("consulto la reserva con el id obtenido en la respuesta")
    public void obtainReservationWithIdOnResponse() {
        String param = SerenityRest.lastResponse().jsonPath().getString("[0].bookingid");
        Assert.assertNotNull("El campo bookingid no existe en la respuesta", param);
        OnStage.theActorInTheSpotlight().attemptsTo(
                ConsumeGetPathParams.with(BASE_URL.replace(TYPE_ENVIRONMENT, ENV_QA), "GET_BOOKING", param)
        );
    }

    @And("obtenemos el token consumiendo el servicio POST a {string} con las credenciales:")
    public void obtainToken(String endpoint, DataTable table) {
        List<CreateTokenDTO> data = table.asList(CreateTokenDTO.class);
        OnStage.theActor(ACTOR).attemptsTo(
                ConsumePost.with(BASE_URL.replace(TYPE_ENVIRONMENT, ENV_QA), data.get(0), endpoint, String.valueOf(ContentType.JSON))
        );
        OnStage.theActorInTheSpotlight().remember("token", "token=".concat(SerenityRest.lastResponse().jsonPath().getString("token")));
    }

    @And("actualizo con la solicitud PUT a {string} con los siguientes datos de reserva:")
    public void updateBooking(String endpoint, DataTable table) {
        List<UpdateBookingDTO> data = table.asList(UpdateBookingDTO.class);
        String param = SerenityRest.lastResponse().jsonPath().getString("[0].bookingid");
        Assert.assertNotNull("El campo bookingid no existe en la respuesta", param);
        OnStage.theActor(ACTOR).attemptsTo(
                ConsumePut.with(BASE_URL.replace(TYPE_ENVIRONMENT, ENV_QA), data.get(0), endpoint, String.valueOf(ContentType.JSON), param)
        );
    }
}
