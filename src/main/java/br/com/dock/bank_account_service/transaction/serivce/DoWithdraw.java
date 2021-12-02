package br.com.dock.bank_account_service.transaction.serivce;

import br.com.dock.bank_account_service.transaction.dto.WithDraw;

public interface DoWithdraw {
    void apply(Long accountId, WithDraw withDraw);
}
