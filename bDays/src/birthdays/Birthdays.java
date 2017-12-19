package birthdays;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Birthdays {
	
	public enum personCategory {family, work, internet, drinking, university, rest};	// this is to specify where you know the birthdayPerson from.
	
	public final static String FAMILYSTRING = "Familie";
	public final static String WORKSTRING = "Arbeit";
	public final static String DRINKINGSTRING = "Trinken";
	public final static String UNIVERSITYSTRING = "Hochschule";
	public final static String RESTSTRING = "Der Ganze Rest";
	
	public final static String MANUAL_THROW = " THROWN THIS EXCEPTION MANUALLY";
	public final static String Not_MANUAL_THROW = " THIS EXCEPTION HAS NOT BEEN THROWN MANUALLY";
	
	public final static SimpleDateFormat DATEFORMAT = new SimpleDateFormat("dd.MM.YYYY"); 
	
	
	public static class Birthday{
		
		Date birthday;
		Calendar cal = null;
		
		
		public Birthday(int year, int month, int day){
			this.cal = Calendar.getInstance();
			this.cal.set(year, month-1, day);
			this.birthday = this.cal.getTime();
			//System.out.println(DATEFORMAT.format(birthday));
		}
		
		public double getAge(){
			Date TODAY = new Date();
			
			return ((TODAY.getTime() - this.birthday.getTime())/ 1000 / 60 / 60 / 24 / 365);//this is done to get the years .. actually the age from the person
		}
		public String getBirthDate(){ //returns format dd.MM.YYYY
			return (DATEFORMAT.format(birthday));
		}
	}
	public static class BirthdayPerson{
		String firstName;
		String secondName;
		String extra;
		Birthday birthday;
		personCategory category;
		
		public BirthdayPerson(String firstName, String secondName, String extra, Birthday birthday, personCategory category){
			this.firstName = firstName;
			this.secondName = secondName;
			this.extra = extra;
			this.birthday = birthday;
			this.category = category;
		}
		
		public String toString(){//proper toString for console, logs the String into one single line in a txt file
			String retString = "";
			try{
			retString += ("Du hast die Person kennengelernt bei der Kategorie: " + evalPersonCategory(this) + " !");
			}catch(Exception e){
				if(e.getMessage() == "The personCategory of Person" + this.firstName + " " + this.secondName + "is not set!"){
					System.out.println(e.getMessage() + MANUAL_THROW);
				}
				else{
					System.out.println(e.getMessage() + Not_MANUAL_THROW);
				}
			}
			retString += ("\nName: " + firstName + " " + secondName + " [" + extra + "] " + "\nGeburtstag:" + birthday.getBirthDate());
			if(firstName == null || secondName == null || birthday == null){
				try {
					throw new Exception("firstName == null || secondName == null || birthday == null");
				} catch (Exception e){
					System.out.println(e.getMessage()+ MANUAL_THROW);
				}
			}
			return retString;
		}
		
		public static String[] generateBirthdayMessage(BirthdayPerson bP){
			String bdm0 = "Hi " + bP.firstName + " alles Gute zum " + bP.birthday.getAge() + " Geburtstag!! Liebe Grüße, Markus";
			String bdm1 = "Hi " + bP.firstName + " alles Gute zum " + bP.birthday.getAge() + " Geburtstag!! Liebe Grüße, Markus";
			String bdm2 = "Hi " + bP.firstName + " alles Gute zum " + bP.birthday.getAge() + " Geburtstag!! Liebe Grüße, Markus";
			String bdm3 = "Hi " + bP.firstName + " alles Gute zum " + bP.birthday.getAge() + " Geburtstag!! Liebe Grüße, Markus";
			String bdm4 = "Hi " + bP.firstName + " alles Gute zum " + bP.birthday.getAge() + " Geburtstag!! Liebe Grüße, Markus";

			
			return null;
			
		}
	}//birthdayPerson

	ArrayList<BirthdayPerson> all_BirthdayPersons;
	
	public Birthdays(BirthdayPerson firstPerson){
		all_BirthdayPersons = new ArrayList<BirthdayPerson>();
		all_BirthdayPersons.add(firstPerson);
	}
	public Birthdays(){
		this.all_BirthdayPersons = new ArrayList<BirthdayPerson>();
	}
	public Birthdays(ArrayList <BirthdayPerson> allBirthdayPersons){
		this.all_BirthdayPersons = allBirthdayPersons;
	}
		
	public boolean addNewBirthdayPerson(BirthdayPerson bP){
		
		try{
			if(bP.category == null || bP.firstName == null || bP.secondName == null || bP.birthday == null){
				if(bP.birthday.birthday == null){
					throw new Exception("the birthday is not correct!" + MANUAL_THROW);
				}
				throw new Exception("the person is not correct!" + MANUAL_THROW);
			}
			this.all_BirthdayPersons.add(bP);
			return true;
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	
	//TODO: Method to add the full ArrayList of birthdayPersons to the database! psqler ?  
	
	
	//Main()
	/*********************************************************************************************************************************************/
	public static void main(String[] args){
		
		
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
		

		
		
	}
	
	public static String evalPersonCategory(BirthdayPerson bP) throws Exception{//this method is used to get the String behind the category enum
		switch (bP.category){
			case family: return FAMILYSTRING;
			case work: return WORKSTRING;
			case drinking: return DRINKINGSTRING;
			case university: return UNIVERSITYSTRING;
			case rest: return RESTSTRING;
		default:
			throw new Exception("The personCategory of Person" + bP.firstName + " " + bP.secondName + "is not set!");
		}
	}
	
	public void log(String fP){	// logs the birthday ArrayList to a txt file with a unique name made by the current time
		ArrayList<String> toLog = transformBirthdaysToStrings();
		
		Date TODAY = new Date();
		SimpleDateFormat DF = new SimpleDateFormat("dd.MM.YYYY.hh.mm.ss"); 

		String filename = DF.format(TODAY) + ".txt";
		String filePath = fP;
		
		File logFile = new File(filePath + filename);
		
		try {
			FileWriter fW = new FileWriter(logFile);
			BufferedWriter bW = new BufferedWriter(fW);
			
			for(String str : toLog){
				bW.write(str + "\r\n");
			}
			bW.flush();
			bW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}

	public String toString(){	// to print the birthdays Arraylist to the console
		ArrayList <String> toPrint = transformBirthdaysToStrings();
		for(String str : toPrint){
			System.out.println(str + "\n");
		}
		return "\n";
	}
	
	public ArrayList<String> transformBirthdaysToStrings(){
		ArrayList <String> retArrayList = new ArrayList<String>();
		
		for(int i = 0; i < this.all_BirthdayPersons.size(); i++){
			
		
			retArrayList.add(this.all_BirthdayPersons.get(i).toString());
			
		}
		return retArrayList;
	}
	
}













