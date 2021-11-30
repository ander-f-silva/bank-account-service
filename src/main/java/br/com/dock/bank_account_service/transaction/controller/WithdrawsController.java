package br.com.dock.bank_account_service.transaction.controller;

import br.com.dock.bank_account_service.account.repository.AccountRepository;
import br.com.dock.bank_account_service.transaction.controller.dto.WithDrawRequest;
import br.com.dock.bank_account_service.transaction.repository.TransactionEntity;
import br.com.dock.bank_account_service.transaction.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/withdraws")
@AllArgsConstructor
class WithdrawsController {
    private static final String PATH_BALANCE = "/balance?accountId=";

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    @PostMapping
    ResponseEntity<Void> apply(@RequestParam Long accountId, @RequestBody @Valid WithDrawRequest request) {
        var account =  accountRepository.findById(accountId)
                .orElseThrow(NoSuchElementException::new);

        //TODO: Fazer a validação para limite de saque por dia

        //TODO: Fazer a validação se o valor do saque é maior que da conta

        var currentBalance = account.getBalance() - request.getAmount();

        accountRepository.updateBalanceByAccountId(currentBalance, accountId);

        var newTransaction = TransactionEntity.builder()
                .idAccount(accountId)
                .amount(request.getAmount())
                .createdAt(LocalDate.now())
                .build();

        transactionRepository.save(newTransaction);

        return ResponseEntity.created(URI.create(PATH_BALANCE + accountId)).build();
    }
}
