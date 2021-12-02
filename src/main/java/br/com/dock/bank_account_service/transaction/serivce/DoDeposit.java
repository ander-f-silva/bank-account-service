package br.com.dock.bank_account_service.transaction.serivce;

import br.com.dock.bank_account_service.transaction.dto.Deposit;

public interface DoDeposit {
    void apply(Long accountId, Deposit deposit);
}
