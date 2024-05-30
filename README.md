# Currency Exchange App

This project consists of two parts: a RESTful backend developed in Java using the Spring framework and Maven, and a frontend web interface developed in Angular. The backend provides endpoints for fetching current currency exchange rates from the National Bank of Poland (NBP) API and storing queries in a database. The frontend allows users to interact with these endpoints by entering currency codes and user names to retrieve exchange rates and view a list of past queries.

## Backend (Java, Spring, Maven)

### Endpoints

1. **POST /currencies/get-current-currency-value-command**: This endpoint allows users to fetch the current exchange rate of a specified currency from the NBP API. It accepts a JSON object containing the currency code and user name in the request body. Upon successful retrieval, it returns the exchange rate value and saves the query details (user name, currency code, timestamp, and exchange rate value) in the database.

   Example Request Body:
   ```json
   {
       "currency": "EUR",
       "name": "Jan Nowak"
   }
   ```
   Example Response:
    ```json
   {
        "value": 4.2954
   }
   ```

2. **GET /currencies/requests**:  This endpoint allows users to retrieve a list of all past queries stored in the database. It returns an array of JSON objects containing details about each query, including the currency code, user name, timestamp, and exchange rate value. 

    Example Response:
   ```json
    [
      {
        "currency": "EUR",
        "name": "Jan Nowak",
        "date": "2022-01-01T10:00:00.000Z",
        "value": 4.2954
      },
      {
        "currency": "USD",
        "name": "Jon Doe",
        "date": "2022-01-01T10:00:00.000Z",
        "value": 5.7154
      }
    ]
   ```
### Main Dependencies
* Spring Boot
* Spring Data JPA
* H2 Database (for simplicity; can be replaced with other databases like MySQL, PostgreSQL)
* JUnit
* Mockito

## Frontend (Angular)

### Features
* Input form for entering currency code and user name to fetch exchange rates.
* Display of past query history retrieved from the backend.

### Main Dependencies
* Angular
* Angular Material (for UI components)
* Tailwind
