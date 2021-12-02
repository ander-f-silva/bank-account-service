package br.com.dock.bank_account_service.account.service;

import br.com.dock.bank_account_service.account.dto.AccountAtiveStatus;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
class BlockingAccount implements BlockAccount {
    private final AccountRepository accountRepository;

    @Override
    public void apply(Long accountId, AccountAtiveStatus accountAtiveStatus) {
        //TODO: Validate if account exist
        if (!accountRepository.existsById(accountId)) {
            throw new NoSuchElementException();
        }

        accountRepository.updateFlagActiveByAccountId(accountAtiveStatus.getValue(), accountId);
    }
}
