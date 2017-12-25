package psql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import birthdays.Birthday;
import birthdays.Birthdays;
import birthdays.BirthdayPerson;
import birthdays.PersonCategory;



/**
 * databaseDesign:
 * 
 * 
 * 																							DATABASE_NAME = BPC!!
 * 																							PORT = 5433
 * 																							pw = *************************
 * 																							username = postgres
 * 																							 
 * 
 * birthday:
 * bpc=# create Table birthday(
 * bpc(# bdid SERIAL primary key,
 * bpc(# day int check (day between 0 and 32),
 * bpc(# month int check (month between 0 and 13),
 * bpc(# year int check (year between 1900 and 2100))
 * bpc-# ;
 * 																							CREATE TABLE
 * 																							bpc=# select * from birthday;
 * 																							bdid | day | month | year
 *																						    -----+-----+-------+------
 * 
 * 
 * 
 * 
 * bpc=# create table birthdayPerson(
 * bpc(# bdpid SERIAL PRIMARY KEY,
 * bpc(# fname varchar(20) not null,
 * bpc(# sname varchar(20) not null,
 * bpc(# extra varchar(100),
 * bpc(# cat varchar(20) check(cat = 'Familie' or cat = 'Arbeit' or cat = 'Trinken' or cat = 'Rest' or cat = 'Hochschule'),
 * bpc(# bdid int REFERENCES birthday (bdid)
 * bpc(# )
 * bpc-# ;
 * 																							CREATE TABLE
 * 																							bpc=# select * from birthdayPerson;
 * 																							bdpid | fname | sname | extra | cat | bdid
 * 																						    ------+-------+-------+-------+-----+------
 * 
 * 
 * 
 * 
 * 
 * 
 * bpc=# select fname,sname, day,month,year from birthday natural join birthdayPerson;
 * 																							fname  |   sname   | day | month | year
 * 																						    -------+-----------+-----+-------+------
 * 																							Markus | Schwarzer |   5 |     4 | 1998
 * 
 * 
 * @author marku
 *
 */

public class PostgreSQL {

	String databaseName;
	String userName;
	String pw;
	String adr;
	int port;



	public PostgreSQL(String databaseName, String userName, String pw, int port, String adr) {
		this.databaseName = databaseName;
		this.userName = userName;
		this.pw = pw;
		this.port = port;
		this.adr = adr;
		//all we need to connect to our postgresql DB
	}


	public Connection connectToDB() throws Exception{// this method is directly from the internet
		//this uses a local db from postgres ... is there a better way to do this??
		System.out.println("-------- PostgreSQL JDBC Connection ------------");

		try {
			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			e.printStackTrace();
			return null;
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection( "jdbc:postgresql://" + this.adr + ":" + this.port + "/" + this.databaseName, this.userName, this.pw);
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}
		if (connection != null) {
			System.out.println("The Database Connection is set!");
			return connection;
		} else {
			System.out.println("Failed to make connection!");
			return null;
		}
	}
	
	public ArrayList<BirthdayPerson> readAllData() throws Exception{
		Connection c = this.connectToDB();
		
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from birthdayPerson NATURAL JOIN birthday;");	//TODO:remove * by the parameters that i want!
		

		printResultSet(rs);	
		
		ArrayList<BirthdayPerson> bpc = new ArrayList<BirthdayPerson>();
		
		while(rs.next()){
			BirthdayPerson bP = createBPfromDB(rs);
			bpc.add(bP);
		}
		
		return bpc;
	}
	
	
	
	public static BirthdayPerson createBPfromDB(ResultSet rs) throws Exception{ //this is used to create the birthdayPerson from resultobjekt
		PersonCategory cat;
		try{
			cat = Birthdays.reEvalPersoncategory(rs.getString("cat"));
		}catch(Exception e){
			throw new Exception("There was an Error with the personCategory from the database ...");
		}
		
		Birthday newBD;
		try{
			newBD = new Birthday(
				rs.getInt("year"),
				rs.getInt("month"),
				rs.getInt("day")
				);
		}catch(Exception e){
			throw new Exception("There was an Error with the construktion of the birthday from the database ...");
		}
		//birthday b = new Birthday(year, month, day);
		BirthdayPerson newBP;
		try{
			newBP = new BirthdayPerson(
					rs.getString("fname"),
					rs.getString("sname"),
					rs.getString("extra"),
					newBD,
					cat
					);
		}catch(Exception e){
			throw new Exception("There was an Error with the construktion of the birthdayPerson from the database ...");
		}	
		//BirthdayPerson b = new  BirthdayPerson(firstName, secondName, extra, birthday, category)
		return newBP;
	}
	
	
	public static void printResultSet(ResultSet rs) throws Exception{
		System.out.println("printResult_from_Database\n");
	    while(rs.next()){
	    	BirthdayPerson bP = createBPfromDB(rs);
	    	System.out.println(bP.toString() + "\n");    	
	    }
	}
	
}
