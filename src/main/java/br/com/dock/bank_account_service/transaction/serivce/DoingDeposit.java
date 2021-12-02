package br.com.dock.bank_account_service.transaction.serivce;

import br.com.dock.bank_account_service.account.repository.AccountRepository;
import br.com.dock.bank_account_service.transaction.dto.Deposit;
import br.com.dock.bank_account_service.transaction.repository.TransactionEntity;
import br.com.dock.bank_account_service.transaction.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
class DoingDeposit implements DoDeposit {
    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    /*
  TODO:
    Fazer a tratativa para caso de conta não econtrada
    Fazer a query somente para contas ativas
  */
    @Override
    public void apply(Long accountId, Deposit deposit) {
        var accountEntityRecoded = accountRepository.findById(accountId)
                .orElseThrow(NoSuchElementException::new);

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
