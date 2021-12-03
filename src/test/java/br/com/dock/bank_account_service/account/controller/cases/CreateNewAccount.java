package br.com.dock.bank_account_service.account.controller.cases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class CreateNewAccount {

    public static Stream<Arguments> parametersCreateNewAccount() {
        var today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return Stream.of(
                Arguments.of(
                        "Create with success - OK",
                        new UserCase(
                                "{\"person\": {\"name\": \"Antonio da Silva\", \"document\": \"84047092037\", \"dateBirthday\": \"1990-01-01\"},\"withdrawalDayLimit\": 10000.00, \"accountType\": \"CHECKING_ACCOUNT\"}",
                                "{\"id\":2,\"person\":{\"id\":2,\"name\":\"Antonio da Silva\",\"document\":\"84047092037\",\"dateBirthday\":\"1990-01-01\"},\"balance\":0.0,\"withdrawalDayLimit\":10000.0,\"accountType\":\"CHECKING_ACCOUNT\",\"createdAt\":\"" + today + "\"}",
                                HttpStatus.OK)
                ),
                Arguments.of(
                        "Person is null - Bad Request",
                        new UserCase(
                                "{\"person\": null}",
                                "",
                                HttpStatus.BAD_REQUEST)
                ),
                Arguments.of(
                        "CPF is invalid - Bad Request",
                        new UserCase(
                                "{\"person\": {\"name\": \"Antonio da Silva\", \"document\": \"111111111\", \"dateBirthday\": \"1990-01-01\"},\"withdrawalDayLimit\": 10000.00, \"accountType\": \"CHECKING_ACCOUNT\"}",
                                "",
                                HttpStatus.BAD_REQUEST)
                ),
                Arguments.of(
                        "Date Birthday is invalid - Bad Request",
                        new UserCase(
                                "{\"person\": {\"name\": \"Antonio da Silva\", \"document\": \"84047092037\", \"dateBirthday\": \"199S-01-A1\"},\"withdrawalDayLimit\": 10000.00, \"accountType\": \"CHECKING_ACCOUNT\"}",
                                "",
                                HttpStatus.BAD_REQUEST)
                ),
                Arguments.of(
                        "Day Limit is invalid - Bad Request",
                        new UserCase(
                                "{\"person\": {\"name\": \"Antonio da Silva\", \"document\": \"84047092037\", \"dateBirthday\": \"199S-01-A1\"},\"withdrawalDayLimit\": -10000.00, \"accountType\": \"CHECKING_ACCOUNT\"}",
                                "",
                                HttpStatus.BAD_REQUEST)
                ),
                Arguments.of(
                        "Account already exist - Unprocessable Entity",
                        new UserCase(
                                "{\"person\": {\"name\": \"Antonio da Silva\", \"document\": \"84047092037\", \"dateBirthday\": \"1990-01-01\"},\"withdrawalDayLimit\": 10000.00, \"accountType\": \"CHECKING_ACCOUNT\"}",
                                "",
                                HttpStatus.UNPROCESSABLE_ENTITY)
                )
        );
    }

    @AllArgsConstructor
    @Getter
    public static class UserCase {
        private String request;
        private String response;
        private HttpStatus httpStatus;
    }
}
