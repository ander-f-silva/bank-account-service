package br.com.dock.bank_account_service.account.service;

import br.com.dock.bank_account_service.account.dto.AccountStatement;

public interface GetAccountStatement {
    AccountStatement findByAccountId(Long accountId, Integer page, Integer size);
}
