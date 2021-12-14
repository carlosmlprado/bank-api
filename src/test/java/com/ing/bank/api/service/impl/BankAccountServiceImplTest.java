//package com.ing.bank.api.service.impl;
//
//import com.ing.bank.api.controller.BankAccountController;
//import com.ing.bank.api.dto.address.AddressDTO;
//import com.ing.bank.api.dto.bankaccount.BankAccountDTO;
//import com.ing.bank.api.dto.customer.CustomerDTO;
//import com.ing.bank.api.entity.AddressEntity;
//import com.ing.bank.api.entity.BankAccountEntity;
//import com.ing.bank.api.entity.CustomerEntity;
//import com.ing.bank.api.repository.BankAccountRepository;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(BankAccountServiceImpl.class)
//class BankAccountServiceImplTest {
//
//    @Mock
//    BankAccountRepository bankAccountRepository;
//
////    @Test
////    void when_creating_new_account_with_joint_account_and_two_customers_should_create() throws Exception {
////        BankAccountEntity bankAccountEntity = (mockBankAccountEntity("Joint-Account"));
////
////        bankAccountService.createAccount(mockBankAccountDTO("Joint-Account"));
////
////        verify(bankAccountRepository, times(1)).save(bankAccountEntity);
//
////    }
//
//
////    @Test
////    void when_creating_new_account_with_private_and_two_customers_should_not_create() throws Exception {
////        when(bankAccountService.createAccount(mockBankAccountDTO("Private"))).thenReturn("We can only create Private Account for 1 different customer at time");
////    }
////
////    @Test
////    void when_creating_new_account_with_private_and_one_customer_should_not_create() throws Exception {
////        when(bankAccountService.createAccount(mockPrivateBankAccountDTO("Private"))).thenReturn("Success creating account");
////
////        String res = bankAccountService.createAccount(mockPrivateBankAccountDTO("Private"));
////
////        assertThat(res).isEqualTo("Success creating account");
////    }
//
//    private BankAccountDTO mockBankAccountDTO(String type) {
//
//        return BankAccountDTO.builder().
//                bank("IBN").
//                openSince(Calendar.getInstance().getTime()).
//                owners(mockCustomersDTO()).
//                status("ACTIVE").
//                type(type)
//                .build();
//    }
//
//    private BankAccountDTO mockPrivateBankAccountDTO(String type) {
//
//        return BankAccountDTO.builder().
//                bank("IBN").
//                openSince(Calendar.getInstance().getTime()).
//                owners(mockCustomerDTO()).
//                status("ACTIVE").
//                type(type)
//                .build();
//    }
//
//
//    private List<CustomerDTO> mockCustomersDTO() {
//        var customers = new ArrayList<CustomerDTO>();
//        var customer = new CustomerDTO();
//        var address = new AddressDTO();
//
//        address = address.builder().
//                city("Amsterdam").
//                number("180").
//                postCode("7523FR").
//                street("SomethingStraat").
//                build();
//
//        customer = customer.builder().
//                bsn("123456").
//                name("Pedro").
//                address(address).
//                build();
//        customers.add(customer);
//
//        customer = new CustomerDTO();
//        customer = customer.builder().
//                bsn("456123").
//                name("Joaquim").
//                address(address)
//                .build();
//        customers.add(customer);
//
//        return customers;
//
//    }
//
//    private List<CustomerDTO> mockCustomerDTO() {
//        var customers = new ArrayList<CustomerDTO>();
//        var customer = new CustomerDTO();
//        var address = new AddressDTO();
//
//        address = address.builder().
//                city("Amsterdam").
//                number("180").
//                postCode("7523FR").
//                street("SomethingStraat").
//                build();
//
//        customer = customer.builder().
//                bsn("123456").
//                name("Pedro").
//                address(address).
//                build();
//        customers.add(customer);
//
//        return customers;
//
//    }
//
//    private BankAccountEntity mockBankAccountEntity(String type) {
//        var bankAccountEntity = new BankAccountEntity();
//
//        return bankAccountEntity.builder().
//                bank("IBN").
//                openedDate(Calendar.getInstance().getTime()).
//                customer(mockCustomerEntity().get(0)).
//                status("ACTIVE").
//                type(type)
//                .build();
//    }
//
//    private BankAccountEntity mockPrivateBankAccountEntity(String type) {
//        var bankAccountEntity = new BankAccountEntity();
//
//        return bankAccountEntity.builder().
//                bank("IBN").
//                openedDate(Calendar.getInstance().getTime()).
//                customer(mockCustomerEntity().get(0)).
//                status("ACTIVE").
//                type(type)
//                .build();
//    }
//
//
//    private List<CustomerEntity> mockCustomersEntity() {
//        var customers = new ArrayList<CustomerEntity>();
//        var customer = new CustomerEntity();
//        var address = new AddressEntity();
//
//        customer = customer.builder().
//                bsn("123456").
//                name("Pedro").
//                build();
//        customers.add(customer);
//
//        customer = new CustomerEntity();
//        customer = customer.builder().
//                bsn("456123").
//                name("Joaquim")
//                .build();
//        customers.add(customer);
//
//        return customers;
//
//    }
//
//    private List<CustomerEntity> mockCustomerEntity() {
//        var customers = new ArrayList<CustomerEntity>();
//        var customer = new CustomerEntity();
//        var address = new AddressEntity();
//
//        customer = customer.builder().
//                bsn("123456").
//                name("Pedro").
//                build();
//        customers.add(customer);
//
//        return customers;
//
//    }
//
//}