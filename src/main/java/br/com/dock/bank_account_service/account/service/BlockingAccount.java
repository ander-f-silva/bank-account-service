package br.com.dock.bank_account_service.account.service;

import br.com.dock.bank_account_service.account.dto.AccountAtiveStatus;
import br.com.dock.bank_account_service.account.expection.AccountNotFoundException;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
class BlockingAccount implements BlockAccount {
    private static final Logger logger = LoggerFactory.getLogger(BlockingAccount.class);

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void apply(Long accountId, AccountAtiveStatus accountAtiveStatus) {
        if (!accountRepository.existsById(accountId)) {
            logger.error("[event: Block Account] [param path: (accountId:{})] [request: {}] Account not found", accountId, accountAtiveStatus);
            throw new AccountNotFoundException();
        }

        accountRepository.updateFlagActiveByAccountId(accountAtiveStatus.getValue(), accountId);
    }
}
