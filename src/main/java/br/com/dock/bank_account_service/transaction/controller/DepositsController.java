package br.com.dock.bank_account_service.transaction.controller;

import br.com.dock.bank_account_service.transaction.controller.dto.DepositRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/deposits")
class DepositsController {

    @PostMapping
    ResponseEntity<Void> apply(@RequestParam Long accountId, @RequestBody @Valid DepositRequest request) {
        return ResponseEntity.created(URI.create("/balance?accountId=1")).build();
    }

}
