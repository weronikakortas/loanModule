package com.pinksoft.bank.loan.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Setter
public class Loan {

    @Getter
    BigDecimal amount;

    LocalDate dueDate;

    private Loan(BigDecimal amount, LoanDecision loanDecision, int interest) {
        this.amount = amount;
        this.loanDecision = loanDecision;
        this.interest = interest;
    }

    private Loan(BigDecimal amount, LoanDecision loanDecision) {
        this.amount = amount;
        this.loanDecision = loanDecision;
    }

    public LocalDate getDueDate() {
        return LocalDate.now().plusYears(3);
    }

    @Getter
    LoanDecision loanDecision;

    @Getter
    int interest;

    BigDecimal amountToPay;

    public BigDecimal getAmountToPay() {
        return amount.add(amount.multiply(BigDecimal.valueOf(interest).setScale(2)));
    }

    public Loan(BigDecimal amount, LocalDate dueDate) {
        this.amount = amount;
        this.dueDate = dueDate;
    }

    public static Loan withLoanData(
            BigDecimal amount,
            LocalDate dueDate) {
        return new Loan(amount, dueDate);
    }

    public static Loan toSave(
            BigDecimal amount,
            LoanDecision loanDecision,
            int interest) {
        return new Loan(amount, loanDecision, interest);
    }

    public static Loan responseSave(
            BigDecimal amount,
            LoanDecision loanDecision) {
        return new Loan(amount, loanDecision);
    }

}
