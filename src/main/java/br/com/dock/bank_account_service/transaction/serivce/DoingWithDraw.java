package br.com.dock.bank_account_service.transaction.serivce;

import br.com.dock.bank_account_service.account.expection.AccountNotFoundException;
import br.com.dock.bank_account_service.account.expection.AmountAboveBalanceException;
import br.com.dock.bank_account_service.account.expection.WithdrawLimitException;
import br.com.dock.bank_account_service.account.repository.AccountEntity;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import br.com.dock.bank_account_service.transaction.dto.WithDraw;
import br.com.dock.bank_account_service.transaction.repository.TransactionEntity;
import br.com.dock.bank_account_service.transaction.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
class DoingWithDraw implements DoWithdraw {
    private static final Logger logger = LoggerFactory.getLogger(DoingWithDraw.class);

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    @Override
    public void apply(Long accountId, WithDraw withDraw) {
        var accountEntityRecoded = accountRepository.findById(accountId)
                .filter(AccountEntity::getFlagActive)
                .orElseThrow(() -> {
                    logger.error("[event: Withdraw Account] [param path: (accountId:{})] Account not found", accountId);
                    return new AccountNotFoundException();
                });

        if (checkAmountAboveBalance(accountEntityRecoded, withDraw)) {
            logger.error("[event: Withdraw Account] [param path: (accountId:{})] Amount above balance", accountId);
            throw new AmountAboveBalanceException();
        }

        if (checkTfTotalDailyTransitionsGreaterThanLimit(accountEntityRecoded, withDraw)) {
            logger.error("[event: Withdraw Account] [param path: (accountId:{})] Total daily transitions greater than withdraw limit", accountId);
            throw new WithdrawLimitException();
        }

        var currentBalance = accountEntityRecoded.getBalance() - withDraw.getAmount();

        accountRepository.updateBalanceByAccountId(currentBalance, accountId);

        var amountDrawn = withDraw.getAmount() * -1.0;

        var newTransaction = TransactionEntity.builder()
                .idAccount(accountId)
                .amount(amountDrawn)
                .createdAt(LocalDate.now())
                .build();

        transactionRepository.save(newTransaction);
    }

    private boolean checkAmountAboveBalance(AccountEntity accountEntity, WithDraw withDraw) {
        return accountEntity.getBalance() < withDraw.getAmount();
    }

    private boolean checkTfTotalDailyTransitionsGreaterThanLimit(AccountEntity accountEntity, WithDraw withDraw) {
        var totalTransactionsDaily = transactionRepository.sumTransactionInDay(accountEntity.getIdAccount(), LocalDate.now());

        var totalWithdraw = totalTransactionsDaily == null ? 0.0 : totalTransactionsDaily * -1.0;

        return (totalWithdraw + withDraw.getAmount()) > accountEntity.getWithdrawalDayLimit();
    }
}
