package br.com.dock.bank_account_service.account.service;

import br.com.dock.bank_account_service.account.dto.Amount;
import br.com.dock.bank_account_service.account.expection.AccountNotFoundException;
import br.com.dock.bank_account_service.account.repository.AccountEntity;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class GettingBalance implements GetBalance {
    private static final Logger logger = LoggerFactory.getLogger(GettingBalance.class);

    private final AccountRepository accountRepository;

    @Override
    public Amount findByAccountId(Long accountId) {
        var accountEntityRecoded = accountRepository.findById(accountId)
                .filter(AccountEntity::getFlagActive)
                .orElseThrow(() -> {
                    logger.info("[event: Get Balance] [param path: (accountId:{})]] Account not found", accountId);

                    return new AccountNotFoundException();
                });

        return new Amount(accountEntityRecoded.getBalance());
    }
}
