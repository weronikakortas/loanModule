package com.pinksoft.bank.loan.application.port.out;

import com.pinksoft.bank.loan.application.port.in.ApplyLoanCommand;
import com.pinksoft.bank.loan.domain.LoanDecision;

public interface ApplyLoanUseCase {

    LoanDecision applyLoan(ApplyLoanCommand loanCommand);

}
