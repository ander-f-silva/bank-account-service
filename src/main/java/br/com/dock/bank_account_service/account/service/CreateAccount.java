package br.com.dock.bank_account_service.account.service;

import br.com.dock.bank_account_service.account.dto.Account;

public interface CreateAccount {
    Account perform(Account account);
}
