package com.pinksoft.bank.loan.application.port.in;

import com.pinksoft.bank.loan.domain.Loan;

public interface LoadLoanPort {

    Loan loadLoan(Long id);
}
