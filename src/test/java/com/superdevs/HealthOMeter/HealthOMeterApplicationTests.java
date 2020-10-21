package com.superdevs.HealthOMeter;


import com.superdevs.HealthOMeter.service.CalculatorService;
import com.superdevs.HealthOMeter.service.managers.CalculatorManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.math.BigDecimal;

@SpringBootTest
class HealthOMeterApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testCalculatorServiceForBMI() {
		//given
		ApplicationContext context =
				new AnnotationConfigApplicationContext(CalculatorManager.class, CalculatorService.class);
		CalculatorService calculatorService = context.getBean(CalculatorService.class);
		BigDecimal weight = new BigDecimal("119.522");
		BigDecimal height = new BigDecimal("1.971");
		BigDecimal expectedValue = new BigDecimal("30.8");
		//when
		BigDecimal result = calculatorService.getCalculateBMI(weight, height);
		//then
		Assertions.assertEquals(0, result.compareTo(expectedValue));
	}

}
