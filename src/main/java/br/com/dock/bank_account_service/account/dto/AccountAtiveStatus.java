package br.com.dock.bank_account_service.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountAtiveStatus {
    @NotNull
    private Boolean value;
}


