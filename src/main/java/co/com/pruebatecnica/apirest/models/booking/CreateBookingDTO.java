package co.com.pruebatecnica.apirest.models.booking;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBookingDTO {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesDTO bookingdates;
    private String additionalneeds;

}
