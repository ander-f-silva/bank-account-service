package br.com.dock.bank_account_service.account.repository;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Table
@Builder
public class AccountEntity {
    @Id
    private Long idAccount;
    private Long idPerson;
    private Double balance;
    private Double dayLimit;
    private Boolean flagActive;
    private LocalDate createdAt;
}
