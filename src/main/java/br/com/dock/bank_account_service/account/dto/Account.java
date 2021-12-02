package br.com.dock.bank_account_service.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Account {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Person person;

    private Double balance;

    private Double withdrawalDayLimit;

    private AccountType accountType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate createdAt;
}


