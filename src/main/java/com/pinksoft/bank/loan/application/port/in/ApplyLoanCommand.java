package com.pinksoft.bank.loan.application.port.in;

import java.math.BigDecimal;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class ApplyLoanCommand {

    @NotNull
    @Positive
    private final BigDecimal amount;

}
