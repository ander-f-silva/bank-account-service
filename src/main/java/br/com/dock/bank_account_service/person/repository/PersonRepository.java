package br.com.dock.bank_account_service.person.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonRepository extends ReactiveCrudRepository<PersonEntity, Long> {
}
