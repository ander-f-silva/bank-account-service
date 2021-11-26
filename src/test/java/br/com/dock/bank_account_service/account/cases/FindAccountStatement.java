package br.com.dock.bank_account_service.account.cases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

public class FindAccountStatement {

    public static Stream<Arguments> parametersFindAccountStatement() {
        return Stream.of(
                Arguments.of(
                        "Search performed successfully - OK",
                        new FindAccountStatement.UserCase(
                                "{\"transactions\":[{\"id\": 1,\"amount\": 100.00,\"event\":\"DEPOSIT\",\"createdDate\":\"2021-12-01\"},{\"id\": 2,\"amount\": 50.0,\"event\": \"WITHDRAW\",\"createdDate\": \"2021-12-01\"}]}",
                                HttpStatus.OK)
                )

                /*
                    TODO: Fazer a validação para checar se o usuário existe
                 */
        );
    }

    @AllArgsConstructor
    @Getter
    public static class UserCase {
        private String response;
        private HttpStatus httpStatus;
    }
}
