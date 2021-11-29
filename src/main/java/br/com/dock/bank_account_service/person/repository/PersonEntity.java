package br.com.dock.bank_account_service.person.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Table
@Builder
public class PersonEntity {
    @Id
    private Long idPerson;
    private String name;
    private String documentation;
    private LocalDate birthday;
}