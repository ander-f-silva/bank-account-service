package br.com.dock.bank_account_service.account.service;

import br.com.dock.bank_account_service.account.dto.AccountStatement;
import br.com.dock.bank_account_service.account.expection.AccountNotFoundException;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import br.com.dock.bank_account_service.transaction.dto.EventType;
import br.com.dock.bank_account_service.transaction.dto.Transaction;
import br.com.dock.bank_account_service.transaction.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class GettingAccountStatement implements GetAccountStatement {
    private static final Logger logger = LoggerFactory.getLogger(GettingAccountStatement.class);

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    @Override
    @Transactional(readOnly = true)
    public AccountStatement findByAccountId(Long accountId, Integer page, Integer size) {
       accountRepository.findById(accountId)
                .filter(accountEntity -> accountEntity.getFlagActive().equals(false))
                .orElseThrow(() -> {
                    logger.error("[event: Get Account Statement][param path: (accountId:{})] Account not found", accountId);
                    return new AccountNotFoundException();
                });

        PageRequest pageable = PageRequest.of((page -1), size, Sort.by("createdAt").descending());

        var transactions = transactionRepository.findAllByIdAccount(accountId, pageable)
                .stream()
                .map(
                        transaction -> {
                            var eventType = (transaction.getAmount() < 0 ? EventType.WITHDRAW : EventType.DEPOSIT);
                            return new Transaction(
                                    transaction.getIdTransaction(),
                                    transaction.getAmount(),
                                    eventType,
                                    transaction.getCreatedAt());
                        }
                ).collect(Collectors.toList());

        return new AccountStatement(transactions);
    }
}
