#author: jdcasallas12@gmail.com
Feature: Autenticación de usuario

  Background:
    Given el "usuario" obtiene la url de la api

  Scenario: Generar token de autenticación usando el servicio CreateToken
    When envío una solicitud POST a "AUTH" con las credenciales:
      | username | password    |
      | admin    | password123 |
    Then debería recibir un código de respuesta 200 OK