package com.pinksoft.bank.loan.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.pinksoft.bank.loan.domain.Loan;

@Component
public class LoanMapper {

    Loan mapToDomainEntity(LoanJpaEntity loanJpaEntity) {
        return Loan.withLoanData(
                loanJpaEntity.getLendAmount(),
                loanJpaEntity.getDueDate());
    }

    LoanJpaEntity mapToJpaEntity(Loan loan) {
        return LoanJpaEntity.builder()
                .lendAmount(loan.getAmount())
                .dueDate(loan.getDueDate())
                .loanDecision(loan.getLoanDecision())
                .amountToPay(loan.getAmountToPay())
                .interest(loan.getInterest())
                .build();
    }

}
