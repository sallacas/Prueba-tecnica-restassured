# Prueba Técnica de Automatización de APIs

Este proyecto es una prueba técnica desarrollada en Java utilizando el patrón Screenplay con RestAssured para pruebas de
API.

## Requisitos Previos

Antes de comenzar, asegúrate de cumplir con los siguientes requisitos:

- **Java**: Versión 17 o superior
- **Gradle**: Versión 8 o superior

## Estructura del Proyecto

El proyecto está organizado con los siguientes componentes principales:

- **Patrón Screenplay**: Las pruebas están escritas utilizando el patrón Screenplay para hacerlas más legibles,
  reutilizables y mantenibles.
- **RestAssured**: Utilizado para realizar solicitudes HTTP y validar respuestas.
- **JUnit**: Utilizado para escribir y ejecutar casos de prueba.

## Inforomacion de la API

La API REST que se utiliza para realizar las pruebas está alojada en la siguiente URL:  
`https://restful-booker.herokuapp.com`

La API REST está documentada en la siguiente URL:  
`https://restful-booker.herokuapp.com/apidoc`

## Ejecución de Pruebas

### Ejecutar Todas las Pruebas

Para ejecutar todas las pruebas, utiliza el siguiente comando:

```bash
./gradlew clean test
```

### Ejecutar Pruebas Específicas

Para ejecutar pruebas utilizando runners específicos, usa el siguiente comando:

```bash
./gradlew :test --tests "co.com.pruebatecnica.apirest.runners.{runner}"
```

Los runners disponibles son:

- `TestBookingRunner`
- `TestTokenRunner`

### Ejecutar Pruebas con Tags

Para ejecutar pruebas con tags específicos, utiliza el siguiente comando:

```bash
./gradlew clean test -Dtags=@Regression
```

Este comando ejecutará todas las pruebas que contengan la etiqueta `@Regression`.

Las etiquetas disponibles son:

- `@Regression`
- `@CreateBookings`
- `@GetBookings`
- `@UpdateBookings`
- `@DeleteBookings`
- `@Token`

### Generar Reporte de Pruebas

Para generar un reporte de las pruebas, utiliza el siguiente comando:

```bash
./gradlew reports
```

Este comando generará un reporte en formato HTML en la carpeta `build/reports/single-page-html`.