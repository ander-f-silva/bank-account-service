package br.com.dock.bank_account_service.account.dto;

import br.com.dock.bank_account_service.transaction.dto.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountStatement {
    private List<Transaction> transactions;
}
