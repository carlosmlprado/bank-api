package com.ing.bank.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ing.bank.api.dto.transaction.SentAndReceivedMoneyResponseDTO;
import com.ing.bank.api.dto.transaction.TransactionDTO;
import com.ing.bank.api.entity.CustomerEntity;
import com.ing.bank.api.repository.CustomerRepository;
import com.ing.bank.api.repository.TransactionRepository;
import com.ing.bank.api.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;
import java.util.Optional;

import static com.fasterxml.jackson.databind.SerializationFeature.WRAP_ROOT_VALUE;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    public static final String BASE_URL = "http://localhost:8080/api/transaction";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    void whenCreatingNewTransactionShouldReturnCreated() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(mockToDTO());

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/createTransaction")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is(HttpStatus.CREATED.value()));;

    }

    @Test
    void whenPassingAValidUserIdShouldReturnOk() throws Exception {
        when(transactionService.getMoneyReceivedAndSpentInTransactionsByCustomerId(1L)).thenReturn(mockSendAndReceivedMOneyToDTO());
//        when(customerRepository.findById(1L)).thenReturn();

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
    void whenPassingAnInvalidUserIdShouldReturnNotFound() throws Exception {

        when(transactionService.getMoneyReceivedAndSpentInTransactionsByCustomerId(1L)).thenReturn(mockSendAndReceivedMOneyToDTO());
        when(customerRepository.findById(1000L)).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder request = get(BASE_URL + "/moneyReceivedAndSentInTransactions/1000");

        mockMvc.perform(request)
                .andExpect(jsonPath("$.moneySent")
                        .value("0"))
                .andExpect(jsonPath("$.moneyReceived")
                        .value("0"))
                .andExpect(jsonPath("$.total")
                        .value("0"))
                .andExpect(jsonPath("$.customerName")
                        .value("test"))
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

    private SentAndReceivedMoneyResponseDTO mockSendAndReceivedMOneyToDTO(){

        return SentAndReceivedMoneyResponseDTO.builder().
                moneyReceived(100.65F).
                moneySent(80.4F).
                total(20.25F).
                customerName("test").build();

    }
}