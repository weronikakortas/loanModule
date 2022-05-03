package com.pinksoft.bank.loan.adapter.out.persistence


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql

import spock.lang.Specification

@DataJpaTest
class SpringLoanRepositoryTestResponse extends Specification {

    @Autowired
    private SpringLoanRepository springLoanRepository

    @Sql(scripts = "classpath:load/LoadLoanSystemTest.sql")
    def "find loan"() {
        when: "find loan"
        Optional<LoanJpaEntity> loan = springLoanRepository.findById(id)

        then: "check if data are correct"
        loan.isPresent() == expectedValue

        where:
        id | expectedValue
        2L | true
        5L | false
    }

    def "save loan"() {
        when: "save loan"
        springLoanRepository.save(LoanJpaEntity.builder()
                .lendAmount(20)
                .build())

        then: "check if records in table loan increase"
        springLoanRepository.findAll().size() == 1

    }
}
