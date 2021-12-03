package br.com.dock.bank_account_service.account.expection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class WithdrawLimitException extends RuntimeException {
}
