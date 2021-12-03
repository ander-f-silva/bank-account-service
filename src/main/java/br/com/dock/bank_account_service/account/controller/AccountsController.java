package br.com.dock.bank_account_service.account.controller;


import br.com.dock.bank_account_service.account.dto.Account;
import br.com.dock.bank_account_service.account.dto.AccountAtiveStatus;
import br.com.dock.bank_account_service.account.dto.AccountStatement;
import br.com.dock.bank_account_service.account.service.BlockAccount;
import br.com.dock.bank_account_service.account.service.CreateAccount;
import br.com.dock.bank_account_service.account.service.GetAccountStatement;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
class AccountsController {
    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    private final CreateAccount createAccount;

    private final BlockAccount blockAccount;

    private final GetAccountStatement getAccountStatement;

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
    ResponseEntity<AccountStatement> searchAccountStatement(@RequestParam(value = "page", defaultValue = "1") @Valid @Positive @Max(100) Integer page, @RequestParam(value = "size", defaultValue = "30") @Valid @Positive @Max(100) Integer size, @PathVariable Long accountId) {
        var accountStatement = getAccountStatement.findByAccountId(accountId, page, size);

        logger.info("[event: Get Account Statement] [param path: (accountId:{})] [response: {}]  Get Account Statement with success", accountId, accountStatement);

        return ResponseEntity.ok(accountStatement);
    }
}
