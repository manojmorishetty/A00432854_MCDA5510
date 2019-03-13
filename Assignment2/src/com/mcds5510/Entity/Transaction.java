package com.mcds5510.Entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Transaction{

	private String nameOnCard, cardNumber, Quantity, setCreatedOn, ExpDate, CreatedBy;
	
	private int ID, UnitPrice, TotalPrice;
	
	java.util.Date date = new Date(ID);
	public int getID() { return ID; }
	
	public String getCardNumber() { return cardNumber; }
	
	public String getNameOnCard() { return nameOnCard; }
	
	public int getUnitPrice() { return UnitPrice; }
	
	public String getQuantity() { return Quantity; }
	
	public int getTotalPrice() { return TotalPrice; }
	
	public String getExpDate() { return ExpDate; }
	
	public Timestamp getCreatedOn() { return 
			new java.sql.Timestamp(date.getTime());
	}
	
	public String getCreatedBy() { return CreatedBy; }	
	
	public void setID(int ID) { this.ID = ID; }
	
	public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
	
	public void setNameOnCard(String nameOnCard) { this.nameOnCard = nameOnCard; }	
	
	public void setUnitPrice(int UnitPrice) { this.UnitPrice = UnitPrice; }
	
	public void setQuantity(String Quantity) { this.Quantity = Quantity; }	
	
	public void setTotalPrice(int TotalPrice) { this.TotalPrice = TotalPrice; }	
	
	public void setExpDate(String ExpDate) { this.ExpDate = ExpDate; }
	
	public void setCreatedOn(String setCreatedOn) {
		this.setCreatedOn = setCreatedOn; 
		}	
	
	public void setCreatedBy(String CreatedBy) { this.CreatedBy = CreatedBy; }
	
	public String toString(){
		
		String results = new String();
		
		results = "[NameOnCard: " + nameOnCard +",CardNumber: " + cardNumber+"]";
		return results;

	}

	
	
}
