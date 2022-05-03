package com.pinksoft.bank.loan.adapter.in.web

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

import com.fasterxml.jackson.databind.ObjectMapper
import com.pinksoft.bank.loan.application.port.in.ApplyLoanCommand
import com.pinksoft.bank.loan.application.service.LoanService
import com.pinksoft.bank.loan.domain.LoanDecision

import spock.lang.Specification

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = LoanEndpoint.class)
class LoanEndpointTest extends Specification {

    @Autowired
    private MockMvc mvc

    @Mock
    LoanService loanService


    def "apply positive path"() {
        given:
        ApplyLoanCommand applyLoanCommand = new ApplyLoanCommand(new BigDecimal(2))
        loanService.applyLoan(applyLoanCommand) >> LoanDecision.NEGATIVE

        when:
        def results = mvc.perform(post('/loan/')
                .content(asJsonString(new ApplyLoanRequest(new BigDecimal(2))))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

        then:
        results.andExpect(status().isOk())
                .andExpect(jsonPath('$.amount').exists())
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
