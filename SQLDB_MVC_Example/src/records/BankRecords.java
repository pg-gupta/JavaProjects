package records;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Processing Client's Data Application
 * This class contains all the attributes of the client 
 * and processes the data of the file to fetch the client details 
 */

/**
 * 
 * @author Pooja Gupta (A20413675) Date:02/9/2018 Lab: #3 File: BankRecords.java
 */
public class BankRecords extends Client {

	// initialize BankRecords
	 protected static BankRecords[] bankRecords = null;

	// arrayList to hold excel files rows and columns
	static ArrayList<List<String>> bankRecordsFromFile = new ArrayList<>();

	// instance Fields
	private String id;
	private int age;
	private String sex;
	private String region;
	private double Income;
	private String married;
	private int children;
	private String car;
	private String save_act;
	private String current_act;
	private String mortgage;
	private String pep;

	// getters and setters
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the income
	 */
	public double getIncome() {
		return Income;
	}

	/**
	 * @param income
	 *            the income to set
	 */
	public void setIncome(double income) {
		Income = income;
	}

	/**
	 * @return the married
	 */
	public String getMarried() {
		return married;
	}

	/**
	 * @param married
	 *            the married to set
	 */
	public void setMarried(String married) {
		this.married = married;
	}

	/**
	 * @return the children
	 */
	public int getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(int children) {
		this.children = children;
	}

	/**
	 * @return the car
	 */
	public String getCar() {
		return car;
	}

	/**
	 * @param car
	 *            the car to set
	 */
	public void setCar(String car) {
		this.car = car;
	}

	/**
	 * @return the save_act
	 */
	public String getSave_act() {
		return save_act;
	}

	/**
	 * @param save_act
	 *            the save_act to set
	 */
	public void setSave_act(String save_act) {
		this.save_act = save_act;
	}

	/**
	 * @return the current_act
	 */
	public String getCurrent_act() {
		return current_act;
	}

	/**
	 * @param current_act
	 *            the current_act to set
	 */
	public void setCurrent_act(String current_act) {
		this.current_act = current_act;
	}

	/**
	 * @return the mortgage
	 */
	public String getMortgage() {
		return mortgage;
	}

	/**
	 * @param mortgage
	 *            the mortgage to set
	 */
	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}

	/**
	 * @return the pep
	 */
	public String getPep() {
		return pep;
	}

	/**
	 * @param pep
	 *            the pep to set
	 */
	public void setPep(String pep) {
		this.pep = pep;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Client#readData()
	 */
	@Override
	public void readData() throws FileNotFoundException, IOException {

		// declare and initialize the reader object with the file path of the file to be
		// read
		BufferedReader reader = new BufferedReader(new FileReader(new File("bank-detail.csv")));

		// read the data line by line and store it in arraylist i.e. bankRecordsfromFile
		String line;
		while ((line = reader.readLine()) != null) {

			// data in the file is comma separated hence parsing it using (,) and
			// storing it as a list in ArrayList
			bankRecordsFromFile.add(Arrays.asList(line.split(",")));
		}
		// close reader after processing the file
		reader.close();

		// process the data which is read
		processData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Client#processData()
	 */
	@Override
	public void processData() {
		// initialize index to create BankRecords object when iterating through
		// ArrayList
		int index = 0;
		// initialize BankRecords object using the count of records in ArrayList
		bankRecords = new BankRecords[bankRecordsFromFile.size()];

		// read each list (record) from ArrayList (bankRecordsFromFile) and create
		// BankRecord objects from it
		for (List<String> record : bankRecordsFromFile) {
			bankRecords[index] = new BankRecords();
			bankRecords[index].setId(record.get(0));
			bankRecords[index].setAge(Integer.parseInt(record.get(1)));
			bankRecords[index].setSex(record.get(2));
			bankRecords[index].setRegion(record.get(3));
			bankRecords[index].setIncome(Double.parseDouble(record.get(4)));
			bankRecords[index].setMarried(record.get(5));
			bankRecords[index].setChildren(Integer.parseInt(record.get(6)));
			bankRecords[index].setCar(record.get(7));
			bankRecords[index].setSave_act(record.get(8));
			bankRecords[index].setCurrent_act(record.get(9));
			bankRecords[index].setMortgage(record.get(10));
			bankRecords[index].setPep(record.get(11));
			index++;
		}

		// print the data of the BankRecords array
		//printData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Client#printData()
	 */
	@Override
	public void printData() {

		// number of records to be print
		int n = 25;
		System.out.println(String.format("%nPrinting first %d of %d records.......%n", n, bankRecords.length));

		// display the heading
		System.out.println(String.format("%-10s %-5s %-10s %-15s %-10s %-10s", "ID", "AGE", "SEX", "REGION", "INCOME",
				"MORTGAGE"));
		System.out.println("--------------------------------------------------------------------------");

		// print the required records with all its fields
		for (int i = 0; i < n; i++) {
			System.out.println(String.format("%-10s %-5d %-10s %-15s %-10.2f %-10s", bankRecords[i].getId(),
					bankRecords[i].getAge(), bankRecords[i].getSex(), bankRecords[i].getRegion(),
					bankRecords[i].getIncome(), bankRecords[i].getMortgage()));

		}

	}

}
