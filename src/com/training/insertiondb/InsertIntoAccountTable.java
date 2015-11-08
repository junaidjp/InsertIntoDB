package com.training.insertiondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.bank.data.BankingDatabase;
import com.bank.entity.Account;
import com.bank.entity.Customer;


/** 
 * Insert into Account table
 * @author junaidpasha
 *
 */
public class InsertIntoAccountTable {
	
	
	public static void main(String args[]) { 
		
	
		
		Connection conn = null;
		try {
			// 1) Load the Database Driver
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankofdanish", "root",
					"junaid123");
			System.out.println("Successfully establised a Ping");
			
			
			//Lets first retrieve Account from BankingDatabase class 
			BankingDatabase bankingDatabase = new BankingDatabase();
			Customer customerone  = bankingDatabase.getCustomerInfo();
			List<Account> accountsList =  customerone.getAccounts();
			
			PreparedStatement insertstatement  = null;
			
			for(int accountIndex = 0 ; accountIndex < accountsList.size() ; accountIndex ++) { 
				
				Account accountone = accountsList.get(accountIndex);
				 String insertSql = "insert into accountinfo (accountNo,accountType, balance) values  ( ? , ? , ? )";
				 
				  insertstatement = conn.prepareStatement(insertSql);
				 insertstatement.setString(1,  accountone.getAccountno());
				 insertstatement.setString(2, accountone.getAccountType());
				 insertstatement.setDouble(3, accountone.getBalance());
				 
				 // Once you have set tje values execute the query 
				 
				 insertstatement.executeUpdate();
				}
			
			
			
			 
			 insertstatement.close();
			 
			 
			
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		
		
	}

}
