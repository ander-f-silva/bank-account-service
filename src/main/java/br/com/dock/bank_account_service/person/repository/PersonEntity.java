package br.com.dock.bank_account_service.person.repository;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Table("Person")
@Builder
public class PersonEntity {
    @Id
    @Column("idPerson")
    private Long idPerson;
    @Column("name")
    private String name;
    @Column("document")
    private String document;
    @Column("birthday")
    private LocalDate birthday;
}