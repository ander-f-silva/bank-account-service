package br.com.dock.bank_account_service.account.controller.cases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

public class BlockAccount {

    public static Stream<Arguments> parametersBlockAccount() {
        return Stream.of(
                Arguments.of(
                        "Block with success - Not Content",
                        new UserCase(
                                "{\"value\": true}",
                                HttpStatus.NO_CONTENT)
                ),
                Arguments.of(
                        "Value is null - Bad Request",
                        new UserCase(
                                "{\"value\": null}",
                                HttpStatus.BAD_REQUEST)
                ),
                Arguments.of(
                        "Value is not valid - Bad Request",
                        new UserCase(
                                "{\"value\": \"asdfa\"}",
                                HttpStatus.BAD_REQUEST)
                )
//                Arguments.of(
//                        "CPF is invalid - Bad Request",
//                        new UserCase(
//                                "{\"person\": {\"name\": \"Antonio da Silva\", \"document\": \"111111111\", \"dateBirthday\": \"1990-01-01\"},\"dayLimit\": 10000.00, \"accountType\": \"CHECKING_ACCOUNT\"}",
//                                "{\"timestamp\":\"" + today + "\",\"path\":\"/accounts\",\"status\":400,\"error\":\"Bad Request\"}",
//                                HttpStatus.BAD_REQUEST)
//                ),
//                Arguments.of(
//                        "Date Birthday is invalid - Bad Request",
//                        new UserCase(
//                                "{\"person\": {\"name\": \"Antonio da Silva\", \"document\": \"84047092037\", \"dateBirthday\": \"199S-01-A1\"},\"dayLimit\": 10000.00, \"accountType\": \"CHECKING_ACCOUNT\"}",
//                                "{\"timestamp\":\"" + today + "\",\"path\":\"/accounts\",\"status\":400,\"error\":\"Bad Request\"}",
//                                HttpStatus.BAD_REQUEST)
//                ),
//                Arguments.of(
//                        "Day Limit is invalid - Bad Request",
//                        new UserCase(
//                                "{\"person\": {\"name\": \"Antonio da Silva\", \"document\": \"84047092037\", \"dateBirthday\": \"199S-01-A1\"},\"dayLimit\": -10000.00, \"accountType\": \"CHECKING_ACCOUNT\"}",
//                                "{\"timestamp\":\"" + today + "\",\"path\":\"/accounts\",\"status\":400,\"error\":\"Bad Request\"}",
//                                HttpStatus.BAD_REQUEST)
//                )

                /*
                    TODO: Fazer a validação para conta não encontrada
                 */
        );
    }

    @AllArgsConstructor
    @Getter
    public static class UserCase {
        private String request;
        private HttpStatus httpStatus;
    }
}
