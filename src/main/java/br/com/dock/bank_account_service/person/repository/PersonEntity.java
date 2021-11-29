package br.com.dock.bank_account_service.person.repository;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Table
@Builder
public class PersonEntity {
    @Id
    private Long idPerson;
    private String name;
    private String documentation;
    private LocalDate birthday;
}