package unitTests;

import static org.junit.Assert.*;
import org.junit.Test;

import birthdays.Commons;

public class ValuesTest {
	
	@Test
	public void test() {
		
		if(	Commons.FAMILY_STRING != "Familie" 												||
				Commons.WORK_STRING != "Arbeit" 											||
				Commons.DRINKING_STRING != "Trinken" 										||
				Commons.UNIVERSITY_STRING != "Hochschule" 									||
				Commons.REST_STRING != "Rest"												||
				Commons.MANUAL_THROW != " THROWN THIS EXCEPTION MANUALLY"					||
				Commons.Not_MANUAL_THROW != " THIS EXCEPTION HAS NOT BEEN THROWN MANUALLY"){
			
			fail("constants have wrong values!");
		}
		
		if(Commons.DATEFORMAT_FOR_BIRTHDAYPERSON.toPattern() != "dd.MM.YYYY" 			||
				Commons.DATEFORMAT_FOR_LOGS.toPattern() != "dd.MM.YYYY.hh.mm.ss" 	||
				Commons.DATEFORMAT_FOR_BIRTHDAYCHECK.toPattern() != "dd.MM") {

			fail("dateformats have wrong pattern!");
		}
		
		else{
		}
			System.out.println("10 values are correct!");
		}
		
		
		
		
			
}
