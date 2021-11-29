package br.com.dock.bank_account_service.account.controller;


import br.com.dock.bank_account_service.account.controller.dto.AccountRequest;
import br.com.dock.bank_account_service.account.controller.dto.AccountResponse;
import br.com.dock.bank_account_service.account.controller.dto.AccountStatementResponse;
import br.com.dock.bank_account_service.account.controller.dto.BlockAccountRequest;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import br.com.dock.bank_account_service.person.repository.PersonEntity;
import br.com.dock.bank_account_service.person.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
class AccountsController {
    private final AccountRepository accountRepository;

    private final PersonRepository personRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    Mono<AccountResponse> create(@RequestBody @Valid AccountRequest request) {

        var person = PersonEntity.builder()
                .name(request.getPerson().getName())
                .documentation(request.getPerson().getDocument())
                .birthday(request.getPerson().getDateBirthday())
                .build();

      var monoPerson = personRepository.save(person);

      var personSaved = monoPerson.block(Duration.ofMillis(3000));

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
