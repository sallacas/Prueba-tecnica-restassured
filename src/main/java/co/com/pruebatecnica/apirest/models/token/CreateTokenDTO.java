package co.com.pruebatecnica.apirest.models.token;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTokenDTO {
    private String username;
    private String password;
}
