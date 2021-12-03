package br.com.dock.bank_account_service.transaction.serivce;

import br.com.dock.bank_account_service.account.expection.AccountNotFoundException;
import br.com.dock.bank_account_service.account.expection.AmountAboveBalanceException;
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

    /*
  TODO:
    Fazer a validação para limite de saque por dia
    Fazer a validação se o valor do saque é maior que da conta
  */
    @Override
    public void apply(Long accountId, WithDraw withDraw) {
        var accountEntityRecoded = accountRepository.findById(accountId)
                .filter(AccountEntity::getFlagActive)
                .orElseThrow(() -> {
                    logger.info("[event: Withdraw Account] [param path: (accountId:{})] Account not found", accountId);

                    return new AccountNotFoundException();
                });

        if (checkAmountAboveBalance(accountEntityRecoded, withDraw)) {
            throw new AmountAboveBalanceException();
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

    private boolean checkAmountAboveBalance(AccountEntity accountEntity,  WithDraw withDraw) {
        return accountEntity.getBalance() < withDraw.getAmount();
    }
}
