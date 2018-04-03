package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.DaoModel;
import records.BankRecords;
import views.LoanView;
/**
 * 
 * @author Pooja Gupta
 * Date:04/03/2018
 * Lab: #4
 * File: LoanProcessing.java
 *
 */
public class LoanProcessing extends BankRecords {

	public static void main(String[] args) {

		BankRecords records = new BankRecords();
		// read data from file which needs to be inserted into table
		try {
			records.readData();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		records.processData();
		DaoModel daoModel = new DaoModel();
		// create table
		//daoModel.createTable();
		// insert records read from the file into created table
		//daoModel.insertRecords(bankRecords);
		ResultSet resultSet;
		try {
			resultSet = daoModel.retrieveRecods();
			new LoanView().runView(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
