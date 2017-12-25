package unitTests;

import static org.junit.Assert.*;
import org.junit.Test;

import birthdays.PersonCategory;

public class PersonCategoryTest {

	private final static String expected = "Trinken, Familie, Internet, Arbeit, Hochschule, Rest";
	@Test
	public void test() {
		String enumTestString = (PersonCategory.Trinken + ", " 
				+ PersonCategory.Familie + ", " 
				+ PersonCategory.Internet + ", " 
				+ PersonCategory.Arbeit + ", " 
				+ PersonCategory.Hochschule + ", " 
				+ PersonCategory.Rest);
		assertEquals("The enum personcategory is working as expected!", expected, enumTestString);
	}

}
