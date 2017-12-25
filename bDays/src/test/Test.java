package test;

import birthdays.Birthdays;
import psql.PostgreSQL;

public class Test {

	public static void main(String[] args){
		
		/**
		Birthday Geburtstag1 = new Birthday(1998, 4, 5);
		BirthdayPerson Geburtstagskind = new BirthdayPerson("Markus", "Schwarzer", "Josua", Geburtstag1, personCategory.drinking);
		
		Birthday Geburtstag2 = new Birthday(1998, 9, 1);
		BirthdayPerson Geburtstagskind2 = new BirthdayPerson("Jan", "Schulz", "", Geburtstag2, personCategory.family);
		
		Birthday Geburtstag3 = new Birthday(1998, 14, 2);
		BirthdayPerson Geburtstagskind3 = new BirthdayPerson("Werner", "Schwarzer", "Otto", Geburtstag3, personCategory.family);
		
		Birthdays birthdays = new Birthdays(Geburtstagskind);

		birthdays.addNewBirthdayPerson(Geburtstagskind2);
		birthdays.addNewBirthdayPerson(Geburtstagskind3);

		System.out.println(birthdays);
		
		birthdays.log("C:\\Users\\defaultuser0\\Geburtstags_Logs\\");
		
		**/
		
		Birthdays birthdays = new Birthdays();
		PostgreSQL psql = new PostgreSQL("bpc", "postgres", "rfvcde4321", 5433, "localhost");
		try {
			birthdays.all_BirthdayPersons = psql.readAllData();
			System.out.println(birthdays);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("läuft net!");
			e.printStackTrace();
		}
	
	}
}
