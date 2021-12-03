package br.com.dock.bank_account_service.transaction.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
    @Override
    @Modifying
    @Query("delete from Transaction")
    void deleteAll();

    @Query("select sum(amount) from Transaction where idAccount = :idAccount and createdAt = :createdAt")
    Double sumTransactionInDay(@Param("idAccount") Long idAccount, @Param("createdAt") LocalDate createdAt);

    @Query("select sum(amount) from Transaction where idAccount = :idAccount")
    Double sumTransactionInDay(@Param("idAccount") Long idAccount);
}
