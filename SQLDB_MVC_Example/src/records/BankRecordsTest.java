package records;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Processing Client's Data Application
 * This is the class where the methods of the BankRecords class is utilized 
 * to display the details of the clients
 */

/**
 * 
 * @author Pooja Gupta (A20413675) Date: 02/28/2018 Lab: #3
 *         File:BankRecordsTest.java
 */
public class BankRecordsTest {

	public static void main(String[] args) {

		// create a BankRecord object
		BankRecords bankRecord = new BankRecords();
		// call readData method of the BankRecord which will read data from file,
		// process
		// it and print the records
		try {
			bankRecord.readData();
		} catch (FileNotFoundException e) { // handle exception if file to be read is missing
			System.out.println("No such file exist. " + e.getMessage());
		} catch (IOException e) {// handle exception if something goes wrong while reading the file
			System.out.println("Something went wrong while reading the file. " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Something went wrong!");
		}

		try {
			Records record = new Records();
			record.GetAnalysis();
		} catch (Exception e) {
			System.out.println("Something went wrong!");
		}
		
		System.out.println(
				"---------------------------------------------------------------------------\nThank You for using the Application!!\n");
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println("Cur dt=" + timeStamp + "\nProgrammed by Pooja Gupta (A20413675)\n");
	}

}
