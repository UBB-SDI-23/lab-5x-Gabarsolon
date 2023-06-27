package com.smartphones.Controller;

import com.smartphones.Model.Display;
import com.smartphones.DTO.DisplaySmartphonesDTO;
import com.smartphones.Service.DisplayService;
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
@RequestMapping("/display")
public class DisplayController {
    @Autowired
    private DisplayService displayService;

    public DisplayController(DisplayService displayService){
        this.displayService = displayService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDisplayById(@PathVariable Long id) {
        return new ResponseEntity<>(displayService.getById(id), HttpStatus.OK);
    }
    @GetMapping("/byPage/{pageNumber}")
    public ResponseEntity<Object> getAllDisplays(@PathVariable Integer pageNumber){
        return new ResponseEntity<>(displayService.getAll(pageNumber), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Object> getCount(){
        return new ResponseEntity<>(displayService.getCount(), HttpStatus.OK);
    }
    @GetMapping("/autocomplete")
    public ResponseEntity<Object> getDisplaysByNameSizeResolution(@RequestParam(name="query") String query){
        return new ResponseEntity<>(displayService.getDisplaysByTypeSizeResolution(
                URLDecoder.decode(query, StandardCharsets.UTF_8)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addDisplay(@Valid @RequestBody Display newDisplay, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        displayService.add(newDisplay);
        return new ResponseEntity<>("Display added succesfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDisplay(@Valid @RequestBody Display newDisplay, @PathVariable Long id, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        displayService.update(newDisplay, id);
        return new ResponseEntity<>("Display updated succesfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDisplay(@PathVariable Long id){
        displayService.delete(id);
        return new ResponseEntity<>("Display deleted succesfully", HttpStatus.OK);
    }

    @PutMapping("/addSmartphones")
    public ResponseEntity<Object> addSmartphonesToSpecifcDisplay(@RequestBody DisplaySmartphonesDTO displaySmartphonesDTO){
        displayService.linkSmartphonesToDisplay(displaySmartphonesDTO);
        return new ResponseEntity<>("Smartphones added succesfully to display " + displaySmartphonesDTO.getDisplayId()
                , HttpStatus.OK);
    }
}
