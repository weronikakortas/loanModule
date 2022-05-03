package com.pinksoft.bank.loan.application.port.out;

import com.pinksoft.bank.loan.application.port.in.LoadLoanCommand;
import com.pinksoft.bank.loan.domain.Loan;

public interface LoadLoanUseCase {

    Loan loadLoan(LoadLoanCommand loanCommand);

}
