package com.smartphones.Service;
//1079
import com.smartphones.Model.*;
import com.smartphones.Repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
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

    public List<CustomerTotalPriceDto> getAllCustomersOrderedDescByTotalPriceOfBoughtSmartphones(Integer pageNumber){
            return ((TransactionRepository)repository).findAllCustomersOrderedDescByTotalPriceOfBoughtSmartphones(
                    PageRequest.of(pageNumber, 10)
            );
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
