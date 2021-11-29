package br.com.dock.bank_account_service.transaction.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Table
public class TransactionEntity {
    @Id
    private Long idAccount;
    private Long idPerson;
    private Double balance;
    private Double dayLimit;
    private Boolean flagActive;
    private LocalDate createdAt;
}
