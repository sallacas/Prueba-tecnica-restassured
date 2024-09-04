package co.com.pruebatecnica.apirest.models.booking;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingDatesDTO {
    private String checkin;
    private String checkout;
}
