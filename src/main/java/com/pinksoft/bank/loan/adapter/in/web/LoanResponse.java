package com.pinksoft.bank.loan.adapter.in.web;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pinksoft.bank.loan.domain.LoanDecision;

import lombok.Getter;

public class LoanResponse {

    @NotNull
    @Getter
    private BigDecimal amount;

    @NotNull
    @Getter
    private LoanDecision loanDecision;

    @JsonCreator
    public LoanResponse(@JsonProperty("amount") @NotNull final BigDecimal amount,
            @JsonProperty("loanDecision") @NotNull final LoanDecision loanDecision) {
        this.loanDecision = loanDecision;
        this.amount = amount;
    }

}
