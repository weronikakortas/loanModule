package com.pinksoft.bank.loan.application.port.in;

import com.pinksoft.bank.loan.domain.Loan;

public interface ApplyLoanPort {

    void applyLoan(Loan loanToSave);
}
