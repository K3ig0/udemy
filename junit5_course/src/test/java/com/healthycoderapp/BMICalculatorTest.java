package com.healthycoderapp;

import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class BMICalculatorTest {
	
	private String environment = "dev";
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Before all unit tests");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("After all unit tests");
	}
	
	@Nested
	class IsDietRecommendedTests {
		@ParameterizedTest(name = "weight={0}, height={1}") // without specifying these values also works
		@CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1) // read the csv file with this name, and skip the header of the file
		void should_ReturnTrue_When_DietRecommended(Double coderWeight, Double coderHeight) {
			//given
			double weight = coderWeight;
			double height = coderHeight;
			
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
	}
	
	@Nested
	@DisplayName("{{}}} sample inner class display name")
	class FindCoderWithWorstBMI {
		
		@Test
		@DisplayName(">>>> sample method display name")
		@Disabled
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
		void should_ReturnCoderWithWorstBMIIn1Ms_When_CoderListHas10000Elements() {
			//given
			assumeTrue(BMICalculatorTest.this.environment.equals("prod"));
			List<Coder> coders = new ArrayList<>();
			for (int i=0; i < 10000; i++) {
				coders.add(new Coder(1.0 + i, 10.0 + i));
			}
			
			//when
			Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);
			
			//then
			assertTimeout(Duration.ofMillis(500), executable);
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
		
	}
	
	@Nested
	class GetBMIScoresTests {
		
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

}
