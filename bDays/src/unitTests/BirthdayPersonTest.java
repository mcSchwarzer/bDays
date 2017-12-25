package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import birthdays.Birthday;
import birthdays.BirthdayPerson;
import birthdays.PersonCategory;

public class BirthdayPersonTest {

	String firstName = "Markus";
	String secondName = "Schwarzer";
	String extra = "Josua";
	PersonCategory cat = PersonCategory.Familie;
	
	
	@Test
	public void testBirthdayPerson() {
		Birthday birthday = new Birthday(1998, 4, 5);
		BirthdayPerson underTest = new BirthdayPerson(firstName, secondName, extra, birthday, cat );
	}

	@Test
	public void testToString() {
		Birthday birthday = new Birthday(1998, 4, 5);
		String expected = "Du hast die Person kennengelernt bei der Kategorie: Familie !\nName: Markus Schwarzer [Josua] \nGeburtstag: 05.04.1998";
		BirthdayPerson birthdayPerson = new BirthdayPerson(firstName, secondName, extra, birthday, cat);
		String actual = birthdayPerson.toString();
		System.out.println(actual);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGenerateBirthdayMessage() {
		Birthday birthday = new Birthday(1998, 4, 5);
		BirthdayPerson birthdayPerson = new BirthdayPerson(firstName, secondName, extra, birthday, cat);

		String expected1 = "Hi Markus alles Gute zum 19.0 Geburtstag!! Liebe Grüße, Markus";
		String expected2 = "Hey Markus happy birthday zum 19.0 Geburtstag!!";
		String expected3 = "Alles Gute zum 19.0 Geburtstag Markus!!";
		
		System.out.print("start random birthdayMSG:");
		for(int i = 0; i <= 100;i++){
			String msg = birthdayPerson.generateBirthdayMessage();
			//System.out.println(msg);
			if(msg.equals(expected1) || msg.equals(expected2) || msg.equals(expected3)){
				System.out.print(".");	//test s alright
			}
			else{
				fail("unexpected bdmsg");
			}
		}
		System.out.println();//nwline
	}
	
	
	
	
	
}
