package br.com.dock.bank_account_service.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
    private Long id;
    private Double amount;
    private EventType event;
    private LocalDate createdAt;
}
