package br.com.dock.bank_account_service.transaction.serivce;

import br.com.dock.bank_account_service.account.expection.AccountNotFoundException;
import br.com.dock.bank_account_service.account.repository.AccountEntity;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import br.com.dock.bank_account_service.transaction.dto.Deposit;
import br.com.dock.bank_account_service.transaction.repository.TransactionEntity;
import br.com.dock.bank_account_service.transaction.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
class DoingDeposit implements DoDeposit {
    private static final Logger logger = LoggerFactory.getLogger(DoingDeposit.class);

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    @Override
    public void apply(Long accountId, Deposit deposit) {
        var accountEntityRecoded = accountRepository.findById(accountId)
                .filter(AccountEntity::getFlagActive)
                .orElseThrow(() -> {
                    logger.error("[event: Deposit Account] [param path: (accountId:{})] Account not found", accountId);
                    return new AccountNotFoundException();
                });

        var currentBalance = accountEntityRecoded.getBalance() + deposit.getAmount();

        accountRepository.updateBalanceByAccountId(currentBalance, accountId);

        var newTransaction = TransactionEntity.builder()
                .idAccount(accountId)
                .amount(deposit.getAmount())
                .createdAt(LocalDate.now())
                .build();

        transactionRepository.save(newTransaction);
    }
}
