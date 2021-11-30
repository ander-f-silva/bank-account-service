package br.com.dock.bank_account_service.transaction.controller;

import br.com.dock.bank_account_service.account.repository.AccountRepository;
import br.com.dock.bank_account_service.transaction.controller.dto.DepositRequest;
import br.com.dock.bank_account_service.transaction.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/deposits")
@AllArgsConstructor
class DepositsController {
    private static final String PATH_BALANCE = "/balance?accountId=";

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    @PostMapping
    ResponseEntity<Void> apply(@RequestParam Long accountId, @RequestBody @Valid DepositRequest request) {
        return ResponseEntity.created(URI.create(PATH_BALANCE + accountId)).build();
    }

}
