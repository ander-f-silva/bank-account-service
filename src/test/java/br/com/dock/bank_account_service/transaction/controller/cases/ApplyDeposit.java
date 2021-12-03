package br.com.dock.bank_account_service.transaction.controller.cases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class ApplyDeposit {

    public static Stream<Arguments> parametersApplyDeposit() {
        var today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return Stream.of(
                Arguments.of(
                        "Apply with success - Created",
                        new UserCase(
                                1L,
                                "{\"amount\": 1000.00}",
                                "",
                                HttpStatus.CREATED)
                ),
                Arguments.of(
                        "Amount is negative - Bad Request",
                        new UserCase(
                                1L,
                                "{\"amount\": -1000.00}",
                                "",
                                HttpStatus.BAD_REQUEST)
                ),
                Arguments.of(
                        "Amount is null - Bad Request",
                        new UserCase(
                                1L,
                                "{\"amount\": null}",
                                "",
                                HttpStatus.BAD_REQUEST)
                ),
                Arguments.of(
                        "Account not found - Not found",
                        new UserCase(
                                54L,
                                "{\"amount\": 10.5}",
                                "",
                                HttpStatus.NOT_FOUND)
                )
        );
    }

    @AllArgsConstructor
    @Getter
    public static class UserCase {
        private Long queryAccountId;
        private String request;
        private String response;
        private HttpStatus httpStatus;
    }
}
