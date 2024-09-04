package co.com.pruebatecnica.apirest.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/booking.feature",
        glue = "co.com.pruebatecnica.apirest.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class TestBookingRunner {
}
