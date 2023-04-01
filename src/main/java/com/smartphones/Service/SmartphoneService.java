package com.smartphones.Service;

import com.smartphones.Model.Smartphone;
import com.smartphones.Repository.SmartphoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public List<Smartphone> getSmartphonesWithPriceHigherThanGivenValue(BigDecimal price){
        return repository.findAll().stream().filter(smartphone -> smartphone.getPrice().compareTo(price) == 1)
                .collect(Collectors.toList());
    }
}