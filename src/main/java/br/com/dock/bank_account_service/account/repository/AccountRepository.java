package br.com.dock.bank_account_service.account.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
    @Modifying
    @Query("update Account set flagActive = :flagActive WHERE idAccount = :idAccount")
    boolean updateFlagActive(@Param("flagActive") Boolean flagActive, @Param("idAccount") Long idAccount);
}
