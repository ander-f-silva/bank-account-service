package br.com.dock.bank_account_service.transaction.repository;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Table("Transaction")
@Builder
public class TransactionEntity {
    @Id
    @Column("idTransaction")
    private Long idTransaction;
    @Column("idAccount")
    private Long idAccount;
    @Column("amount")
    private Double amount;
    @Column("createdAt")
    private LocalDate createdAt;
}
