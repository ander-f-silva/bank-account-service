package br.com.dock.bank_account_service.account.service;

import br.com.dock.bank_account_service.account.dto.Amount;

public interface GetBalance {
    Amount findByAccountId(Long accountId);
}
