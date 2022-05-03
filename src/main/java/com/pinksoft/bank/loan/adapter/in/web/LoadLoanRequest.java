package com.pinksoft.bank.loan.adapter.in.web;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

public class LoadLoanRequest {

    @NotNull
    @Getter
    private Long id;

    @JsonCreator
    public LoadLoanRequest(@JsonProperty("id") @NotNull final Long id) {
        this.id = id;
    }

}
