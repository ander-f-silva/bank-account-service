package br.com.dock.bank_account_service.person.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
    @Override
    @Modifying
    @Query("delete from Person")
    void deleteAll();

    boolean existsByDocument(String document);
}
