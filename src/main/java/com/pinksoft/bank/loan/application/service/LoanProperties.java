package com.pinksoft.bank.loan.application.service;

import java.math.BigDecimal;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanProperties {

    private Integer interest = Integer.MAX_VALUE;
    private LocalTime calculateTimeOn = LocalTime.now();
    private LocalTime calculateTimeOff = LocalTime.now();
    private BigDecimal maxAmount = BigDecimal.ZERO;

}
