package co.com.pruebatecnica.apirest.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Endpoints {
    CREATE_TOKEN("/auth"),
    CREATE_BOOKING("/booking"),
    GET_BOOKING_IDS("/booking"),
    GET_BOOKING("/booking/{id}"),
    UPDATE_BOOKING("/booking/{id}"),
    DELETE_BOOKING("/booking/{id}");
    private final String endpoint;

}
