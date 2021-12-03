package br.com.dock.bank_account_service.account.service;


import br.com.dock.bank_account_service.account.dto.AccountAtiveStatus;

public interface BlockAccount {
    void apply(Long accountId, AccountAtiveStatus accountAtiveStatus);
}
