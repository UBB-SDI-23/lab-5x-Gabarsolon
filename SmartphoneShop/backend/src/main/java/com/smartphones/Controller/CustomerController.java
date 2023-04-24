package com.smartphones.Controller;

import com.smartphones.Model.Customer;
import com.smartphones.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/byPage/{pageNumber}")
    public ResponseEntity<Object> getAllCustomers(@PathVariable Integer pageNumber){
        return new ResponseEntity<>(customerService.getAll(pageNumber), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Object> getCount(){
        return new ResponseEntity<>(customerService.getCount(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<>(customerService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<Object> getCustomersByFirstNameLastNameEmail(@RequestParam(name="query") String query){
        return new ResponseEntity<>(customerService.getCustomersByFirstNameLastNameEmail(
                URLDecoder.decode(query, StandardCharsets.UTF_8)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addCustomer(@Valid @RequestBody Customer newCustomer, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        customerService.add(newCustomer);
        return new ResponseEntity<>("Customer added succesfully", HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@Valid @RequestBody Customer newCustomer, @PathVariable Long id, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        customerService.update(newCustomer, id);
        return new ResponseEntity<>("Customer updated successfully", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id){
        customerService.delete(id);
        return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
    }
}
