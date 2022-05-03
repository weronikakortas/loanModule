package com.pinksoft.bank.loan.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpringLoanRepository extends JpaRepository<LoanJpaEntity, Long> {

}
