package com.lab1gabarsolon.Service;
//1079
import com.lab1gabarsolon.Model.*;
import com.lab1gabarsolon.Repository.CustomerRepository;
import com.lab1gabarsolon.Repository.SmartphoneRepository;
import com.lab1gabarsolon.Repository.TransactionRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionService extends EntityService<Transaction>{

    public TransactionService(TransactionRepository repository) {
        super(repository);
    }

    public void update(Transaction newTransaction, Long id){
        repository.findById(id).map(transaction -> {
            transaction.setCustomer(newTransaction.getCustomer());
            transaction.setSmartphone(newTransaction.getSmartphone());
            transaction.setDateTime(newTransaction.getDateTime());
            transaction.setQuantity(newTransaction.getQuantity());
            return repository.save(transaction);
        }).orElseGet(()->{
            newTransaction.setId(id);
            return repository.save(newTransaction);
        });
    }

    public List<CustomerTotalPriceDto> getAllCustomersOrderedDescByTotalPriceOfBoughtSmartphones(){
        Map<Customer, BigDecimal> customerTotalPrice = repository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getCustomer,
                        Collectors.mapping(t -> t.getSmartphone().getPrice().multiply(new BigDecimal(t.getQuantity())), Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        return customerTotalPrice.entrySet().stream().map(e->{
            Customer currentCustomer = e.getKey();
            return new CustomerTotalPriceDto(currentCustomer.getFirstName(), currentCustomer.getLastName(),
                    currentCustomer.getPhoneNumber(), currentCustomer.getDateOfBirth(), currentCustomer.getEmail(),
                    e.getValue());})
                .sorted(Comparator.comparing(CustomerTotalPriceDto::getTotalPrice, Comparator.reverseOrder()))
                .collect(Collectors.toList());

    }

    public List<TransactionDTO> getAllCustomersBornBeforeSpecificDateAndSortBoughtSmartphonesByStorageCapacity(String dob){
        LocalDate localDate = LocalDate.parse(dob);
        Map<Customer, List<SmartphoneDTO>> customerBornBeforeGivenDob = new HashMap<>();
        repository.findAll().stream().forEach(transaction -> {
            Customer customer = transaction.getCustomer();
            if(customer.getDateOfBirth().isBefore(localDate))
            {
                SmartphoneDTO smartphoneDTO = new SmartphoneDTO(transaction.getSmartphone().getBrand(),
                        transaction.getSmartphone().getModel(),transaction.getSmartphone().getStorageCapacity());
                if(!customerBornBeforeGivenDob.containsKey(customer)){
                    List<SmartphoneDTO> itemList = new ArrayList<>();
                    itemList.add(smartphoneDTO);
                    customerBornBeforeGivenDob.put(customer, itemList);
                }
                  else{
                    customerBornBeforeGivenDob.get(customer).add(smartphoneDTO);
                }
            }
        });



        List<TransactionDTO> transactionDTOS = customerBornBeforeGivenDob.entrySet().stream().map(e->{
            Customer currentCustomer = e.getKey();
            return new TransactionDTO(currentCustomer.getFirstName(), currentCustomer.getLastName(),
                    currentCustomer.getDateOfBirth(), e.getValue());}).collect(Collectors.toList());

        transactionDTOS.forEach(transactionDTO -> {transactionDTO.getSmartphoneSet().sort(Comparator.comparing(SmartphoneDTO::getStorageCapacity));});
        transactionDTOS.sort(Comparator.comparing(TransactionDTO::getLastName));

        return transactionDTOS;
    }
}
