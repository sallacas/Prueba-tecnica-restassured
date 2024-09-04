package co.com.pruebatecnica.apirest.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Endpoints {
    CREATE_TOKEN("/auth"),
    CREATE_BOOKING("/booking"),
    GET_BOOKING(""),
    UPDATE_BOOKING("");
    private final String endpoint;

}
