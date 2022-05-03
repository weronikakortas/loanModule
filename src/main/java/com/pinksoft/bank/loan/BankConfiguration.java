package com.pinksoft.bank.loan;

import java.time.Clock;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pinksoft.bank.loan.application.service.LoanProperties;

@Configuration
@EnableConfigurationProperties(LoanConfigurationProperties.class)
public class BankConfiguration {

    @Bean
    public LoanProperties loanProperties(LoanConfigurationProperties loanConfigurationProperties) {
        return LoanProperties.builder()
                .interest(loanConfigurationProperties.getInterest())
                .maxAmount(loanConfigurationProperties.getMaxAmount())
                .calculateTimeOn(loanConfigurationProperties.getCalculateTimeOn())
                .calculateTimeOff(loanConfigurationProperties.getCalculateTimeOff())
                .build();
    }

    @Bean
    public Clock getClock() {
        return Clock.systemDefaultZone();
    }

}
