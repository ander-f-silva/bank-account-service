package br.com.dock.bank_account_service.account.dto;

import java.util.Arrays;

public enum AccountType {
    CHECKING_ACCOUNT(1);

    private final Integer valueNumber;

    AccountType(Integer valueNumber) {
        this.valueNumber = valueNumber;
    }

    public Integer getValueNumber() {
        return valueNumber;
    }

    public AccountType getValue(Integer valueNumber) {
        return Arrays
                .stream(AccountType.values())
                .filter(accountType -> accountType.valueNumber.equals(valueNumber))
                .findFirst()
                .get();
    }
}