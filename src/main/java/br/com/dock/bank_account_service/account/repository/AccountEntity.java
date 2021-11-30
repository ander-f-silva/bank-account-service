package br.com.dock.bank_account_service.account.repository;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Table("Account")
@Builder
public class AccountEntity {
    @Id
    @Column("idAccount")
    private Long idAccount;
    @Column("idPerson")
    private Long idPerson;
    @Column("balance")
    private Double balance;
    @Column("withdrawalDayLimit")
    private Double withdrawalDayLimit;
    @Column("flagActive")
    private Boolean flagActive;
    @Column("accountType")
    private Integer accountType;
    @Column("createdAt")
    private LocalDate createdAt;
}
