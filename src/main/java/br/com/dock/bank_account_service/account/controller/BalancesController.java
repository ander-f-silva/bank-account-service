package br.com.dock.bank_account_service.account.controller;

import br.com.dock.bank_account_service.account.dto.Amount;
import br.com.dock.bank_account_service.account.service.GetBalance;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balances")
@AllArgsConstructor
class BalancesController {
    private final GetBalance getBalance;

    @GetMapping
    ResponseEntity<Amount> search(@RequestParam("accountId") Long accountId) {
        return ResponseEntity.ok(getBalance.findByAccountId(accountId));
    }
}
