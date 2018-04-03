package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import records.BankRecords;

/**
 * 
 * @author Pooja Gupta Date:04/03/2018 Lab: #4 File: DaoModel.java
 *
 */
public class DaoModel {

	// variables
	DBConnect connection = null;
	Statement statement = null;
	String tableName = "p_gupt_tab";

	// constructor
	public DaoModel() {
		connection = new DBConnect();
	}

	/**
	 * method to create table
	 */
	public void createTable() {
		try {
			System.out.println("Connecting to a sql database for creating table..\nConnected successfully...");
			System.out.println("Creating table in the database");

			statement = connection.connect().createStatement();
			// create table query statement
			String createQuery = "CREATE TABLE " + tableName + " (pid INTEGER not NULL AUTO_INCREMENT, "
					+ "id VARCHAR(10), " + "income numeric(8,2), " + "pep VARCHAR(3), " + "PRIMARY KEY(pid))";
			statement.executeUpdate(createQuery);
			System.out.println("Table successfully created..");
			// close the connection
			connection.connect().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to insert Bank records into table
	 * 
	 * @param records
	 *            Bank records list to be inserted
	 */
	public void insertRecords(BankRecords[] records) {
		try {
			System.out.println("Inserting records into table..");
			String insertQuery;
			statement = connection.connect().createStatement();

			// iterate and insert records into table
			for (BankRecords record : records) {
				// create insert query
				insertQuery = "INSERT INTO " + tableName + "(id,income,pep) VALUES('" + record.getId() + "','"
						+ record.getIncome() + "','" + record.getPep() + "')";
				statement.executeUpdate(insertQuery);
			}

			System.out.println("Records successfully inserted..");
			// close the connection
			connection.connect().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * method to get all the records in the table
	 * @return records in the table
	 */
	public ResultSet retrieveRecods() {
		ResultSet results = null;
		System.out.println("Retrieving bank records from the database..");
		try {
			statement = connection.connect().createStatement();
			String getQuery = "SELECT * FROM " + tableName;
			results = statement.executeQuery(getQuery);
			connection.connect().close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
}
