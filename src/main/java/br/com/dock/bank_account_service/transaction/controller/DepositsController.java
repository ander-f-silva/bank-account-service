package br.com.dock.bank_account_service.transaction.controller;

import br.com.dock.bank_account_service.transaction.dto.Deposit;
import br.com.dock.bank_account_service.transaction.serivce.DoDeposit;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/deposits")
@AllArgsConstructor
class DepositsController {
    private static final Logger logger = LoggerFactory.getLogger(DepositsController.class);

    private static final String PATH_BALANCE = "/balance?accountId=";

    private final DoDeposit doDeposit;

    @PostMapping
    @Transactional
    ResponseEntity<Void> apply(@RequestParam Long accountId, @RequestBody @Valid Deposit deposit) {
        doDeposit.apply(accountId, deposit);

        logger.info("[event: Deposit Account] [param path: (accountId:{})] [request: {}] Deposit with success", accountId, deposit);

        return ResponseEntity.created(URI.create(PATH_BALANCE + accountId)).build();
    }
}
