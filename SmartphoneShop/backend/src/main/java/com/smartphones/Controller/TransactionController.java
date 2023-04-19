package com.smartphones.Controller;

import com.smartphones.Model.Transaction;
import com.smartphones.Service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController{
    @Autowired
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTransactionById(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.getById(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Object> getAllTransactions(){
        return new ResponseEntity<>(transactionService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addTransaction(@Valid @RequestBody Transaction newTransaction, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        transactionService.add(newTransaction);
        return new ResponseEntity<>("Transaction added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTransaction(@Valid @RequestBody Transaction newTransaction, @PathVariable Long id,
                                                    BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        transactionService.update(newTransaction, id);
        return new ResponseEntity<>("Transaction updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long id){
        transactionService.delete(id);
        return new ResponseEntity<>("Transaction deleted succcessfully", HttpStatus.OK);
    }

    @GetMapping("/getAllCustomersOrderedDescByTotalPriceOfBoughtSmartphones")
    public ResponseEntity<Object> getAllCustomersOrderedDescByTotalPriceOfBoughtSmartphones(){
        return new ResponseEntity<>(transactionService.getAllCustomersOrderedDescByTotalPriceOfBoughtSmartphones(), HttpStatus.OK);
    }

    @GetMapping("/getAllCustomersBornBeforeSpecificDateAndSortBoughtSmartphonesByStorageCapacity")
    public ResponseEntity<Object> getAllCustomersBornBeforeSpecificDateAndSortBoughtSmartphonesByStorageCapacity(@RequestParam(value = "dob") String dob){
        return new ResponseEntity<>(transactionService.getAllCustomersBornBeforeSpecificDateAndSortBoughtSmartphonesByStorageCapacity(dob), HttpStatus.OK);
    }
}
