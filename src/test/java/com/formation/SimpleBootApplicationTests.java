package com.formation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.formation.service.Calculator;
import com.formation.service.MessageBuilder;

@SpringBootTest
class SimpleBootApplicationTests {

	private Calculator calculator = new Calculator(); 
	
	@Test
	public void testSum() {
		assertEquals(5, calculator.sum(2,3));
	}

	@Test
	public void testName() {
		MessageBuilder obj= new MessageBuilder();
		assertEquals("Hello Toto", obj.getMessage("Toto"));
	}
}
