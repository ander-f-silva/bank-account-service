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
                                "{\"amount\": 1000.00}",
                                "",
                                HttpStatus.CREATED)
                ),
                Arguments.of(
                        "Amount is negative - Bad Request",
                        new UserCase(
                                "{\"amount\": -1000.00}",
                                "{\"timestamp\":\"" + today + "\",\"path\":\"/deposits\",\"status\":400,\"error\":\"Bad Request\"}",
                                HttpStatus.BAD_REQUEST)
                ),
                Arguments.of(
                        "Amount is null - Bad Request",
                        new UserCase(
                                "{\"amount\": null}",
                                "{\"timestamp\":\"" + today + "\",\"path\":\"/deposits\",\"status\":400,\"error\":\"Bad Request\"}",
                                HttpStatus.BAD_REQUEST)
                )


                /*
                    TODO: Fazer a validação para não deixar que usuário depositar acima do limite diario
                 */
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
