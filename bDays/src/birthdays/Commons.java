package birthdays;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Commons implements Values{
	public ArrayList<BirthdayPerson> all_BirthdayPersons;
	
	public Commons(){
		
	}
	
	public static void testOutPut(String expected, String actual){
		System.out.println("Expected: " + expected + "\nactual: " + actual + "\n");
	}
	

	
	public static String evalPersonCategory(BirthdayPerson bP) throws Exception{//this method is used to get the String behind the category enum
		switch (bP.category){
			case Familie: return FAMILY_STRING;
			case Arbeit: return WORK_STRING;
			case Trinken: return DRINKING_STRING;
			case Hochschule: return UNIVERSITY_STRING;
			case Rest: return REST_STRING;
		default:
			throw new Exception("The personCategory of Person" + bP.firstName + " " + bP.secondName + "is not set!");
		}
	}
	public static PersonCategory reEvalPersoncategory(String str) throws Exception{//this method is used to transform the String from the database to the personCategory
		switch(str){
			case FAMILY_STRING: return PersonCategory.Familie;
			case WORK_STRING: return PersonCategory.Arbeit;
			case DRINKING_STRING: return PersonCategory.Trinken;
			case UNIVERSITY_STRING: return PersonCategory.Hochschule;
			case REST_STRING: return PersonCategory.Rest;
		default: 
			throw new Exception("The String from the Database is not one that exists in the personcategorys!");
		}
	}
	
	
	
	
	
	
	public void log(String fP){	// logs the birthday ArrayList to a txt file with a unique name made by the current time
		ArrayList<String> toLog = transformBirthdaysToStrings();

		String filename = DATEFORMAT_FOR_LOGS.format(TODAY) + ".txt";
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
	public ArrayList<String> transformBirthdaysToStrings(){//ArrayList of birthdayPersons to ArrayList of Strings with toString()
		ArrayList <String> retArrayList = new ArrayList<String>();
		for(int i = 0; i < this.all_BirthdayPersons.size(); i++){
			retArrayList.add(this.all_BirthdayPersons.get(i).toString());
		}
		return retArrayList;
	}
	
}
