package br.com.dock.bank_account_service.transaction.controller;

import br.com.dock.bank_account_service.transaction.controller.dto.WithDrawRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/withdraws")
class WithdrawsController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Void> apply(@RequestParam Long accountId, @RequestBody @Valid WithDrawRequest request) {
        return Mono.empty();
    }
}
