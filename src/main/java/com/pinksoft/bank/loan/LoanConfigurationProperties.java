package com.pinksoft.bank.loan;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "loan")
public class LoanConfigurationProperties {

  @Value("${interest}")
  private int interest;

  @Value("${maxAmount}")
  private BigDecimal maxAmount;

  @DateTimeFormat
  @Value("${calculateTimeOff}")
  private String calculateTimeOff;

  @DateTimeFormat
  @Value("${calculateTimeOn}")
  private String calculateTimeOn;

  public LocalTime getCalculateTimeOn() {
    return LocalTime.parse(calculateTimeOn);
  }

  public LocalTime getCalculateTimeOff() {
    return LocalTime.parse(calculateTimeOff);
  }

}
