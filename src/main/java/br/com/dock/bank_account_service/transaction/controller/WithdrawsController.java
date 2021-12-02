package br.com.dock.bank_account_service.transaction.controller;

import br.com.dock.bank_account_service.transaction.dto.WithDraw;
import br.com.dock.bank_account_service.transaction.serivce.DoWithdraw;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/withdraws")
@AllArgsConstructor
class WithdrawsController {
    private static final String PATH_BALANCE = "/balance?accountId=";

    private final DoWithdraw doWithdraw;

    @PostMapping
    ResponseEntity<Void> apply(@RequestParam Long accountId, @RequestBody @Valid WithDraw withDraw) {
        doWithdraw.apply(accountId, withDraw);
        return ResponseEntity.created(URI.create(PATH_BALANCE + accountId)).build();
    }
}
