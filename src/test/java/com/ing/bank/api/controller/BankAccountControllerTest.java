package com.ing.bank.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ing.bank.api.dto.AddressDTO;
import com.ing.bank.api.dto.BankAccountDTO;
import com.ing.bank.api.dto.BankAccountResponseDTO;
import com.ing.bank.api.dto.CustomerDTO;
import com.ing.bank.api.entity.AddressEntity;
import com.ing.bank.api.entity.BankAccountEntity;
import com.ing.bank.api.entity.CustomerEntity;
import com.ing.bank.api.repository.BankAccountRepository;
import com.ing.bank.api.service.BankAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.fasterxml.jackson.databind.SerializationFeature.WRAP_ROOT_VALUE;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BankAccountController.class)
class BankAccountControllerTest {

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
    void whenCreatingNewAccountShouldReturnCreated() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(mockBankAccountDTO());

        MockHttpServletRequestBuilder request = post(BASE_URL + "/createAccount")
                .contentType(APPLICATION_JSON)
                .content(requestJson);

        mockMvc.perform(request)
                .andExpect(status().is(HttpStatus.CREATED.value()));

    }

    @Test
    void whenNotPassingParameterToListCustomerAccountThenShouldReturnBadRequest() throws Exception {
        MockHttpServletRequestBuilder request = get(BASE_URL + "/listCustomerAccount/");

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPassingValidParameterToListCustomerAccountShouldReturnOk() throws Exception {
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
    void whenPassingInValidParameterToListCustomerAccountShouldReturnOkButEmpty() throws Exception {
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

    @Test
    void whenDeletingValidAccountShouldReturnNoContent() throws Exception {
        MockHttpServletRequestBuilder request = delete(BASE_URL + "/deleteAccounts/{customerId}", 1L);

        mockMvc.perform(request)
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));
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
                type("joint_account").build();

    }


}