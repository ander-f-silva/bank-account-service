package br.com.dock.bank_account_service.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountStatement {

    private List<Transaction> transactions;

    public enum EventType {
        DEPOSIT, WITHDRAW
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Transaction {
        private Long id;
        private Double amount;
        private EventType event;
        private LocalDate createdDate;
    }
}
