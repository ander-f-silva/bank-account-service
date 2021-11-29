package br.com.dock.bank_account_service.account.controller;

import br.com.dock.bank_account_service.account.controller.dto.AmountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balances")
class BalancesController {

    @GetMapping
    ResponseEntity<AmountResponse> search(@RequestParam("accountId") Long accountId) {
        return ResponseEntity.ok(new AmountResponse(1000.00));
    }
}
