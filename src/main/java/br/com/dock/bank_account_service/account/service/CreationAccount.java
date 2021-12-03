package br.com.dock.bank_account_service.account.service;

import br.com.dock.bank_account_service.account.dto.Account;
import br.com.dock.bank_account_service.account.dto.AccountType;
import br.com.dock.bank_account_service.account.dto.Person;
import br.com.dock.bank_account_service.account.expection.AccountAlreadyExistException;
import br.com.dock.bank_account_service.account.repository.AccountEntity;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import br.com.dock.bank_account_service.person.repository.PersonEntity;
import br.com.dock.bank_account_service.person.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
class CreationAccount implements CreateAccount {
    private static final Logger logger = LoggerFactory.getLogger(CreationAccount.class);

    private final AccountRepository accountRepository;

    private final PersonRepository personRepository;

    @Override
    @Transactional
    public Account perform(Account account) {
        return saveAccount(account);
    }

    private Account saveAccount(final Account account) {
        if (personRepository.existsByDocument(account.getPerson().getDocument())) {
            logger.error("[event: Create Account] [request: {}] Account already exist", account);
            throw  new AccountAlreadyExistException();
        }

        var person = savePerson(account.getPerson());

        var accountNewEntity = AccountEntity.builder()
                .idPerson(person.getId())
                .withdrawalDayLimit(account.getWithdrawalDayLimit())
                .flagActive(false)
                .balance(0.0)
                .createdAt(LocalDate.now())
                .accountType(account.getAccountType().getValueNumber())
                .build();

        var accountEntitySaved = accountRepository.save(accountNewEntity);

        return Account.builder()
                .id(accountEntitySaved.getIdAccount())
                .person(person)
                .createdAt(accountEntitySaved.getCreatedAt())
                .balance(accountEntitySaved.getBalance())
                .withdrawalDayLimit(accountEntitySaved.getWithdrawalDayLimit())
                .accountType(AccountType.CHECKING_ACCOUNT.getValue(accountEntitySaved.getAccountType()))
                .build();
    }

    private Person savePerson(final Person person) {
        var personNewEntity = PersonEntity.builder()
                .name(person.getName())
                .document(person.getDocument())
                .birthday(person.getDateBirthday())
                .build();

        var personEntitySaved = personRepository.save(personNewEntity);

        return new Person(personEntitySaved.getIdPerson(), personEntitySaved.getName(), personEntitySaved.getDocument(), personEntitySaved.getBirthday());
    }
}
