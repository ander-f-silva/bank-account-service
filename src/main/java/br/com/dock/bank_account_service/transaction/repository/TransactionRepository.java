package br.com.dock.bank_account_service.transaction.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
    @Override
    @Modifying
    @Query("delete from Transaction")
    void deleteAll();
}
