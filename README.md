# UniTech Backend APIs

Welcome to the UniTech backend repository, housing the APIs for UniTech, a comprehensive fintech solution tailored for banking products.

### Custom Exception

#### The system handles specific scenarios through custom exceptions. These exceptions are multi-lingual to accommodate users across different languages, ensuring clear and concise error messages.
 
insert into public.custom_exception (id, created_by, created_date, created_time, insert_date, modification_by, modification_date, modification_time, status, error_code, lang, message)
values  ('afe41b19-720b-4906-94a3-3114da6b441e', null, 20231214, 165731, 20231214165731, null, null, null, 'A', 'unitech-2001', 'AZ', 'PIN artıq qeydiyyatdan keçirilib'),
        ('ffd19faf-7e3b-489e-83cb-4e1ecdbd7549', null, 20231214, 165731, 20231214165731, null, null, null, 'A', 'unitech-2001', 'RU', 'PIN уже зарегистрирован'),
        ('daeb47b7-d875-4e8c-8ef4-2daddb64c3e8', null, 20231214, 165731, 20231214165731, null, null, null, 'A', 'unitech-2001', 'EN', 'The PIN is already registered'),
        ('20d43a4a-422a-423f-9a0a-3c9ab3a4ce11', null, 20231214, 173833, 20231214173833, null, null, null, 'A', 'unitech-2002', 'RU', 'Неверный PIN-код'),
        ('a1d48ba8-dbc9-4f54-b3e1-4afa979cfdee', null, 20231214, 173833, 20231214173833, null, null, null, 'A', 'unitech-2002', 'EN', 'The PIN is incorrect'),
        ('affe9665-8924-4c82-a582-0f60142ba86b', null, 20231214, 173833, 20231214173833, null, null, null, 'A', 'unitech-2002', 'AZ', 'PIN yanlışdır'),
        ('a30c41a3-7054-4f2e-97ab-ec922199f429', null, 20231214, 173909, 20231214173909, null, null, null, 'A', 'unitech-2003', 'EN', 'The password is incorrect'),
        ('9ffe7689-86ec-45d7-9bba-1c9edf799c49', null, 20231214, 173909, 20231214173909, null, null, null, 'A', 'unitech-2003', 'AZ', 'Şifrə yanlışdır'),
        ('63dc884e-2031-4152-bfc2-1c1e910ad5f1', null, 20231214, 173909, 20231214173909, null, null, null, 'A', 'unitech-2003', 'RU', 'Неправильный пароль'),
        ('502d6ce1-caa5-4db3-b747-5ff41cf05811', null, 20231217, 174705, 20231217174705, null, null, null, 'A', 'unitech-2004', 'RU', 'Номер счета уже существует'),
        ('9935a4e3-3e2d-40e1-979d-c5a95935732c', null, 20231217, 174705, 20231217174705, null, null, null, 'A', 'unitech-2004', 'EN', 'Account number already exists'),
        ('ed4c51df-9121-45cc-83e5-85a19f58f554', null, 20231217, 174704, 20231217174704, null, null, null, 'A', 'unitech-2004', 'AZ', 'Hesab nömrəsi artıq mövcuddur'),
        ('1b79728a-ef22-46fa-99cb-7d80beca6538', null, 20231217, 174810, 20231217174810, null, null, null, 'A', 'unitech-2005', 'AZ', 'İstifadəçi tapılmadı'),
        ('910849f2-abf6-4e3c-9fa9-4c2470b0c935', null, 20231217, 174810, 20231217174810, null, null, null, 'A', 'unitech-2005', 'EN', 'User not found'),
        ('d3922762-2c98-4032-9ac5-1e9f0c3f8927', null, 20231217, 174810, 20231217174810, null, null, null, 'A', 'unitech-2005', 'RU', 'Пользователь не найден'),
        ('be4f5a9e-230c-4452-b832-9fbd0acef113', null, 20231217, 174903, 20231217174903, null, null, null, 'A', 'unitech-2006', 'AZ', 'Hesap nömrəsi 16 rəqəmli olmalıdır'),
        ('2663c3fd-ba74-48ac-b545-6071e3d888ee', null, 20231217, 174903, 20231217174903, null, null, null, 'A', 'unitech-2006', 'EN', 'Account number must be a 16-digit number'),
        ('723ae2aa-23d5-4528-b4be-f2500eab0444', null, 20231217, 174903, 20231217174903, null, null, null, 'A', 'unitech-2006', 'RU', 'Номер счета должен состоять из 16 цифр'),
        ('61d86abf-217d-4817-a0c4-8749cf245e66', null, 20231217, 175013, 20231217175013, null, null, null, 'A', 'unitech-2007', 'RU', 'Невозможен перевод средств на неактивный счёт'),
        ('f31cab19-4d2b-4dee-9bff-03e2e11b3050', null, 20231217, 175013, 20231217175013, null, null, null, 'A', 'unitech-2007', 'EN', 'Cannot transfer funds to inactive account'),
        ('0fb34636-27da-4bb4-a18c-71d5136fd571', null, 20231217, 175013, 20231217175013, null, null, null, 'A', 'unitech-2007', 'AZ', 'Pasiv hesaba pul köçürmək olmaz'),
        ('66766e9b-1501-4011-8c52-e101d4b0720d', null, 20231217, 175110, 20231217175110, null, null, null, 'A', 'unitech-2008', 'RU', 'Недостаточный баланс'),
        ('1598f86e-8777-4c7c-8036-c076a7ae4ac8', null, 20231217, 175110, 20231217175110, null, null, null, 'A', 'unitech-2008', 'EN', 'Insufficient balance'),
        ('9483dda7-9813-42f1-9060-60ddbd70542a', null, 20231217, 175110, 20231217175110, null, null, null, 'A', 'unitech-2008', 'AZ', 'Kifayət qədər bakiyə yoxdur'),
        ('094e7c72-292e-45d5-9e08-6e0e15e9567e', null, 20231217, 175207, 20231217175207, null, null, null, 'A', 'unitech-2009', 'AZ', 'Eyni hesaba pul köçürmələri edilə bilməz'),
        ('f4d7948f-6c03-4631-b10e-18a26ade5b93', null, 20231217, 175207, 20231217175207, null, null, null, 'A', 'unitech-2009', 'EN', 'Cannot transfer money to the same account'),
        ('828cc01c-ebda-4db5-a5e1-6251d3d16156', null, 20231217, 175207, 20231217175207, null, null, null, 'A', 'unitech-2009', 'RU', 'Перевод на тот же счет невозможен'),
        ('06d9f6fc-cee1-4e81-a541-0f07f0bb7ab2', null, 20231217, 175306, 20231217175306, null, null, null, 'A', 'unitech-2010', 'RU', 'Счёт не найден'),
        ('ed73c77d-c02e-47d5-9337-487b23743063', null, 20231217, 175306, 20231217175306, null, null, null, 'A', 'unitech-2010', 'EN', 'Account not found'),
        ('5a3e154c-8734-4b7b-8a84-7725d2686164', null, 20231217, 175306, 20231217175306, null, null, null, 'A', 'unitech-2010', 'AZ', 'Hesab tapılmadı'),
        ('f5005440-799c-4616-9157-73ff3ae52d26', null, 20231217, 175930, 20231217175930, null, null, null, 'A', 'unitech-2011', 'AZ', 'Valyuta tapılmadı'),
        ('ae4267e7-28a6-42d0-ab87-5b1b2a0a1a4c', null, 20231217, 175930, 20231217175930, null, null, null, 'A', 'unitech-2011', 'EN', 'Currency not found'),
        ('10f814fd-68ae-42f1-9545-e9da181b43f4', null, 20231217, 175930, 20231217175930, null, null, null, 'A', 'unitech-2011', 'RU', 'Валюта не найдена'),
        ('4ae31f33-e2a0-4eda-8484-8f64b3765ccc', '', 20231218, 2610, 20231218002610, null, null, null, 'A', 'unitech-2012', 'AZ', 'Dəyər diapazondan kənardadır, lütfən, 0 ilə 1000 arasında rəqəm daxil edin'),
        ('296358df-ffea-41e8-95a0-df82a1014914', '', 20231218, 2610, 20231218002610, null, null, null, 'A', 'unitech-2012', 'EN', 'Value out of range, please enter a number between 0 and 1000'),
        ('6d45cab3-8992-4532-86e3-665e2dffa562', '', 20231218, 2610, 20231218002610, null, null, null, 'A', 'unitech-2012', 'RU', 'Значение вне диапазона, пожалуйста, введите число от 0 до 1000');

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
