package com.formation.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.formation.service.Calculator;

@RestController
public class CalculatorController {
	@Autowired
	private Calculator calculator;
	
	public  String  sum(Integer a, Integer b) {
		return String.valueOf(calculator.sum(a, b));
	}

}
