package br.com.dock.bank_account_service.account.controller.dto;

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

    private Double withdrawalDayLimit;

    private AccountType accountType;

    private LocalDate createdDate;

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


