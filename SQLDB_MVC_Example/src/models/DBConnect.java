package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @author Pooja Gupta
 * Date:04/03/2018
 * Lab: #4
 * File: DBConnect.java
 *
 */
public class DBConnect {

	static final String DB_URL = "jdbc:mysql://www.papademas.net:3306/510labs?autoReconnect=true&useSSL=false";
	static final String USER = "db510", PASS = "510";
	public Connection connect() throws SQLException {
		return DriverManager.getConnection(DB_URL,USER,PASS);
	}

}
