package com.pinksoft.bank.loan.adapter.out.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;

import com.pinksoft.bank.loan.domain.LoanDecision;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loan")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class LoanJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @LastModifiedDate
    @Column(name = "changed_ts")
    private LocalDateTime modificationTime;

    @Column
    private BigDecimal lendAmount;

    @Column
    private Integer interest;

    @Column
    private BigDecimal amountToPay;

    @Column
    private LocalDate dueDate;

    @Column
    @Enumerated(EnumType.STRING)
    private LoanDecision loanDecision;
}
