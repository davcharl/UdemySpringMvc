package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class testJdbc {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
		String username = "hbstudent";
		String password = "hbstudent";
		
		try {
			System.out.println("Connecting to db: " + url);
			Connection myConnection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Successful!!!");
			myConnection.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
