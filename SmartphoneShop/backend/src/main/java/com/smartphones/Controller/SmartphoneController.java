package com.smartphones.Controller;

import com.smartphones.Model.Smartphone;
import com.smartphones.Service.SmartphoneService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/smartphone")
public class SmartphoneController {
    @Autowired
    private SmartphoneService smartphoneService;

    public SmartphoneController(SmartphoneService smartphoneService){
        this.smartphoneService = smartphoneService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSmartphoneById(@PathVariable Long id) {
        return new ResponseEntity<>(smartphoneService.getById(id), HttpStatus.OK);
    }
    @GetMapping("/byPage/{pageNumber}")
    public ResponseEntity<Object> getAllSmartphones(@PathVariable Integer pageNumber){
        return new ResponseEntity<>(smartphoneService.getAll(pageNumber), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Object> getCount(){
        return new ResponseEntity<>(smartphoneService.getCount(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addSmartphone(@Valid @RequestBody Smartphone newSmartphone, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        smartphoneService.add(newSmartphone);
        return new ResponseEntity<>("Smartphone added succesfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSmartphone(@Valid @RequestBody Smartphone newSmartphone, @PathVariable Long id, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        smartphoneService.update(newSmartphone, id);
        return new ResponseEntity<>("Smartphone updated succesfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSmartphone(@PathVariable Long id){
        smartphoneService.delete(id);
        return new ResponseEntity<>("Smartphone deleted succesfully", HttpStatus.OK);
    }

    @GetMapping("/getWithPriceHigherThan/{pageNumber}")
    public ResponseEntity<Object> getSmartphonesWithPriceHigherThanGivenValue(@PositiveOrZero @PathVariable Integer pageNumber,
                                                                              @RequestParam BigDecimal price){
        return new ResponseEntity<>(smartphoneService.getSmartphonesWithPriceHigherThanGivenValue(price, pageNumber), HttpStatus.OK);
    }
}