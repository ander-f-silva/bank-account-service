package br.com.dock.bank_account_service.account.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AccountRepository extends ReactiveCrudRepository<AccountEntity, Long> {
}
