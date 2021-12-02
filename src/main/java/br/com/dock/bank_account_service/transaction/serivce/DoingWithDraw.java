package br.com.dock.bank_account_service.transaction.serivce;

import br.com.dock.bank_account_service.account.repository.AccountRepository;
import br.com.dock.bank_account_service.transaction.dto.WithDraw;
import br.com.dock.bank_account_service.transaction.repository.TransactionEntity;
import br.com.dock.bank_account_service.transaction.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
class DoingWithDraw implements DoWithdraw {
    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    /*
  TODO:
    Fazer a tratativa para caso de conta não econtrada
    Fazer a query somente para contas ativas
    Fazer a validação para limite de saque por dia
    Fazer a validação se o valor do saque é maior que da conta
  */
    @Override
    public void apply(Long accountId, WithDraw withDraw) {
        var accountEntityRecoded =  accountRepository.findById(accountId)
                .orElseThrow(NoSuchElementException::new);


        var currentBalance = accountEntityRecoded.getBalance() - withDraw.getAmount();

        accountRepository.updateBalanceByAccountId(currentBalance, accountId);

       var amountDrawn = withDraw.getAmount() * -1.0;

        var newTransaction = TransactionEntity.builder()
                .idAccount(accountId)
                .amount(amountDrawn)
                .createdAt(LocalDate.now())
                .build();

        transactionRepository.save(newTransaction);

        transactionRepository.save(newTransaction);
    }
}
