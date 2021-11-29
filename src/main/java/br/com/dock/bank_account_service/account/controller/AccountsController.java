package br.com.dock.bank_account_service.account.controller;


import br.com.dock.bank_account_service.account.controller.dto.*;
import br.com.dock.bank_account_service.account.repository.AccountEntity;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import br.com.dock.bank_account_service.person.repository.PersonEntity;
import br.com.dock.bank_account_service.person.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Transactional
    ResponseEntity<AccountResponse> create(@RequestBody @Valid AccountRequest request) {
        var person = PersonEntity.builder()
                .name(request.getPerson().getName())
                .document(request.getPerson().getDocument())
                .birthday(request.getPerson().getDateBirthday())
                .build();

        var personCreated = personRepository.save(person);

        var account = AccountEntity.builder()
                .idPerson(personCreated.getIdPerson())
                .dayLimit(request.getDayLimit())
                .flagActive(true)
                .balance(0.0)
                .createdAt(LocalDate.now())
                .accountType(request.getAccountType().getValueNumber())
                .build();

        var accountCreated =  accountRepository.save(account);

        //TODO: Fazer o código para mascarar o CPF -> Usar um View Helper

        var response = AccountResponse
                .builder()
                .id(account.getIdAccount())
                .person(new AccountResponse.Person(person.getIdPerson(), person.getName(), person.getDocument(), person.getBirthday()))
                .createdDate(accountCreated.getCreatedAt())
                .balance(accountCreated.getBalance())
                .dayLimit(accountCreated.getDayLimit())
                .accountType(AccountType.CHECKING_ACCOUNT.getValue(accountCreated.getAccountType()))
                .build();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{accountId}/blocks")
    ResponseEntity<Void> block(@PathVariable Long accountId, @RequestBody @Valid BlockAccountRequest request) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{accountId}/transactions")
    ResponseEntity<AccountStatementResponse> searchAccountStatement(@PathVariable Long accountId) {
        return ResponseEntity.ok(
                new AccountStatementResponse(
                        Arrays.asList(
                                new AccountStatementResponse.Transaction(1L, 100.0, AccountStatementResponse.EventType.DEPOSIT, LocalDate.of(2021, 12, 1)),
                                new AccountStatementResponse.Transaction(2L, 50.0, AccountStatementResponse.EventType.WITHDRAW, LocalDate.of(2021, 12, 1))
                        )
                )
        );
    }
}
