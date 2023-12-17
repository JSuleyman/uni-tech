# UniTech Backend APIs

This repository contains the backend APIs for UniTech, a fintech solution designed for banking products.

## API Endpoints

### 1. Register API

#### Request
http
POST /auth/register

{
  "pin": "",
  "password":""
}
#### Unit Test - `testRegisterWhenNewUserThenSuccess`, `testRegisterWhenExistingUserThenPinAlreadyExistsException`

### 2. Login API

#### Request
http
POST /auth/login

{
  "pin": "",
  "password":""
}
#### Unit Test - `testAuthenticateWhenUserIsSuccessfullyAuthenticatedThenReturnAuthenticationResponseWithToken`, `testAuthenticateWhenUserIsNotFoundThenThrowInvalidPinException`, `testAuthenticateWhenPasswordIsIncorrectThenThrowInvalidPasswordException`, `testAuthenticateWhenUserPinIsInvalidThenThrowInvalidPinException`, `testAuthenticateWhenUserPasswordIsInvalidThenThrowInvalidPasswordException`

### 3. Get accounts API

#### Request
http
GET /user-account

#### Unit Test - `testGetActiveUserAccountsWhenActiveUserAccountsExistThenReturnUserAccountViewerResponseDTOs`, `testGetActiveUserAccountsWhenNoActiveUserAccountsExistThenReturnEmptyList`

#### Request
http
POST /user-account

{
  "accountName":"",
  "accountNumber":""
}

#### Unit Test - `testCreateUserAccountWhenAccountNumberExistsThenThrowDuplicateAccountNumberException`, `testCreateUserAccountWhenUserNotFoundThenThrowUserNotFoundException`, `testCreateUserAccountWhenAccountNumberNotExistsAndUserFoundThenSaveUserAccount`

#### Request
http
POST /user-account/increment-balance

{
  "accountNumber": "",
  "amount": 
}

#### Unit Test - `testIncrementBalanceWhenAmountAndAccountNumberAreValidThenReturnUpdatedBalance`, `testIncrementBalanceWhenAmountIsLessThanOrEqualToZeroThenThrowException`, `testIncrementBalanceWhenAmountIsGreaterThanOrEqualTo1000ThenThrowException`, `testIncrementBalanceWhenAccountNumberIsInvalidThenThrowException`

























## Technology Stack

| Technology              | Description               |
|-------------------------|---------------------------|
| Core Framework          | Spring Boot               |
| Security Framework      | Spring Security, JWT      |
| Persistent Layer        | Spring Data JPA           |
| Database                | PostgreSQL                |

## Project Structure

- **authentication:** Manages application user authentication.
- **configuration:** Contains application configurations.
- **controllers:** Listens to client requests.
- **dto:** Data transfer objects for carrying data between processes.
- **enum:** Enumeration classes for maintaining constants.
- **exception:** Handles custom exception scenarios.
- **models:** Entity definitions representing database structures.
- **repository:** Communicates with the database.
- **service:** Contains business logic.
- **util:** Holds utility classes.
- **test/:** Contains unit tests.
- **build.gradle:** Lists all project dependencies.

## Usage

To run the project locally, follow these steps:

1. Clone the repository.
2. Navigate to the project directory.
3. Install project dependencies using the specified tools (e.g., Gradle).
4. Configure the database and other settings in `application.properties`.
5. Run the application using Spring Boot.
