package com.lab1gabarsolon;

import com.lab1gabarsolon.Model.Display;
import com.lab1gabarsolon.Model.Smartphone;
import com.lab1gabarsolon.Repository.SmartphoneRepository;
import com.lab1gabarsolon.Service.SmartphoneService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SmartphoneServiceTests {
    @Mock
    SmartphoneRepository smartphoneRepository;
    @InjectMocks
    private SmartphoneService smartphoneService;

    @Test
    public void givenSmartphones_whenGetWithPriceHigherThanGivenValueAndSpecificSmartphoneExists_thenReturnRequiredSmartphones(){
        Display d1 = new Display("type", 5.1, 1, 1, "protection");
        Smartphone s1 = new Smartphone("brand1", "model1", new BigDecimal(100),
                64, LocalDate.parse("2022-02-02"), d1);
        Smartphone s2 = new Smartphone("brand2", "model2", new BigDecimal(200),
                64, LocalDate.parse("2022-02-02"), d1);
        Smartphone s3 = new Smartphone("brand3", "model3", new BigDecimal(300),
                64, LocalDate.parse("2022-02-02"), d1);

        List<Smartphone> smartphoneList = Arrays.asList(s1, s2, s3);

        when(smartphoneRepository.findAll()).thenReturn(smartphoneList);

        List<Smartphone> requiredSmartphones = smartphoneService.getSmartphonesWithPriceHigherThanGivenValue(new BigDecimal(150));
        assertThat(requiredSmartphones).contains(s2);
        assertThat(requiredSmartphones).contains(s3);
    }
}
