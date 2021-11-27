package br.com.dock.bank_account_service.transaction.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepositRequest {
    @Positive
    @NotNull
    private Double amount;
}
