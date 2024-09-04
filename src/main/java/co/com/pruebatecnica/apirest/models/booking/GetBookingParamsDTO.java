package co.com.pruebatecnica.apirest.models.booking;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetBookingParamsDTO {
    private String firstname;
    private String lastname;
}
