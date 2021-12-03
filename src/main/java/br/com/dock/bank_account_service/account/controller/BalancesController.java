package br.com.dock.bank_account_service.account.controller;

import br.com.dock.bank_account_service.account.dto.Amount;
import br.com.dock.bank_account_service.account.service.GetBalance;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balances")
@AllArgsConstructor
class BalancesController {
    private static final Logger logger = LoggerFactory.getLogger(BalancesController.class);

    private final GetBalance getBalance;

    @GetMapping
    ResponseEntity<Amount> search(@RequestParam("accountId") Long accountId) {
        var amount = getBalance.findByAccountId(accountId);

        logger.info("[event: Get Balance] [param path: (accountId:{})] [response: {}] Get balance searched with success", accountId, amount);

        return ResponseEntity.ok(amount);
    }
}
