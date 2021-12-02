package br.com.dock.bank_account_service.transaction.controller;

import br.com.dock.bank_account_service.transaction.dto.Deposit;
import br.com.dock.bank_account_service.transaction.serivce.DoDeposit;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/deposits")
@AllArgsConstructor
class DepositsController {
    private static final String PATH_BALANCE = "/balance?accountId=";

    private final DoDeposit doDeposit;

    @PostMapping
    @Transactional
    ResponseEntity<Void> apply(@RequestParam Long accountId, @RequestBody @Valid Deposit deposit) {
        doDeposit.apply(accountId, deposit);
        return ResponseEntity.created(URI.create(PATH_BALANCE + accountId)).build();
    }
}
