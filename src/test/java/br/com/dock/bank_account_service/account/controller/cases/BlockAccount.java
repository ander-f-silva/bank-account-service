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
                                1L,
                                HttpStatus.NO_CONTENT)
                ),
                Arguments.of(
                        "Value is null - Bad Request",
                        new UserCase(
                                "{\"value\": null}",
                                1L,
                                HttpStatus.BAD_REQUEST)
                ),
                Arguments.of(
                        "Value is not valid - Bad Request",
                        new UserCase(
                                "{\"value\": \"asdfa\"}",
                                1L,
                                HttpStatus.BAD_REQUEST)
                ),
                Arguments.of(
                        "Account not found - Not found",
                        new UserCase(
                                "{\"value\": \"true\"}",
                                4L,
                                HttpStatus.NOT_FOUND)
                )
        );
    }

    @AllArgsConstructor
    @Getter
    public static class UserCase {
        private String request;
        private Long paramAccountId;
        private HttpStatus httpStatus;
    }
}
