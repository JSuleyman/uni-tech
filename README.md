# UniTech Backend APIs

Welcome to the UniTech backend repository, housing the APIs for UniTech, a comprehensive fintech solution tailored for banking products.

### Custom Exception

#### The system handles specific scenarios through custom exceptions. These exceptions are multi-lingual to accommodate users across different languages, ensuring clear and concise error messages.

`/utility/exception/custom-exception-insert.txt` copy and paste to the sql console for the custom exception table.

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

### 4. Account to account API

#### Request
http
POST /user-account/money-transfer

{
  "fromAccountNumber": "",
  "toAccountNumber": "",
  "amount":""
}
#### Unit Test - `testTransferMoneyWhenTransferIsSuccessfulThenReturnFromAccountBalance`, `testTransferMoneyWhenFromAccountNotFoundThenThrowAccountNotFoundException`, `testTransferMoneyWhenToAccountNotFoundThenThrowAccountNotFoundException`, `testTransferMoneyWhenToAccountIsInactiveThenThrowInactiveAccountTransferException`, `testTransferMoneyWhenFromAndToAccountsAreSameThenThrowSameAccountTransferException`, `testTransferMoneyWhenFromAccountHasInsufficientBalanceThenThrowInsufficientBalanceException`

### 5. Currency rates API

#### Request
http
POST /exchange?from=&to=

#### Unit Test - `testGetCurrencyDataWhenFromAndToCurrencyAreSameThenReturnOne`, `testGetCurrencyDataWhenFromCurrencyIsAZNAndToCurrencyIsNotAZNThenReturnCorrectExchangeRate`, `testGetCurrencyDataWhenToCurrencyIsAZNAndFromCurrencyIsNotAZNThenReturnCorrectExchangeRate`, `testGetCurrencyDataWhenBothFromAndToCurrencyAreNotAZNThenReturnCorrectExchangeRate`, `testGetCurrencyDataWhenFromCurrencyNotFoundThenThrowCurrencyNotFoundException`, `testGetCurrencyDataWhenToCurrencyNotFoundThenThrowCurrencyNotFoundException`

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
