package com.smartphones;

import com.smartphones.DTO.CustomerTotalPriceDto;
import com.smartphones.Model.*;
import com.smartphones.Repository.TransactionRepository;
import com.smartphones.Service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private TransactionService transactionService;
    @Test
    public void givenTransactions_getAllCustomersOrderedDescByTotalPriceOfBoughtSmartphones_thenReturnRequiredCustomers(){
        Display d1 = new Display("type", 5.1, 1, 1, "protection");

        Smartphone s1 = new Smartphone("brand1", "model1", new BigDecimal(100),
                64, LocalDate.parse("2022-02-02"), d1, "");
        Smartphone s2 = new Smartphone("brand2", "model2", new BigDecimal(200),
                64, LocalDate.parse("2022-02-02"), d1, "");
        Smartphone s3 = new Smartphone("brand3", "model3", new BigDecimal(300),
                64, LocalDate.parse("2022-02-02"), d1, "");

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

        CustomerTotalPriceDto cDTO1 = new CustomerTotalPriceDto(0L, c1.getFirstName(), c1.getLastName(), c1.getPhoneNumber(),
                 new BigDecimal(300));
        CustomerTotalPriceDto cDTO2 = new CustomerTotalPriceDto(1L, c2.getFirstName(), c2.getLastName(), c2.getPhoneNumber(),
                 new BigDecimal(500));
        CustomerTotalPriceDto cDTO3 = new CustomerTotalPriceDto(2L, c3.getFirstName(), c3.getLastName(), c3.getPhoneNumber(),
                 new BigDecimal(2000));

        List<Transaction> transactionList = Arrays.asList(t1, t2, t3, t4, t5);
        when(transactionRepository.findAll()).thenReturn(transactionList);

        List<CustomerTotalPriceDto> customerTotalPriceDtoList =
                transactionService.getAllCustomersOrderedDescByTotalPriceOfBoughtSmartphones(10);

        assertThat(customerTotalPriceDtoList.get(0).getFirstName()).isEqualTo(cDTO3.getFirstName());
        assertThat(customerTotalPriceDtoList.get(1).getFirstName()).isEqualTo(cDTO2.getFirstName());
        assertThat(customerTotalPriceDtoList.get(2).getFirstName()).isEqualTo(cDTO1.getFirstName());
    }
}
