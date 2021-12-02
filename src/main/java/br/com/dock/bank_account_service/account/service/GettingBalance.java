package br.com.dock.bank_account_service.account.service;

import br.com.dock.bank_account_service.account.dto.Amount;
import br.com.dock.bank_account_service.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
class GettingBalance implements GetBalance {
    private final AccountRepository accountRepository;

    /*
TODO:
Fazer a tratativa para caso de conta não econtrada
*/
    @Override
    public Amount findByAccountId(Long accountId) {
        var account =  accountRepository.findById(accountId)
                .orElseThrow(NoSuchElementException::new);

        return new Amount(account.getBalance());
    }
}
