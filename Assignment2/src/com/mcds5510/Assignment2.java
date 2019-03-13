package com.mcds5510;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mcds5510.Entity.*;
import com.mcds5510.connect.ConnectionFactory;
import com.mcds5510.connect.MySQLJDBCConnection;
import com.mcds5510.dao.MySQLAccess;

public class Assignment2 {

	public static Connection single_instance;
	
	public static Connection getInstance() {
		if (single_instance == null) {
			MySQLJDBCConnection dbConnection = new MySQLJDBCConnection();
			single_instance = dbConnection.setupConnection();
		}
		return single_instance;
	}
	static Scanner sc = new Scanner(System.in);
	public static int Start() {
		int input;
		boolean flag;
		System.out.println("Select any option by entering....");
		do {	
			flag=false;
			System.out.println("1.Create a transaction");
			System.out.println("2.Update a transaction");
			System.out.println("3.Get a transaction");
			System.out.println("4.Remove a transaction");
			input=sc.nextInt();
			if(input>4 || input< 1) {
				flag=true;
				System.out.println("Please enter valid Number.....");
			}
		}while(flag);
		return input;
	}
	
	public static Transaction PostTransaction(Transaction trxn, ArrayList<String> Cards,ArrayList<Integer> Prefix,ArrayList<Integer> Length) {
		System.out.println("Enter TransactionID");
		trxn.setID(sc.nextInt());
		trxn.setCardNumber(creditCardCheck(Cards, Prefix, Length));
		System.out.println("Enter name on card");
		trxn.setNameOnCard(checkValidString(sc.next()));
		System.out.println("Enter Unit price");
		trxn.setUnitPrice(sc.nextInt());
		System.out.println("Enter quantity");
		trxn.setQuantity(sc.next());
		System.out.println("Enter Expiry Date");
		trxn.setExpDate(checkValidExpDate(sc.next()));
		return trxn;
	}
	
	public static int get() {
		System.out.println("Enter TransactionID");
		int ID=sc.nextInt();
		return ID;
	}
	
	public static String checkValidExpDate(String expDate) {	
		String regex="(0[1-9]|10|11|12)/201[6-9]|202[0-9]|203[0-1]$";
		boolean flag;
		do {
			Matcher matcher = Pattern.compile(regex).matcher(expDate);
			if(matcher.find()) {
				flag=false;
			}
			else {
				System.out.println("Enter valid name");
				expDate=sc.next();
				flag=true;
			}
		}while(flag);	
    	return expDate;
	}
	
	public static String checkValidString(String str) {
		
		String regex="^[^<>'\\\";:^!@#$%*+?]*$";
		boolean flag;
		do {
			Matcher matcher = Pattern.compile(regex).matcher(str);
			if(matcher.find()) {
				flag=false;
			}
			else {
				System.out.println("Enter valid name");
				str=sc.next();
				flag=true;
			}
		}while(flag);
    	
    	return str;
	}
	
	public static FileHandler getLog() throws SecurityException, IOException {
		FileHandler fh = new FileHandler("./Transaction.log");
		return fh;
	}
	
	public static FileHandler getOutputLog() throws SecurityException, IOException {
		FileHandler fh = new FileHandler("C:\\Users\\Manoj\\Documents\\Programming software in BE\\MCDA5510_Assignments\\Assignment2\\Output\\Output.log");
		return fh;
	}
	
	public static void postLog(FileHandler fh,String str) { 
		Logger logger=Logger.getLogger("Transactionlog");
	    try {     
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  
	        logger.info(str);
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } 
	}
	
