package birthdays;

import java.util.Calendar;
import java.util.Date;

public class Birthday implements Values{
	
	Date birthday;
	Calendar cal = null;
	
	public Birthday(int year, int month, int day){
		this.setCal(Calendar.getInstance());
		this.cal.set(year, month-1, day);
		this.setBirthday(this.cal.getTime());
	}
	
	public double getAge(){
		Date TODAY = new Date();
		return ((TODAY.getTime() - this.birthday.getTime())/ 1000 / 60 / 60 / 24 / 365);//this is done to get the age from the person
	}
	public String getBirthDate(){ //returns format dd.MM.YYYY
		return (DATEFORMAT_FOR_BIRTHDAYPERSON.format(birthday));
	}
	public String transformToBirthdayFormat(){
		return DATEFORMAT_FOR_BIRTHDAYCHECK.format(this.birthday);
	}


	
	/**Getters**/
	public Date getBirthday() {
		return birthday;
	}
	public Calendar getCal() {
		return cal;
	}
	/**Setters**/
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setCal(Calendar cal) {
		this.cal = cal;
	}
}
