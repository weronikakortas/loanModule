package com.pinksoft.bank.loan.application.service

import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.zone.ZoneRules

import javax.validation.ClockProvider

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension

import com.pinksoft.bank.loan.LoanConfigurationProperties
import com.pinksoft.bank.loan.application.port.in.ApplyLoanCommand
import com.pinksoft.bank.loan.application.port.in.ApplyLoanPort
import com.pinksoft.bank.loan.application.port.in.LoadLoanPort
import com.pinksoft.bank.loan.domain.Loan
import com.pinksoft.bank.loan.domain.LoanDecision

import spock.lang.Specification
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

import spock.lang.Subject
import spock.mock.DetachedMockFactory

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [
//    FixingClockConfiguration.class, LoanService.class])
//@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(properties="spring.main.allow-bean-definition-overriding=true")
class LoanServiceTest extends Specification {

    private static Instant REFERENCE_DATE_TIME_WHEN_CALCULATION_OFF = LocalDateTime.of(2022, 4, 1, 1, 0)
            .atZone(ZoneId.of("UTC"))
            .toInstant()

    private static Instant REFERENCE_DATE_TIME_WHEN_CALCULATION_ON = LocalDateTime.of(2018, 4, 1, 9, 0)
            .atZone(ZoneId.of("UTC"))
            .toInstant()

    @MockBean
    ApplyLoanPort applyLoanPort

    @MockBean
    LoadLoanPort loadLoanPort

    Clock clock = Mock() {
        instant() >> Clock.fixed(Instant.parse("2020-03-22T01:15:30.00Z"), ZoneId.of("UTC"));
    }

    LoanConfigurationProperties loanConfigurationProperties = Mock() {
        getCalculateTimeOff() >> LocalTime.of(4, 30)
        getCalculateTimeOn() >> LocalTime.of(0, 30)
        getMaxAmount() >> new BigDecimal(3000)
    }

    @Autowired
    LoanService loanService = new LoanService(loadLoanPort, applyLoanPort, loanConfigurationProperties, clock)

    def "LoadLoan"() {
    }

    def "ApplyLoan with #description"() {
        given:
        ApplyLoanCommand applyLoanCommand = new ApplyLoanCommand(amount)
        clock.instant() >> dateTime

        when:
        LoanDecision loanDecision = loanService.applyLoan(applyLoanCommand)

        then:
        loanDecision == expectedDecision

        where:
        amount | expectedDecision | dateTime | description
        BigDecimal.TEN | LoanDecision.POSITIVE | REFERENCE_DATE_TIME_WHEN_CALCULATION_ON | 'correct amount when calculation on'
        BigDecimal.valueOf(7000) | LoanDecision.NEGATIVE | REFERENCE_DATE_TIME_WHEN_CALCULATION_ON | 'wrong amount when calculation on'
        BigDecimal.valueOf(6000) | LoanDecision.NEGATIVE | REFERENCE_DATE_TIME_WHEN_CALCULATION_OFF | 'wrong amount when calculation off'
        BigDecimal.valueOf(1000) | LoanDecision.POSITIVE | REFERENCE_DATE_TIME_WHEN_CALCULATION_OFF | 'correct amount when calculation off'
    }

}
