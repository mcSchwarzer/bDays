package birthdays;

import java.util.Random;

public class BirthdayPerson extends Commons implements Values{
	
	String firstName;
	String secondName;
	String extra;
	Birthday birthday;
	PersonCategory category;
	
	public BirthdayPerson(String firstName, String secondName, String extra, Birthday birthday, PersonCategory category){
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
		retString += ("\nName: " + firstName + " " + secondName + " [" + extra + "] " + "\nGeburtstag: " + birthday.getBirthDate());
		if(firstName == null || secondName == null || birthday == null){
			try {
				throw new Exception("firstName == null || secondName == null || birthday == null");
			} catch (Exception e){
				System.out.println(e.getMessage()+ MANUAL_THROW);
			}
		}
		return retString;
	}
	
	public String generateBirthdayMessage(){
		String[] bdmsg = 
			{"Hi " + this.firstName + " alles Gute zum " + this.birthday.getAge() + " Geburtstag!! Liebe Grüße, Markus",
					"Hey " + this.firstName + " happy birthday zum " + this.birthday.getAge() + " Geburtstag!!",
					"Alles Gute zum " + this.birthday.getAge() + " Geburtstag " + this.firstName + "!!",
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
