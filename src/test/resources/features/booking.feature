# author: jdcasallas12@gmail.com
# description: Prueba de integración con la API REST de la aplicación de reservas de libros
@Regression
Feature: Crear reservas de libros

  Background:
    Given el "usuario" obtiene la url de la api
    And obtenemos el token consumiendo el servicio POST a "CREATE_TOKEN" con las credenciales:
      | username | password    |
      | admin    | password123 |

  @CreateBookings
  Scenario Outline: Crear reservas de libros y verificar la respuesta
    When envío una solicitud POST a "CREATE_BOOKING" con los siguientes datos de reserva:
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    Then debería recibir un código de respuesta 200 OK
    And el campo "booking.firstname" en la respuesta debería ser "<firstname>" para la reserva

    Examples:
      | firstname | lastname  | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Pedro     | Gutierrez | 100        | true        | 2024-03-01 | 2024-04-01 | Comics          |
      | Javier    | Jaramillo | 356        | true        | 2024-03-15 | 2024-04-15 | Terror          |

  @GetBookings
  Scenario Outline: Consultar los libros creados y validar los detalles
    When envio la solicitud las reservas en "GET_BOOKING_IDS" con los siguientes query params:
      | firstname   | lastname   |
      | <firstname> | <lastname> |
    And consulto la reserva con el id obtenido en la respuesta
    Then debería recibir un código de respuesta 200 OK
    And el campo "firstname" en la respuesta debería ser "<firstname>" para la reserva
    And el campo "lastname" en la respuesta debería ser "<lastname>" para la reserva

    Examples:
      | firstname | lastname  |
      | Pedro     | Gutierrez |
      | Javier    | Jaramillo |

  @UpdateBookings
  Scenario Outline: Actualizar los datos de una reserva y validar la respuesta
    When envio la solicitud las reservas en "GET_BOOKING_IDS" con los siguientes query params:
      | firstname          | lastname          |
      | <initialFirstName> | <initialLastName> |
    And actualizo con la solicitud PUT a "UPDATE_BOOKING" con los siguientes datos de reserva:
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    Then debería recibir un código de respuesta 200 OK
    And el campo "firstname" en la respuesta debería ser "<firstname>" para la reserva
    And el campo "lastname" en la respuesta debería ser "<lastname>" para la reserva

    Examples:
      | firstname | lastname  | totalprice | depositpaid | checkin    | checkout   | additionalneeds | initialFirstName | initialLastName |
      | Jose      | Gutierrez | 100        | true        | 2023-05-12 | 2023-06-28 | Comics          | Pedro            | Gutierrez       |
      | Javier    | Mora      | 356        | true        | 2023-06-20 | 2023-07-20 | Terror          | Javier           | Jaramillo       |

  @DeleteBookings
  Scenario Outline: Eliminar una reserva y validar la respuesta
    When envio la solicitud las reservas en "GET_BOOKING_IDS" con los siguientes query params:
      | firstname   | lastname   |
      | <firstname> | <lastname> |
    And elimino la reserva con la solicitud DELETE a "DELETE_BOOKING" con el id obtenido en la respuesta
    Then debería recibir un código de respuesta 201 OK
    And la respuesta del servicio es igual a "HTTP/1.1 201 Created"

    Examples:
      | firstname | lastname  |
      | Jose      | Gutierrez |
      | Javier    | Mora      |