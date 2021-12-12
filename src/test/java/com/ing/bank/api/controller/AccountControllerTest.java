package com.ing.bank.api.controller;

import com.ing.bank.api.dto.BankAccountResponseDTO;
import com.ing.bank.api.entity.BankAccountEntity;
import com.ing.bank.api.repository.BankAccountRepository;
import com.ing.bank.api.service.BankAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BankAccountController.class)
class AccountControllerTest {

    public static final String BASE_URL = "http://localhost:8080/api/account";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankAccountRepository bankAccountRepository;

    @MockBean
    private BankAccountService bankAccountService;

    @Test
    void createAccount() {
    }


    @Test
    void whenNotPassingParameterToListCustomerAccountThenShouldReturnBadRequest() throws Exception {
        MockHttpServletRequestBuilder request = get(BASE_URL + "/listCustomerAccount/");

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    void aa() throws Exception {
        when(bankAccountRepository.listCustomerAccounts(129L)).thenReturn(singletonList(mockCustomerEntity()));

        MockHttpServletRequestBuilder request = get(BASE_URL + "/listCustomerAccount/129");

        mockMvc.perform(request)
                .andExpect(jsonPath("$[0].iban")
                        .value("IN12 3456 7890 1234"))
                .andExpect(jsonPath("$[0].type")
                        .value("private"))
                .andExpect(jsonPath("$[0].bank")
                        .value("IBN"))
                .andExpect(jsonPath("$[0].status")
                        .value("Active"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAccounts() {

    }

    private BankAccountResponseDTO mockCustomerDTO() {
        return BankAccountResponseDTO.builder().
                iban("IN12 3456 7890 1234").
                type("private").
                bank("IBN").
                status("Active").build();

    }

    private BankAccountEntity mockCustomerEntity() {
        return BankAccountEntity.builder().
                iban("IN12 3456 7890 1234").
                type("private").
                bank("IBN").
                status("Active").build();

    }
}