package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class BMICalculatorTest {
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Before all unit tests");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("After all unit tests");
	}

	@Test
	void should_ReturnTrue_When_DietRecommended() {
		//given
		double weight = 89.0;
		double height = 1.72;
		
		//when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);
		
		//then
		assertTrue(recommended);
	}
	
	@Test
	void should_ReturnFalse_When_DietRecommended() {
		//given
		double weight = 59.0;
		double height = 1.77;
		
		//when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);
		
		//then
		assertFalse(recommended);
	}
	
	@Test
	void should_ThrowArithmeticException_When_HeightZero() {
		//given
		double weight = 59.0;
		double height = 0.0;
		
		//when
		Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
		
		//then
		assertThrows(ArithmeticException.class, executable);
	}
	
	@Test
	void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {
		//given
		List<Coder> coders = new ArrayList<>();
		coders.add(new Coder(60.0, 1.80));
		coders.add(new Coder(98.0, 1.82));
		coders.add(new Coder(64.7, 1.82));
		
		//when
		Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
		
		//then (in this way, if both assertions fail then both will be printed
		assertAll(
			() -> assertEquals(98.0, coderWorstBMI.getWeight()),
			() -> assertEquals(1.82, coderWorstBMI.getHeight())
		);
	}
	
	@Test
	void should_ReturnNullWorstBMICoder_When_CoderListEmpty() {
		//given
		List<Coder> coders = new ArrayList<>();
		
		//when
		Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
		
		//then
		assertNull(coderWorstBMI);
	}
	
	@Test
	void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty() {
		//given
		List<Coder> coders = new ArrayList<>();
		coders.add(new Coder(60.0, 1.80));
		coders.add(new Coder(98.0, 1.82));
		coders.add(new Coder(64.7, 1.82));
		double[] expected = {18.52, 29.59, 19.53};
		
		//when
		double[] bmiScores = BMICalculator.getBMIScores(coders);
		
		//then
		//	assertEquals fails here, because compare the memory reference
		assertArrayEquals(expected, bmiScores);
	}

}
