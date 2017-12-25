package unitTests;

import static org.junit.Assert.*;
import org.junit.Test;
import birthdays.Birthday;
import birthdays.Commons;

public class BirthdayTest extends Commons{

	int testYear 	= 1998;  
	int testMonth 	= 4;  
	int testDay 	= 5;  

	@Test
	public void testBirthday() {
		Birthday underTest = new Birthday(testYear, testMonth, testDay);
		
		if(underTest.getAge() != 0.0 && underTest.getCal() != null && underTest.transformToBirthdayFormat() != null){
			System.out.println("constructor worked fine");
		}
	}
	
	@Test
	public void testGetAge() {
		Birthday underTest = new Birthday(testYear, testMonth, testDay);
		double expected = 19;
		double actual = underTest.getAge();
		assertEquals(expected, actual, 0);
		System.out.println("getAge() worked fine");
		
		testOutPut(Double.toString(expected), Double.toString(actual));
	}
	
	@Test
	public void testGetBirthDate() {
		Birthday underTest = new Birthday(testYear, testMonth, testDay);
		String expected = "05.04.1998";
		String actual = underTest.getBirthDate();
		assertEquals(expected, actual);
		System.out.println("getBirthDate() worked fine");
		
		testOutPut(expected, actual);
	}

	@Test
	public void testTransformToBirthdayFormat() {
		Birthday underTest = new Birthday(testYear, testMonth, testDay);
		String expected = "05.04";
		String actual = underTest.transformToBirthdayFormat();
		assertEquals(expected, actual);
		System.out.println("transformToBirthdayFormat() worked fine");
		
		testOutPut(expected, actual);
	}	
}