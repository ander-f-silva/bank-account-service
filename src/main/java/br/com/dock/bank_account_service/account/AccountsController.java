package br.com.dock.bank_account_service.account;

import br.com.dock.bank_account_service.account.payloads.AccountRequest;
import br.com.dock.bank_account_service.account.payloads.AccountResponse;
import br.com.dock.bank_account_service.account.payloads.AccountStatementResponse;
import br.com.dock.bank_account_service.account.payloads.BlockAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;

@RestController
@RequestMapping("/accounts")
class AccountsController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    Mono<AccountResponse> create(@RequestBody @Valid AccountRequest request) {

        var response = AccountResponse
                .builder()
                .id(1L)
                .person(new AccountResponse.Person(1L, "Antonio da Silva", "840XXXXXX37", LocalDate.of(1990, 1, 1)))
                .createdDate(LocalDate.now())
                .balance(0.0)
                .dayLimit(10000.00)
                .accountType(AccountResponse.AccountType.CHECKING_ACCOUNT)
                .build();

        return Mono.just(response);
    }

    @PatchMapping("/{accountId}/blocks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Mono<Void> block(@PathVariable Long accountId, @RequestBody @Valid BlockAccountRequest request) {
        return Mono.empty();
    }

    @GetMapping("/{accountId}/transactions")
    Mono<AccountStatementResponse> searchAccountStatement(@PathVariable Long accountId) {
        return Mono.just(
                new AccountStatementResponse(
                        Arrays.asList(
                                new AccountStatementResponse.Transaction(1L, 100.0, AccountStatementResponse.EventType.DEPOSIT, LocalDate.of(2021, 12, 1)),
                                new AccountStatementResponse.Transaction(2L, 50.0, AccountStatementResponse.EventType.WITHDRAW, LocalDate.of(2021, 12, 1))
                        )
                )
        );
    }
}
