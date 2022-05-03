package com.pinksoft.bank.loan.adapter.out.persistence;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Component;

import com.pinksoft.bank.loan.application.port.in.ApplyLoanPort;
import com.pinksoft.bank.loan.application.port.in.LoadLoanPort;
import com.pinksoft.bank.loan.domain.Loan;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoanPersistenceAdapter implements LoadLoanPort, ApplyLoanPort {

    private final SpringLoanRepository loanRepository;
    private final LoanMapper loanMapper;

    @Override
    public Loan loadLoan(Long id) {
        LoanJpaEntity loanJpaEntity = loanRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return loanMapper.mapToDomainEntity(loanJpaEntity);
    }

    @Override
    public void applyLoan(Loan loanToSave) {
        loanRepository.save(loanMapper.mapToJpaEntity(loanToSave));
    }
}
