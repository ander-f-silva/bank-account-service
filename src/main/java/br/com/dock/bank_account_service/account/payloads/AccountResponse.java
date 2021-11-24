package br.com.dock.bank_account_service.account.payloads;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AccountResponse {
    private Long id;

    private Person person;

    private Double balance;

    private Double dayLimit;

    private AccountType accountType;

    private LocalDate createdDate;

    public enum AccountType {
        CHECKING_ACCOUNT
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Person {
        private Long id;

        private String name;

        private String document;

        private LocalDate dateBirthday;
    }
}


