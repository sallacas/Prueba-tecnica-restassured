package co.com.pruebatecnica.apirest.stepdefinitions;

import co.com.pruebatecnica.apirest.models.booking.BookingDatesDTO;
import co.com.pruebatecnica.apirest.models.booking.CreateBookingDTO;
import co.com.pruebatecnica.apirest.models.token.CreateTokenDTO;
import groovy.util.logging.Slf4j;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.Map;


@Slf4j
public class Hooks {
    @Before
    public void setStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @DataTableType
    public CreateTokenDTO createTokenEntry(Map<String, String> entry) {
        return new CreateTokenDTO(
                entry.get("username"),
                entry.get("password")
        );
    }

    @DataTableType
    public CreateBookingDTO createBookingEntry(Map<String, String> entry) {
        return new CreateBookingDTO(
                entry.get("firstname"),
                entry.get("lastname"),
                Integer.parseInt(entry.get("totalprice")),
                Boolean.parseBoolean(entry.get("depositpaid")),
                new BookingDatesDTO(
                        entry.get("checkin"),
                        entry.get("checkin")
                ),
                entry.get("additionalneeds")
        );
    }
}
