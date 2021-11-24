package br.com.dock.bank_account_service.account.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountRequest {
    @Valid
    @NotNull
    private Person person;

    @NotNull
    @Positive
    private Double dayLimit;

    @NotNull
    private AccountType accountType;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Person {
        @NotNull
        private String name;

        @CPF
        private String document;

        @NotNull
        private LocalDate dateBirthday;
    }

    public enum AccountType {
        CHECKING_ACCOUNT
    }

}


