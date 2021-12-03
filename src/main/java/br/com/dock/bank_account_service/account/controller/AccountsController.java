package br.com.dock.bank_account_service.account.controller;


import br.com.dock.bank_account_service.account.dto.Account;
import br.com.dock.bank_account_service.account.dto.AccountAtiveStatus;
import br.com.dock.bank_account_service.account.dto.AccountStatement;
import br.com.dock.bank_account_service.account.service.BlockAccount;
import br.com.dock.bank_account_service.account.service.CreateAccount;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
class AccountsController {
    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    private final CreateAccount createAccount;

    private final BlockAccount blockAccount;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    ResponseEntity<Account> create(@RequestBody @Valid Account account) {
        var accountCreated = createAccount.perform(account);

        logger.info("[event: Create Account] [request: {}] [response: {}] Account created with success", account, accountCreated);

        return ResponseEntity.ok(accountCreated);
    }

    @PatchMapping("/{accountId}/blocks")
    @Transactional
    ResponseEntity<Void> block(@PathVariable Long accountId, @RequestBody @Valid AccountAtiveStatus accountAtiveStatus) {
        blockAccount.apply(accountId, accountAtiveStatus);

        logger.info("[event: Block Account] [param path: (accountId:{})] [request: {}] Account blocked with success", accountId, accountAtiveStatus);

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
