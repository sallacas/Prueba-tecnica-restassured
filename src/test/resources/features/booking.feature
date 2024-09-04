Feature: Crear reservas de libros

  Background:
    Given el "usuario" obtiene la url de la api

  Scenario Outline: Crear dos reservas de libros y verificar la respuesta
    When envío una solicitud POST a "CREATE_BOOKING" con los siguientes datos de reserva:
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    Then debería recibir un código de respuesta 200 OK
    And el campo "booking.firstname" en la respuesta debería ser "<firstname>" para la reserva

    Examples:
      | firstname | lastname  | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Pedro     | Gutierrez | 100        | true        | 2024-03-01 | 2024-04-01 | Comics          |
      | Javier    | Jaramillo | 356        | true        | 2024-03-15 | 2024-04-15 | Terror          |