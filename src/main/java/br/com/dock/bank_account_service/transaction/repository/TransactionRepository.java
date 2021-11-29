package br.com.dock.bank_account_service.transaction.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TransactionRepository extends ReactiveCrudRepository<TransactionEntity, Long> {
}
