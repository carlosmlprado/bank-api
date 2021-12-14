package com.ing.bank.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.bank.api.dto.address.AddressDTO;
import com.ing.bank.api.dto.bankaccount.BankAccountDTO;
import com.ing.bank.api.dto.bankaccount.BankAccountResponseDTO;
import com.ing.bank.api.dto.customer.CustomerDTO;
import com.ing.bank.api.entity.BankAccountEntity;
import com.ing.bank.api.entity.CustomerEntity;
import com.ing.bank.api.repository.BankAccountRepository;
import com.ing.bank.api.service.impl.BankAccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BankAccountController.class)
class BankAccountControllerTest {

    public static final String BASE_URL = "http://localhost:8080/api/account";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankAccountRepository bankAccountRepository;

    @MockBean
    BankAccountServiceImpl bankAccountService;

    @Test
    void when_creating_new_account_and_resp_is_success_should_return_created() throws Exception {
        when(bankAccountService.createAccount(any())).thenReturn("Success creating account");

        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(mockBankAccountDTO());

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/createAccount")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    void when_creating_new_account_and_resp_is_error_should_return_internal_server_error() throws Exception {
        when(bankAccountService.createAccount(any())).thenReturn("error");

        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(mockBankAccountDTO());

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/createAccount")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @Test
    void when_creating_new_account_invalid_request_should_return_bad_request_case_1() throws Exception {
        when(bankAccountService.createAccount(any())).thenReturn("We can only create Private Account for 1 different customer at time");

        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(mockBankAccountDTO());

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/createAccount")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void when_creating_new_account_invalid_request_should_return_bad_request_case_2() throws Exception {
        when(bankAccountService.createAccount(any())).thenReturn("We can only create Joint Accounts for 2 different customers at time");

        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(mockBankAccountDTO());

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/createAccount")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void when_passing_valid_customer_id_to_delete_customer_bank_account_then_should_return_no_content() throws Exception {
        when(bankAccountService.deleteBankAccountsByCustomerId(10000L)).thenReturn(HttpStatus.NO_CONTENT);

        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/deleteAccounts/10000")).
                andExpect(status().isNoContent());
    }

    @Test
    void when_passing_valid_customer_id_to_list_customer_account_should_return_ok() throws Exception {
        when(bankAccountRepository.listCustomerAccounts(129L)).thenReturn(singletonList(mockBankAccountEntity()));

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
    void when_passing_invalid_customer_id_to_list_customer_account_should_return_ok() throws Exception {
        when(bankAccountRepository.listCustomerAccounts(129L)).thenReturn(singletonList(mockBankAccountEntity()));

        MockHttpServletRequestBuilder request = get(BASE_URL + "/listCustomerAccount/130");

        mockMvc.perform(request)
                .andExpect(jsonPath("$[0].iban")
                        .doesNotExist())
                .andExpect(jsonPath("$[0].type")
                        .doesNotExist())
                .andExpect(jsonPath("$[0].bank")
                        .doesNotExist())
                .andExpect(jsonPath("$[0].status")
                        .doesNotExist())
                .andExpect(status().isOk());
    }

    private BankAccountResponseDTO mockCustomerDTO() {
        return BankAccountResponseDTO.builder().
                iban("IN12 3456 7890 1234").
                type("private").
                bank("IBN").
                status("Active").build();

    }

    private BankAccountEntity mockBankAccountEntity() {
        List<CustomerEntity> listCustomers = new ArrayList<>();

        return BankAccountEntity.builder().
                iban("IN12 3456 7890 1234").
                type("private").
                bank("IBN").
                status("Active").build();

    }

    private List<CustomerDTO> mockCustomersDTO() {
        var customers = new ArrayList<CustomerDTO>();
        var customer = new CustomerDTO();
        var address = new AddressDTO();

        address = address.builder().
                city("Amsterdam").
                number("180").
                postCode("7523FR").
                street("SomethingStraat").
                build();

        customer = customer.builder().
                bsn("123456").
                name("Pedro").
                address(address).
                build();
        customers.add(customer);

        customer = new CustomerDTO();
        customer = customer.builder().
                bsn("456123").
                name("Joaquim").
                address(address)
                .build();
        customers.add(customer);

        return customers;

    }

    private BankAccountDTO mockBankAccountDTO() {
        var bankAccountDTO = new BankAccountDTO();

        return bankAccountDTO.builder().
                bank("IBN").
                openSince(Calendar.getInstance().getTime()).
                owners(mockCustomersDTO()).
                status("ACTIVE").
                type("Joint-Account")
                .build();
    }

}