package com.pinksoft.bank.loan.application.service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.pinksoft.bank.loan.LoanConfigurationProperties;
import com.pinksoft.bank.loan.application.port.in.ApplyLoanCommand;
import com.pinksoft.bank.loan.application.port.in.ApplyLoanPort;
import com.pinksoft.bank.loan.application.port.in.LoadLoanCommand;
import com.pinksoft.bank.loan.application.port.in.LoadLoanPort;
import com.pinksoft.bank.loan.application.port.out.ApplyLoanUseCase;
import com.pinksoft.bank.loan.application.port.out.LoadLoanUseCase;
import com.pinksoft.bank.loan.domain.Loan;
import com.pinksoft.bank.loan.domain.LoanDecision;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanService implements LoadLoanUseCase, ApplyLoanUseCase {

    private final LoadLoanPort loadLoanPort;
    private final ApplyLoanPort applyLoanPort;
    private final LoanConfigurationProperties loanConfigurationProperties;
    private final Clock clock;


    @Override
    public Loan loadLoan(LoadLoanCommand command) {
        return loadLoanPort.loadLoan(command.getLoanId());
    }

    @Override
    public LoanDecision applyLoan(ApplyLoanCommand loanCommand) {

        BigDecimal amount = loanCommand.getAmount();
        Loan loan = Loan.toSave(amount, getLoanDecision(amount), loanConfigurationProperties.getInterest());

        applyLoanPort.applyLoan(loan);

        return loan.getLoanDecision();
    }

    private LoanDecision getLoanDecision(BigDecimal amount) {
        LocalTime localTime = LocalTime.now(clock);
        LocalTime calculateTimeOff = loanConfigurationProperties.getCalculateTimeOff();
        BigDecimal maxAmount = loanConfigurationProperties.getMaxAmount();
        LocalTime calculateTimeOn = loanConfigurationProperties.getCalculateTimeOn();

        if (amount.compareTo(maxAmount) < 0 && localTime.isBefore(calculateTimeOn) && localTime.isAfter(calculateTimeOff)) {
            return LoanDecision.POSITIVE;
        }

        return LoanDecision.NEGATIVE;
    }
}




