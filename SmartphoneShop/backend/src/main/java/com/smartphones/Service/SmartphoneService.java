package com.smartphones.Service;

import com.smartphones.Model.Smartphone;
import com.smartphones.Repository.SmartphoneRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SmartphoneService extends EntityService<Smartphone>{
    @Autowired
    public SmartphoneService(SmartphoneRepository repository) {
        super(repository);
    }
    public List<Long> getAllSmartphoneIds(){
        return repository.findAll().stream().map(smartphone -> smartphone.getId()).collect(Collectors.toList());
    }

    public List<Smartphone> getSmartphonesByBrandModelPrice(String query){
        //Get the first 20 results
        PageRequest pageRequest = PageRequest.of(0, 20);
        //Cast the repository to SmartphoneRepository to access specific methods
        SmartphoneRepository smartphoneRepository = (SmartphoneRepository)repository;

        String[] queryFields = query.split(" ");
        Integer length = queryFields.length;

        String brand = queryFields[0];
        if(length < 2)
            return smartphoneRepository.findByBrandContainingIgnoreCase(brand, pageRequest);

        String model = Arrays.stream(queryFields).skip(1).
                filter(s -> !StringUtils.isNumeric(s)).collect(Collectors.joining(" "));
        if(!StringUtils.isNumeric(queryFields[length-1]))
            return smartphoneRepository.findByBrandContainingIgnoreCaseAndModelContainingIgnoreCase(brand, model, pageRequest);

        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(queryFields[length-1]));
        return smartphoneRepository.findByBrandContainingIgnoreCaseAndModelContainingIgnoreCaseAndPrice(
                brand, model, price, pageRequest
        );
    }
    public void update(Smartphone newSmartphone, Long id){
        repository.findById(id).map(smartphone -> {
            smartphone.setBrand(newSmartphone.getBrand());
            smartphone.setModel(newSmartphone.getModel());
            smartphone.setPrice(newSmartphone.getPrice());
            smartphone.setStorageCapacity(newSmartphone.getStorageCapacity());
            smartphone.setLaunchDate(newSmartphone.getLaunchDate());
            smartphone.setDisplay(newSmartphone.getDisplay());
            return repository.save(smartphone);
        }).orElseGet(()->{
            newSmartphone.setId(id);
            return repository.save(newSmartphone);
        });
    }

    public List<Smartphone> getSmartphonesWithPriceHigherThanGivenValue(BigDecimal price, Integer pageNumber){
        return ((SmartphoneRepository)repository).findByPriceGreaterThan(price, PageRequest.of(pageNumber, 10));
    }

    public Integer getNumberOfSmartphonesHavingSpecificDisplayId(Integer displayId){
        return ((SmartphoneRepository)repository).countSmartphonesByDisplay_Id(displayId);
    }

}