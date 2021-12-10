package com.ing.bank.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
class AccountControllerTest {

    public static final String BASE_URL = "http://localhost:8080/api/account";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createAccount() {
    }

    @Test
    void listCustomerAccount() {
    }

    @Test
    void deleteAccounts() {
    }
}