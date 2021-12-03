package br.com.dock.bank_account_service.account.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
    @Modifying
    @Query("update Account set flagActive = :flagActive where idAccount = :idAccount")
    boolean updateFlagActiveByAccountId(@Param("flagActive") Boolean flagActive, @Param("idAccount") Long idAccount);

    @Modifying
    @Query("update Account set balance = :balance where idAccount = :idAccount")
    boolean updateBalanceByAccountId(@Param("balance") Double balance, @Param("idAccount") Long idAccount);

    @Override
    @Modifying
    @Query("delete from Account")
    void deleteAll();
}
