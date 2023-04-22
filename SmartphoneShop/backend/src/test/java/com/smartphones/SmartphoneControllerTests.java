package com.smartphones;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

import com.smartphones.Controller.SmartphoneController;
import com.smartphones.Model.Display;
import com.smartphones.Model.Smartphone;
import com.smartphones.Service.SmartphoneService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@WebMvcTest(SmartphoneController.class)
class SmartphoneControllerTests {
	private static List<Smartphone> smartphoneList;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private static SmartphoneService smartphoneService;
	@BeforeAll
	public static void init(){
		Display d1 = new Display("type", 5.1, 1, 1, "protection");
		Smartphone s1 = new Smartphone("brand1", "model1", new BigDecimal(100),
				64, LocalDate.parse("2022-02-02"), d1, "");
		Smartphone s2 = new Smartphone("brand2", "model2", new BigDecimal(200),
				64, LocalDate.parse("2022-02-02"), d1, "");
		Smartphone s3 = new Smartphone("brand3", "model3", new BigDecimal(300),
				64, LocalDate.parse("2022-02-02"), d1, "");

		smartphoneList = Arrays.asList(s2, s3);
	}

	@Test
	void givenSmartphones_whenGetWithPriceHigherThanGivenValueAndSpecificSmartphoneExists_thenReturnedJsonArrayShouldContainSpecificSmartphone()
			throws Exception{
		when(smartphoneService.getSmartphonesWithPriceHigherThanGivenValue(new BigDecimal(150),0)).thenReturn(smartphoneList);

		this.mockMvc.perform(get("/api/smartphone/getWithPriceHigherThan/150"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("brand2")));
	}

	@Test
	void givenSmartphonesAndNoPrice_whenGetWithPriceHigherThan_thenStatusIsNotFound()
		throws Exception{
		when(smartphoneService.getSmartphonesWithPriceHigherThanGivenValue(new BigDecimal(150),0)).thenReturn(smartphoneList);
		this.mockMvc.perform(get("/api/smartphone/getWithPriceHigherThan/"))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
}
