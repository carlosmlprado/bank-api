package com.ing.bank.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.bank.api.dto.transaction.SentAndReceivedMoneyResponseDTO;
import com.ing.bank.api.dto.transaction.TransactionDTO;
import com.ing.bank.api.repository.CustomerRepository;
import com.ing.bank.api.service.impl.TransactionServiceImpl;
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

import java.util.Calendar;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    public static final String BASE_URL = "http://localhost:8080/api/transaction";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionServiceImpl transactionService;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void when_creating_new_transaction_and_resp_is_valid_should_return_created() throws Exception {
        when(transactionService.createTransaction(any())).thenReturn("Transaction created with status Valid");

        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(mockToDTO());

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/createTransaction")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    void when_creating_new_transaction_and_resp_is_error_should_return_internal_server_error() throws Exception {
        when(transactionService.createTransaction(any())).thenReturn("error");

        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(mockToDTO());

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/createTransaction")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @Test
    void when_creating_new_transaction_and_resp_is_invalid_should_return_created() throws Exception {
        when(transactionService.createTransaction(any())).thenReturn("Transaction created with status Invalid");

        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(mockToDTO());

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/createTransaction")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    void when_passing_a_valid_user_id_should_return_ok() throws Exception {
        when(transactionService.getMoneyReceivedAndSpentInTransactionsByCustomerId(1L)).thenReturn(mockSendAndReceivedMOneyToDTO());

        MockHttpServletRequestBuilder request = get(BASE_URL + "/moneyReceivedAndSentInTransactions/1");

        mockMvc.perform(request)
                .andExpect(jsonPath("$.moneySent")
                        .value("80.4"))
                .andExpect(jsonPath("$.moneyReceived")
                        .value("100.65"))
                .andExpect(jsonPath("$.total")
                        .value("20.25"))
                .andExpect(jsonPath("$.customerName")
                        .value("test"))
                .andExpect(status().isOk());
    }

    @Test
    void when_passing_an_invalid_user_id_should_return_not_found() throws Exception {

        when(transactionService.getMoneyReceivedAndSpentInTransactionsByCustomerId(1L)).thenReturn(mockSendAndReceivedMOneyToDTO());
        when(customerRepository.findById(1000L)).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder request = get(BASE_URL + "/moneyReceivedAndSentInTransactions/1000");

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    private TransactionDTO mockToDTO() {

        return TransactionDTO.builder().
                amount(1800F).
                destinationAccount("IN85897212042751").
                originBank("ING").
                originAccount("IN43775419638953").
                originName("Pedro").
                destinationBank("ABN").
                destinationName("Joaquim").
                transactionDate(Calendar.getInstance().getTime()).build();
    }

    private SentAndReceivedMoneyResponseDTO mockSendAndReceivedMOneyToDTO() {

        return SentAndReceivedMoneyResponseDTO.builder().
                moneyReceived(100.65F).
                moneySent(80.4F).
                total(20.25F).
                customerName("test").build();

    }
}