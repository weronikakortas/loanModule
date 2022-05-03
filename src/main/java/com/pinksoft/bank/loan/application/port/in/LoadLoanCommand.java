package com.pinksoft.bank.loan.application.port.in;

import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class LoadLoanCommand {

    @NotNull
    private final Long loanId;

}
