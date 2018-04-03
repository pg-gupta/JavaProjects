package records;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Processing Data Application
 * This application parses client data from the file,
 * processes and displays the details of the clients
 */

/**
 * 
 * @author Pooja Gupta (A20413675) Date:02/9/2018 Lab: #3 File: Client.java
 */
public abstract class Client {
	/**
	 * Method to read data from the file.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public abstract void readData() throws FileNotFoundException, IOException;

	/**
	 * Method to process the data read from the file.
	 */
	public abstract void processData();

	/**
	 * Method to print the data being processed from the file.
	 */
	public abstract void printData();
}
