package br.com.dock.bank_account_service.account;

import br.com.dock.bank_account_service.account.payloads.AmountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/balances")
class BalancesController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Mono<AmountResponse> search(@RequestParam("accountId") Long accountId) {
        return Mono.just(new AmountResponse(1000.00));
    }
}
