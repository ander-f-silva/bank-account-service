package br.com.dock.bank_account_service.account.controller;


import br.com.dock.bank_account_service.account.dto.Account;
import br.com.dock.bank_account_service.account.dto.AccountStatement;
import br.com.dock.bank_account_service.account.dto.BlockAccount;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import br.com.dock.bank_account_service.account.service.CreateAccount;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
class AccountsController {
    private final AccountRepository accountRepository;

    private final CreateAccount createAccount;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    ResponseEntity<Account> create(@RequestBody @Valid Account account) {
        return ResponseEntity.ok(createAccount.perform(account));
    }

    @PatchMapping("/{accountId}/blocks")
    @Transactional
    ResponseEntity<Void> block(@PathVariable Long accountId, @RequestBody @Valid BlockAccount request) {
        //TODO: Validate if account exist
        if (!accountRepository.existsById(accountId)) {
            throw new NoSuchElementException();
        }

        accountRepository.updateFlagActiveByAccountId(request.getValue(), accountId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{accountId}/transactions")
    ResponseEntity<AccountStatement> searchAccountStatement(@PathVariable Long accountId) {
        return ResponseEntity.ok(
                new AccountStatement(
                        Arrays.asList(
                                new AccountStatement.Transaction(1L, 100.0, AccountStatement.EventType.DEPOSIT, LocalDate.of(2021, 12, 1)),
                                new AccountStatement.Transaction(2L, 50.0, AccountStatement.EventType.WITHDRAW, LocalDate.of(2021, 12, 1))
                        )
                )
        );
    }
}
