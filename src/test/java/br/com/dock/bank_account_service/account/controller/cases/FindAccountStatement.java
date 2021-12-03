package br.com.dock.bank_account_service.account.controller.cases;

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
                                "{\"transactions\":[{\"id\":1,\"amount\":100.0,\"event\":\"DEPOSIT\",\"createdDate\":\"2021-12-01\"},{\"id\":2,\"amount\":50.0,\"event\":\"WITHDRAW\",\"createdDate\":\"2021-12-01\"}]}",
                                1L,
                                HttpStatus.OK)
                )
                //TODO: Fazer este teste de conta não encontrada
//                Arguments.of(
//                        "Account not found - Not found",
//                        new FindAccountStatement.UserCase(
//                                "",
//                                4000L,
//                                HttpStatus.FOUND)
//                )
        );
    }

    @AllArgsConstructor
    @Getter
    public static class UserCase {
        private String response;
        private Long paramAccountId;
        private HttpStatus httpStatus;
    }
}
