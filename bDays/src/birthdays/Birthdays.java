package birthdays;

import java.util.ArrayList;

public class Birthdays extends Commons implements Values{
		
	
	/**Constructors**/
	
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
	
	//TODO: Method to add the full ArrayList of birthdayPersons to the database! psqler?
	
	public String toString(){	// to print the birthdays Arraylist to the console
		ArrayList <String> toPrint = transformBirthdaysToStrings();
		for(String str : toPrint){
			System.out.println(str + "\n");
		}
		return "\n";
	}
	

	
	public BirthdayPerson checkForBirthdaysTODAY(){// call this to check all birthdayPersons if bd is TODAY!
		
		for(int i = 0; i<this.all_BirthdayPersons.size(); i++){
			String btd = this.all_BirthdayPersons.get(i).birthday.transformToBirthdayFormat();
			
			if(btd.equals(DATEFORMAT_FOR_BIRTHDAYCHECK.format(TODAY))){
				System.out.println("We got a hit there!");
				return this.all_BirthdayPersons.get(i);
			}
		}
		//Ye no birthday today so we can chill!
		return null;
	}
	
	public void contextForBirthdayCheck(){//checks, sends 
		BirthdayPerson bP = this.checkForBirthdaysTODAY();
		
		String msg = bP.generateBirthdayMessage();
		//send the msg to the whatsapp acc 
	}
	
}

