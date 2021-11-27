package br.com.dock.bank_account_service.transaction;

import br.com.dock.bank_account_service.transaction.payload.DepositRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/deposits")
class DepositsController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Void> apply(@RequestParam Long accountId, @RequestBody @Valid DepositRequest request) {
        return Mono.empty();
    }

}
