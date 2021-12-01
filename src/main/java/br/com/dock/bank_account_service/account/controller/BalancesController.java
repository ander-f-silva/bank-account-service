package br.com.dock.bank_account_service.account.controller;

import br.com.dock.bank_account_service.account.controller.dto.AmountResponse;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/balances")
@AllArgsConstructor
class BalancesController {
    private final AccountRepository accountRepository;

    @GetMapping
    ResponseEntity<AmountResponse> search(@RequestParam("accountId") Long accountId) {
        var account =  accountRepository.findById(accountId)
                .orElseThrow(NoSuchElementException::new);

        return ResponseEntity.ok(new AmountResponse(account.getBalance()));
    }
}
