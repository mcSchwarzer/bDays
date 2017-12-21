package birthdays;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import psql.PostgreSQL;

public class Birthdays {
	
	public enum personCategory {family, work, internet, drinking, university, rest};	// this is to specify where you know the birthdayPerson from.
	
	public final static String FAMILY_STRING = "Familie";
	public final static String WORK_STRING = "Arbeit";
	public final static String DRINKING_STRING = "Trinken";
	public final static String UNIVERSITY_STRING = "Hochschule";
	public final static String REST_STRING = "Der Ganze Rest";
	
	public final static String MANUAL_THROW = " THROWN THIS EXCEPTION MANUALLY";
	public final static String Not_MANUAL_THROW = " THIS EXCEPTION HAS NOT BEEN THROWN MANUALLY";
	
	public final static SimpleDateFormat DATEFORMAT = new SimpleDateFormat("dd.MM.YYYY"); 
	
	ArrayList<BirthdayPerson> all_BirthdayPersons;
	
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
		public String transformToBirthdayFormat(){
			SimpleDateFormat useableFormat = new SimpleDateFormat("dd.MM");
			return useableFormat.format(this);
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
		
		public static String generateBirthdayMessage(BirthdayPerson bP){
			String[] bdmsg = 
				{"Hi " + bP.firstName + " alles Gute zum " + bP.birthday.getAge() + " Geburtstag!! Liebe Grüße, Markus",
						"Hey " + bP.firstName + " happy birthday zum " + bP.birthday.getAge() + " Geburtstag!!",
						"Alles Gute zum " + bP.birthday.getAge() + " Geburtstag" + bP.firstName + "!!",
				};		//TODO: think of a better way to do this!

			switch(new Random().nextInt(3)){
			case 0 : return bdmsg[0];
			case 1 : return bdmsg[1];
			case 2 : return bdmsg[2];
			default : return "Alles Gute zum Geburtstag!!";
			}	
		}
		
		public void sendBirthdayMessageToPerson(String bdmsg){
			
			/**
			 * TODO: if person has whatsapp send bdmsg else send sms
			 * 
			 */
		}
		
	}//birthdayPerson


	
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
	
	
	
	public static String evalPersonCategory(BirthdayPerson bP) throws Exception{//this method is used to get the String behind the category enum
		switch (bP.category){
			case family: return FAMILY_STRING;
			case work: return WORK_STRING;
			case drinking: return DRINKING_STRING;
			case university: return UNIVERSITY_STRING;
			case rest: return REST_STRING;
		default:
			throw new Exception("The personCategory of Person" + bP.firstName + " " + bP.secondName + "is not set!");
		}
	}
	
	
	public static personCategory reEvalPersoncategory(String str) throws Exception{//this method is used to transform the String from the database to the personCategory
		switch(str){
			case FAMILY_STRING: return personCategory.family;
			case WORK_STRING: return personCategory.work;
			case DRINKING_STRING: return personCategory.drinking;
			case UNIVERSITY_STRING: return personCategory.university;
			case REST_STRING: return personCategory.rest;
		default: 
			throw new Exception("The String from the Database is not one that exists in the personcategorys!");
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
	
	public BirthdayPerson checkForBirthdaysTODAY(){// call this to check all birthdayPersons if bd is TODAY!
		Date TODAY  = new Date();
		
		for(int i = 0; i<this.all_BirthdayPersons.size(); i++){
			String btd = this.all_BirthdayPersons.get(i).birthday.transformToBirthdayFormat();
			
			SimpleDateFormat useableFormat = new SimpleDateFormat("dd.MM");
			if(btd.equals(useableFormat.format(TODAY))){
				System.out.println("We got a hit there!");
				return this.all_BirthdayPersons.get(i);
			}
		}
		
		//Ye no birthday today so we can chill!
		
		return null;//found nothing!
	}
	
	public void contextForBirthdayCheck(){//checks, sends 
		BirthdayPerson bP = this.checkForBirthdaysTODAY();
		
		String msg = BirthdayPerson.generateBirthdayMessage(bP);
		//send the msg to the whatsapp acc 
	}
	
}













