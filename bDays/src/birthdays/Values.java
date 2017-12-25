package birthdays;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface Values {	
	
	/**values for the database (enum to String) is there a better way?**/
	public final static String FAMILY_STRING = "Familie";
	public final static String WORK_STRING = "Arbeit";
	public final static String DRINKING_STRING = "Trinken";
	public final static String UNIVERSITY_STRING = "Hochschule";
	public final static String REST_STRING = "Rest";
	
	
	/**maybe remove later?**/
	public final static String MANUAL_THROW = " THROWN THIS EXCEPTION MANUALLY";
	public final static String Not_MANUAL_THROW = " THIS EXCEPTION HAS NOT BEEN THROWN MANUALLY";
	
	
	/**DateFormats**/
	public final static SimpleDateFormat DATEFORMAT_FOR_BIRTHDAYPERSON = new SimpleDateFormat("dd.MM.YYYY");//for birthdayPerson
	public final static SimpleDateFormat DATEFORMAT_FOR_LOGS = new SimpleDateFormat("dd.MM.YYYY.hh.mm.ss");//to get an unique name for the logFile
	public final static SimpleDateFormat DATEFORMAT_FOR_BIRTHDAYCHECK = new SimpleDateFormat("dd.MM");//to see if a person has its birthday today
	
	
	/**TODAY**/
	public static Date TODAY = new Date();
}
