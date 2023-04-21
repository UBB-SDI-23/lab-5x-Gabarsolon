package com.smartphones.Service;

import com.smartphones.Model.Display;
import com.smartphones.Model.DisplaySmartphonesDTO;
import com.smartphones.Repository.DisplayRepository;
import com.smartphones.Repository.SmartphoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DisplayService extends EntityService<Display>{

    private SmartphoneRepository smartphoneRepository;

    @Autowired
    public DisplayService(DisplayRepository repository, SmartphoneRepository smartphoneRepository) {
        super(repository);
        this.smartphoneRepository = smartphoneRepository;
    }

    public List<Long> getAllDisplayIds(){
        return repository.findAll().stream().map(display -> display.getId()).collect(Collectors.toList());
    }

    public List<Display> getDisplaysByTypeSizeResolution(String query){
        String type;
        Double size;
        Integer resolutionWidth, resolutionHeight;

        String[] sizeAndResolution;
        DisplayRepository displayRepository = (DisplayRepository)repository;
        Pageable pageable = PageRequest.of(0, 20);

        type = query.split("[0-9]")[0].strip();
        String typeAndRestString = query.substring(type.length());
        sizeAndResolution = typeAndRestString.split(" ");
        if(sizeAndResolution.length < 2)
            return displayRepository.findByTypeContaining(type, pageable);

        size = Double.parseDouble(sizeAndResolution[1]);
        if(sizeAndResolution.length < 3 || sizeAndResolution[2].split("x").length < 2)
            return displayRepository.findByTypeContainingAndSize(type, size, pageable);

        String[] resolution = sizeAndResolution[2].split("x");
        resolutionWidth = Integer.parseInt(resolution[0]);
        resolutionHeight = Integer.parseInt(resolution[1]);
        return ((DisplayRepository) repository).findByTypeContainingAndSizeAndResolutionWidthAndResolutionHeight(
                type, size, resolutionWidth, resolutionHeight, PageRequest.of(0, 20));
    }

    public void update(Display newDisplay, Long id){
        repository.findById(id).map(display -> {
            display.setType(newDisplay.getType());
            display.setSize(newDisplay.getSize());
            display.setResolutionWidth(newDisplay.getResolutionWidth());
            display.setResolutionHeight(newDisplay.getResolutionHeight());
            display.setProtection(newDisplay.getProtection());
            return repository.save(display);
        }).orElseGet(()->{
            newDisplay.setId(id);
            return repository.save(newDisplay);
        });
    }

    public void linkSmartphonesToDisplay(DisplaySmartphonesDTO displaySmartphonesDTO){
        Display display = repository.getById(displaySmartphonesDTO.getDisplayId());
        displaySmartphonesDTO.getSmartphonesIds().stream().forEach(id-> {
                    smartphoneRepository.getById(id).setDisplay(display);
                }
                );
    }
}
