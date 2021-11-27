package br.com.dock.bank_account_service.account.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BlockAccountRequest {
    @NotNull
    private Boolean value;
}


