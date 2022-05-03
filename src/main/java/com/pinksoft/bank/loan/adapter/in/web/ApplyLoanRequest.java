package com.pinksoft.bank.loan.adapter.in.web;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

public class ApplyLoanRequest {

    @NotNull
    @Getter
    private BigDecimal amount;

    @JsonCreator
    public ApplyLoanRequest(@JsonProperty("amount") @NotNull final BigDecimal amount) {
        this.amount = amount;
    }

}
