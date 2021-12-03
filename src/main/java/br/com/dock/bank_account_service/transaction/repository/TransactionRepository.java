package br.com.dock.bank_account_service.transaction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<TransactionEntity, Long> {
    @Override
    @Modifying
    @Query("delete from Transaction")
    void deleteAll();

    @Query("select sum(amount) from Transaction where idAccount = :idAccount and createdAt = :createdAt")
    Double sumTransactionInDay(@Param("idAccount") Long idAccount, @Param("createdAt") LocalDate createdAt);

    Page<TransactionEntity> findAllByIdAccount(Long accountId, Pageable pageable);
}