	public static String creditCardCheck(ArrayList<String> Cards,ArrayList<Integer> Prefix,ArrayList<Integer> Length) {
		System.out.println("Select any option by entering....");
		
		boolean inputFlag=false;
		String cardNumber = null;
		int cardType;
		do {
			System.out.println("1."+Cards.get(3));
			System.out.println("2."+Cards.get(7));
			System.out.println("3."+Cards.get(0));
			cardType=sc.nextInt();
			if(cardType < 4 && cardType > 0) {
				boolean validCardNumber=false;
				System.out.println("Enter Card Number");
				do {		
					cardNumber=sc.next();
					if(cardNumber.length()>2) {
						int MC=Integer.parseInt(cardNumber.substring(0, 2));
						int V=Integer.parseInt(cardNumber.substring(0, 1));
						
							if(cardType==1&&cardNumber.length()==Length.get(2)&&(MC<Prefix.get(6)+1&&MC>Prefix.get(2)-1)) {
								return cardNumber;
							}
							else if(cardType==2&&cardNumber.length()==Length.get(3)&&V==Prefix.get(7)) {
								return cardNumber;
							}
							else if(cardType==3&&cardNumber.length()==Length.get(0)&&(MC==Prefix.get(0)||MC==Prefix.get(1))) {
								return cardNumber;
							}
							else {
								validCardNumber=true;
								System.out.println("Enter valid card number...");
							}
					}
					else {
						validCardNumber=true;
						System.out.println("Enter valid card number...");
					}
					
				}while(validCardNumber);
					}
			else {
				inputFlag=true;
				System.out.println("Please enter valid Input.....");
				
			}
		}
		while(inputFlag);		
		return cardNumber;
	}
	
	public static void main(String[] args) throws SecurityException, IOException {
		MySQLAccess dao = new MySQLAccess();
		FileHandler logfh=getLog();
		FileHandler logOutputlog=getLog();
		
		try {
			ConnectionFactory factory = new ConnectionFactory();
			Connection connection = factory.getConnection("mySQLJDBC");
			Transaction trxnn=new Transaction();
			int input=Start();
			boolean create, update, remove; 
			dao.CardResultset(connection);
			ArrayList<String> Cardtype=dao.getCards();
			ArrayList<Integer> prefix=dao.getPrefix();
			ArrayList<Integer> Length=dao.getLength();
			if(input==1) {
				Transaction createTrxn=PostTransaction(trxnn,Cardtype,prefix,Length);
					create=dao.CreateTransaction(connection, createTrxn);
					if(create) {
						System.out.println("\n"+create+": Created Successfully");
						postLog(logfh,create+": Created Successfully");
						postLog(logOutputlog,create+": Created Successfully");
					}
					else {
						System.out.println(create+": Not Created");
						postLog(logfh,create+": Not Created");
						postLog(logOutputlog,create+": Not Created");
					}			
			}
			else if(input==2) {
				Transaction updateTraxn=PostTransaction(trxnn,Cardtype,prefix,Length);
				update=dao.UpdateTransaction(connection, updateTraxn);
				if(update) {
					System.out.println(update+": Updated Successfully");
					postLog(logfh,update+": Updated Successfully");
					postLog(logOutputlog,update+": Updated Successfully");
				}
				else {
					System.out.println(update+": Not Updated");
					postLog(logfh,update+": Not Updated");
					postLog(logOutputlog,update+": Not Updated");
				}
			}
			else if(input==3) {
				Transaction trxn = dao.getTransaction(connection,get());
				String str=trxn.getID()+" "+trxn.getCardNumber()+" | "+trxn.getUnitPrice()+" | "+trxn.getQuantity()+" | "+trxn.getTotalPrice()
				+" | "+trxn.getExpDate()+" | "+trxn.getCreatedOn()+" | "+trxn.getCreatedBy();
				System.out.println(str);
				postLog(logfh,str);
				postLog(logOutputlog,str);
			}
			else {
				remove=dao.removeTransaction(connection, get());
				if(remove) {
					System.out.println(remove+": Removed Successfully");
					postLog(logfh,remove+": Removed Successfully");
					postLog(logOutputlog,remove+": Removed Successfully");
				}
				else {
					System.out.println(remove+": Not Removed");
					postLog(logfh,remove+": Not Removed");
					postLog(logOutputlog,remove+": Not Removed");
				}
			}			
			if (connection != null) {
				connection.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
