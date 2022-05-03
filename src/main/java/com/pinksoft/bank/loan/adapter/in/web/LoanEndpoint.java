package com.pinksoft.bank.loan.adapter.in.web;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinksoft.bank.loan.application.port.in.ApplyLoanCommand;
import com.pinksoft.bank.loan.application.port.in.LoadLoanCommand;
import com.pinksoft.bank.loan.application.port.out.ApplyLoanUseCase;
import com.pinksoft.bank.loan.application.port.out.LoadLoanUseCase;
import com.pinksoft.bank.loan.domain.Loan;
import com.pinksoft.bank.loan.domain.LoanDecision;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
class LoanEndpoint {

    @Qualifier("loanService")
    private final LoadLoanUseCase loadLoanUseCase;

    @Qualifier("loanService")
    private final ApplyLoanUseCase applyLoanUseCase;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    LoanResponse loanInformation(@RequestBody final LoadLoanRequest loadLoanRequest) {
        LoadLoanCommand loadLoanCommand = new LoadLoanCommand(loadLoanRequest.getId());
        Loan loan = loadLoanUseCase.loadLoan(loadLoanCommand);
        return new LoanResponse(loan.getAmount(), loan.getLoanDecision());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    LoanResponse apply(@RequestBody final ApplyLoanRequest applyLoanRequest) {
        ApplyLoanCommand applyLoanCommand = new ApplyLoanCommand(applyLoanRequest.getAmount());
        LoanDecision loanDecision = applyLoanUseCase.applyLoan(applyLoanCommand);
        return new LoanResponse(applyLoanRequest.getAmount(), loanDecision);
    }

}
