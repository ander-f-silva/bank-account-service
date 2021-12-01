package br.com.dock.bank_account_service.account.controller.cases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

public class SearchBalance {

    public static Stream<Arguments> parametersSearchBalance() {
        return Stream.of(
                Arguments.of(
                        "Search performed successfully - OK",
                        new UserCase(
                                "{\"amount\":1000.0}",
                                HttpStatus.OK)
                )

                /*
                    TODO: Fazer a pesquisa de usuário não encontrado -> Lembrar que usuário tem que estar ativo
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
