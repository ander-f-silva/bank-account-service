package br.com.dock.bank_account_service.account.cases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class FindAccountStatement {

    public static Stream<Arguments> parametersFindAccountStatement() {
        var today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        var test =

        "{\n" +
                "    transactions: [\n" +
                "{\n" +
                "        \"id\": 1,\n" +
                "        \"amount\": 100.00,\n" +
                "        \"event\": \"DEPOSIT\",\n" +
                "        \"creted_date\": \"2021-12-01\"\n" +
                "},\n" +
                "{\n" +
                "         \"id\": 1,\n" +
                "         \"amount\": 50.0,\n" +
                "         \"event\": \"WITHDRAW\",\n" +
                "          \"creted_date\": \"2021-12-01\"\n" +
                "},\n" +
                "     ]\n" +
                "}";

        return Stream.of(
                Arguments.of(
                        "Create with success - OK",
                        new FindAccountStatement.UserCase(
                                "{\"person\": {\"id\": 1, \"name\": \"Antonio da Silva\", \"document\": \"840XXXXXX37\", \"dateBirthday\": \"1990-01-01\"},\"id\": 1, \"balance\": 0, \"dayLimit\": 10000.00, \"accountType\": \"CHECKING_ACCOUNT\", \"createdDate\": \"" + today + "\"}",
                                HttpStatus.OK)
                )//,
//                Arguments.of(
//                        "Person is null - Bad Request",
//                        new CreateNewAccount.UserCase(
//                                "{\"person\": null}",
//                                "{\"timestamp\":\"" + today + "\",\"path\":\"/accounts\",\"status\":400,\"error\":\"Bad Request\"}",
//                                HttpStatus.BAD_REQUEST)
//                ),
//                Arguments.of(
//                        "CPF is invalid - Bad Request",
//                        new CreateNewAccount.UserCase(
//                                "{\"person\": {\"name\": \"Antonio da Silva\", \"document\": \"111111111\", \"dateBirthday\": \"1990-01-01\"},\"dayLimit\": 10000.00, \"accountType\": \"CHECKING_ACCOUNT\"}",
//                                "{\"timestamp\":\"" + today + "\",\"path\":\"/accounts\",\"status\":400,\"error\":\"Bad Request\"}",
//                                HttpStatus.BAD_REQUEST)
//                ),
//                Arguments.of(
//                        "Date Birthday is invalid - Bad Request",
//                        new CreateNewAccount.UserCase(
//                                "{\"person\": {\"name\": \"Antonio da Silva\", \"document\": \"84047092037\", \"dateBirthday\": \"199S-01-A1\"},\"dayLimit\": 10000.00, \"accountType\": \"CHECKING_ACCOUNT\"}",
//                                "{\"timestamp\":\"" + today + "\",\"path\":\"/accounts\",\"status\":400,\"error\":\"Bad Request\"}",
//                                HttpStatus.BAD_REQUEST)
//                ),
//                Arguments.of(
//                        "Day Limit is invalid - Bad Request",
//                        new CreateNewAccount.UserCase(
//                                "{\"person\": {\"name\": \"Antonio da Silva\", \"document\": \"84047092037\", \"dateBirthday\": \"199S-01-A1\"},\"dayLimit\": -10000.00, \"accountType\": \"CHECKING_ACCOUNT\"}",
//                                "{\"timestamp\":\"" + today + "\",\"path\":\"/accounts\",\"status\":400,\"error\":\"Bad Request\"}",
//                                HttpStatus.BAD_REQUEST)
//                )

                /*
                    TODO: Fazer a validação para não deixar que usuário seja cadastrado quando com uma CPF que já existe na base
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
