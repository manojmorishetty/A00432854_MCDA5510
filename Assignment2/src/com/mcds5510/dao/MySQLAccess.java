package com.mcds5510.dao;

/**
 * Original source code from 
 * http://www.vogella.com/tutorials/MySQLJava/article.html
 * 
**/

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mcds5510.Entity.*;


public class MySQLAccess {
	
	ArrayList<String> cardTypes;
	ArrayList<Integer> prefix;
	ArrayList<Integer> length;

	public boolean CreateTransaction(Connection connection,Transaction trxn) {
		boolean flag=false;
		String CreateCheckquery="SELECT * FROM JAVA_ASSIGNMENT2.TRANSACTION WHERE ID="+trxn.getID();
		Transaction results = new Transaction();
		results=getTrasactionObject(connection,CreateCheckquery);
		if(results.getID()!=trxn.getID()) {
			int totalPrice=Integer.parseInt(trxn.getQuantity())*trxn.getUnitPrice();
			String query="INSERT INTO JAVA_ASSIGNMENT2.TRANSACTION VALUES ("+trxn.getID()+",'"+trxn.getNameOnCard()+"','"+trxn.getCardNumber()
			  +"',"+trxn.getUnitPrice()+","+trxn.getQuantity()+","+totalPrice+",'"+trxn.getExpDate()+"','"
		      +trxn.getCreatedOn()+"','"+System.getProperty("user.name")+"')";
			flag=postQuery(connection,query);
		}
		else {
			System.out.println("ID Already exists please update");
		}
		return flag;
	}
	
	public boolean UpdateTransaction(Connection connection,Transaction trxn) {
		boolean flag=false;
		String Updatecheckquery="SELECT * FROM JAVA_ASSIGNMENT2.TRANSACTION WHERE ID="+trxn.getID();
		Transaction results = new Transaction();
		results=getTrasactionObject(connection,Updatecheckquery);
		if(results.getID()==trxn.getID()) {
			int totalPrice=Integer.parseInt(trxn.getQuantity())*trxn.getUnitPrice();
			String Insertquery="UPDATE JAVA_ASSIGNMENT2.TRANSACTION SET NameOnCard = '"+trxn.getNameOnCard()+"', CardNumber = '"+trxn.getCardNumber()
			  +"', UnitPrice = "+trxn.getUnitPrice()+", Quantity = "+trxn.getQuantity()+", TotalPrice = "+totalPrice+", ExpDate = '"+trxn.getExpDate()+
			  "', CreatedOn = '"+trxn.getCreatedOn()+"', CreatedBy = '"+System.getProperty("user.name")+"' WHERE ID="+trxn.getID();
		flag=postQuery(connection,Insertquery);
		
		}
		else  {
			System.out.println("ID not exists please create new transaction");
		}			
		return flag;
	}
	
	public Transaction getTransaction(Connection connection,int ID){
		Transaction results = new Transaction();
		String getTransactionquery="SELECT * FROM JAVA_ASSIGNMENT2.TRANSACTION WHERE ID="+ID;
		results=getTrasactionObject(connection,getTransactionquery);
		return results;
	}
	
	
	public boolean removeTransaction(Connection connection,int ID){
		String getTransactionquery="DELETE FROM JAVA_ASSIGNMENT2.TRANSACTION WHERE ID="+ID;
		boolean flag=postQuery(connection,getTransactionquery);
		return flag;
	}
	
	private Transaction getTrasactionObject(Connection connection,String query) {
		Statement statement = null;
		ResultSet resultSet = null;
		Transaction results = new Transaction();
		try {
			statement=connection.createStatement();
			resultSet = getResultSet(statement,query);
			results = ToObjectfromResultset(resultSet);
			
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;		
	}
	
	
	private ResultSet getResultSet(Statement statement, String query) {
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(query);		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	private Transaction ToObjectfromResultset(ResultSet resultSet) throws SQLException{
		Transaction trxn = new Transaction();
		if(resultSet.next()) {
			trxn.setID(resultSet.getInt("ID"));
			trxn.setNameOnCard(resultSet.getString("NameOnCard"));
			trxn.setCardNumber(resultSet.getString("CardNumber"));
			trxn.setUnitPrice(resultSet.getInt("UnitPrice"));
			trxn.setQuantity(resultSet.getString("Quantity"));
			trxn.setTotalPrice(resultSet.getInt("TotalPrice"));
			trxn.setExpDate(resultSet.getString("ExpDate"));
			trxn.setCreatedOn(resultSet.getString("CreatedOn"));
			trxn.setCreatedBy(resultSet.getString("CreatedBy"));
		}
		return trxn;	
	}
	
	
	private boolean postQuery(Connection connection,String query) {
		Statement statement = null;
		boolean flag=false;
		try {
			statement=connection.createStatement();
			flag=insertRow(statement,query);

			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	private boolean insertRow(Statement statement, String query) {
		boolean flag=false;
		try {
			statement.executeUpdate(query);
			flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public void CardResultset(Connection connection) throws SQLException{
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement=connection.createStatement();
			resultSet = getResultSet(statement,"SELECT * FROM JAVA_ASSIGNMENT2.Cardtype");
			cardTypes=new ArrayList<String>();
			prefix=new ArrayList<Integer>();
			length=new ArrayList<Integer>();
			while(resultSet.next()) {
				cardTypes.add(resultSet.getString("Creditcard"));
				prefix.add(resultSet.getInt("Prefix"));
				length.add(resultSet.getInt("Length"));
			}			
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public ArrayList<String> getCards(){
		return cardTypes;
	}
	
	public ArrayList<Integer> getPrefix(){
		return prefix;
	}
	
	public ArrayList<Integer> getLength(){
		return length;
}
	
}


