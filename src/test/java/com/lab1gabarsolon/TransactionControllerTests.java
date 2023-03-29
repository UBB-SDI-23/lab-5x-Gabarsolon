package com.lab1gabarsolon;

import com.lab1gabarsolon.Controller.TransactionController;
import com.lab1gabarsolon.Model.*;
import com.lab1gabarsolon.Service.TransactionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hibernate.Hibernate.contains;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTests {
    private static List<CustomerTotalPriceDto> customerTotalPriceDtoList;
    @MockBean
    private TransactionService transactionService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void init(){
        Display d1 = new Display("type", 5.1, 1, 1, "protection");

        Smartphone s1 = new Smartphone("brand1", "model1", new BigDecimal(100),
                64, LocalDate.parse("2022-02-02"), d1);
        Smartphone s2 = new Smartphone("brand2", "model2", new BigDecimal(200),
                64, LocalDate.parse("2022-02-02"), d1);
        Smartphone s3 = new Smartphone("brand3", "model3", new BigDecimal(300),
                64, LocalDate.parse("2022-02-02"), d1);

        Customer c1 = new Customer("cust1", "omer1", "07 n-am cartela",
                LocalDate.parse("2022-02-02"), "email@mail.ro");
        Customer c2 = new Customer("cust2", "omer2", "07 n-am cartela",
                LocalDate.parse("2022-02-02"), "email@mail.ro");
        Customer c3 = new Customer("cust3", "omer3", "07 n-am cartela",
                LocalDate.parse("2022-02-02"), "email@mail.ro");

        Transaction t1 = new Transaction(c1, s1, 1, LocalDateTime.parse("2022-02-02T21:15"));
        Transaction t2 = new Transaction(c1, s2, 1, LocalDateTime.parse("2022-02-02T21:15"));
        Transaction t3 = new Transaction(c2, s2, 1, LocalDateTime.parse("2022-02-02T21:15"));
        Transaction t4 = new Transaction(c2, s3, 1, LocalDateTime.parse("2022-02-02T21:15"));
        Transaction t5 = new Transaction(c3, s2, 10, LocalDateTime.parse("2022-02-02T21:15"));

        CustomerTotalPriceDto cDTO1 = new CustomerTotalPriceDto(c1.getFirstName(), c1.getLastName(), c1.getPhoneNumber(),
                c1.getDateOfBirth(), c1.getEmail(), new BigDecimal(300));
        CustomerTotalPriceDto cDTO2 = new CustomerTotalPriceDto(c2.getFirstName(), c2.getLastName(), c2.getPhoneNumber(),
                c2.getDateOfBirth(), c2.getEmail(), new BigDecimal(500));
        CustomerTotalPriceDto cDTO3 = new CustomerTotalPriceDto(c3.getFirstName(), c3.getLastName(), c3.getPhoneNumber(),
                c3.getDateOfBirth(), c3.getEmail(), new BigDecimal(2000));

        customerTotalPriceDtoList = Arrays.asList(cDTO1, cDTO2, cDTO3);
    }

    @Test
    public void givenTransactions_getAllCustomersOrderedDescByTotalPriceOfBoughtSmartphones_thenReturnRequiredCustomers()
        throws Exception{

        when(transactionService.getAllCustomersOrderedDescByTotalPriceOfBoughtSmartphones()).thenReturn(customerTotalPriceDtoList);

        mockMvc.perform(get("/api/transaction/getAllCustomersOrderedDescByTotalPriceOfBoughtSmartphones"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("300")))
                .andExpect(content().string(containsString("500")))
                .andExpect(content().string(containsString("2000")));
    }
}
