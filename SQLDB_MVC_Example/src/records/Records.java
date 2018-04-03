package records;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Analyzing Client's Data Application
 * This is the class where the BankRecords are analyzed 
 * and the data is displayed for certain analysis
 */

/**
 * 
 * @author Pooja Gupta (A20413675) Date: 02/28/2018 Lab: #3 File:Records.java
 */
public class Records extends BankRecords {

	BankRecords[] bankrecordsArray = BankRecords.bankRecords;
	ArrayList<BankRecords[]> filteredBankRecordsByRegion = new ArrayList<BankRecords[]>();
	List<BankRecords> bankRecords = Arrays.asList(bankrecordsArray);
	String output;

	static FileWriter fileWriter = null;

	public Records() {
		try {
			fileWriter = new FileWriter("bankrecords.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// comparator to sort records primarily by location and secondarily by income
	class LocationIncomeComparator implements Comparator<BankRecords> {

		@Override
		public int compare(BankRecords obj0, BankRecords obj1) {
			if (!obj0.getRegion().equals(obj1.getRegion())) // primary sort
				return obj0.getRegion().compareTo(obj1.getRegion());
			else
				return obj0.getIncome() - obj1.getIncome() < 0 ? -1 : 1; // secondary sort
		}

	}

	// comparator to sort records by gender
	class GenderComparator implements Comparator<BankRecords> {

		@Override
		public int compare(BankRecords obj0, BankRecords obj1) {
			return obj0.getSex().compareTo(obj1.getSex());
		}

	}

	// comparator to sort records primarily by gender and secondarily by children
	class GenderChildrenComparator implements Comparator<BankRecords> {

		@Override
		public int compare(BankRecords obj0, BankRecords obj1) {
			if (!obj0.getSex().equals(obj1.getSex()))
				return obj0.getSex().compareTo(obj1.getSex()); // primary sort
			return obj0.getChildren() - obj1.getChildren(); // secondary sort
		}

	}

	/**
	 * get the distinct regions of the bank records
	 * 
	 * @return unique regions
	 */
	private List<String> GetRegions() {
		HashSet<String> regionSet = new HashSet<String>();
		regionSet.addAll(bankRecords.stream().map(BankRecords::getRegion).collect(Collectors.toList()));
		List<String> regions = new ArrayList<String>(regionSet);
		Collections.sort(regions);
		return regions;
	}

	/**
	 * gets average income per region
	 */
	private void GetAvgIncomeByRegion() {

		// initialize variables
		double sum = 0;
		int count = 0;

		for (BankRecords[] records : filteredBankRecordsByRegion) {
			for (BankRecords record : records) {
				sum += record.getIncome();
				count++;
			}

			output = String.format("%s region avg.income:  $%.2f", records[0].getRegion(), sum / count);

			System.out.println(output);
			WriteToFile(output);

			sum = 0;
			count = 0;
		}
	}

	private void WriteToFile(String output) {
		try {
			fileWriter.write(output + "\n");

			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * gets the maximum income per region
	 */
	private void GetMaxIncomeByRegion() {
		for (BankRecords[] records : filteredBankRecordsByRegion) {
			Arrays.sort(records, new LocationIncomeComparator());
			output = String.format("%s region Min inc:  $%.2f", records[0].getRegion(), records[0].getIncome());
			System.out.println(output);
			WriteToFile(output);

		}
	}

	/**
	 * gets the minimum income per region
	 */
	private void GetMinIncomeByRegion() {
		for (BankRecords[] records : filteredBankRecordsByRegion) {

			Arrays.sort(records, new LocationIncomeComparator());
			output = String.format("%s region Max inc:  $%.2f", records[0].getRegion(),
					records[records.length - 1].getIncome());
			System.out.println(output);
			WriteToFile(output);

		}
	}

	/**
	 * gets the number of females with mortgages and savings account per region
	 */
	private void GetFemaleWithMortAndSavingAccountByRegion() {

		// for records per region
		for (BankRecords[] records : filteredBankRecordsByRegion) {
			// sort records by gender
			Arrays.sort(records, new GenderComparator());
			// filter and count the number of females
			long femalsWithMortAndSavAccount = Arrays.asList(records).stream().filter(
					p -> p.getSex().equals("FEMALE") && p.getMortgage().equals("YES") && p.getSave_act().equals("YES"))
					.count();
			output = String.format("%s region females with mort and savings account: %d", records[0].getRegion(),
					femalsWithMortAndSavAccount);
			System.out.println(output);
			WriteToFile(output);

		}
	}

	/**
	 * gets number of males with car and 1 child
	 */
	private void GetMalesWithCarAndAChild() {
		// for records per region
		for (BankRecords[] records : filteredBankRecordsByRegion) {
			// sort by children
			Arrays.sort(records, new GenderChildrenComparator());
			long malesWithCarAndAChild = Arrays.asList(records).stream()
					.filter(p -> p.getSex().equals("MALE") && p.getCar().equals("YES") && p.getChildren() == 1).count();
			output = String.format("%s region males with car and 1 child: %d", records[0].getRegion(),
					malesWithCarAndAChild);
			System.out.println(output);
			WriteToFile(output);

		}
	}

	/**
	 * gets the analyzed data of the Bank Records
	 */
	public void GetAnalysis() {

		try {
			System.out.println("Data analytics results:\n");
			WriteToFile("Data Analysis Results:");
			WriteToFile("\n");
			// get the regions from where the clients
			List<String> regionSet = GetRegions();

			// sort bank records by region
			Arrays.sort(bankrecordsArray, new LocationIncomeComparator());
			List<BankRecords> bankRecords = Arrays.asList(bankrecordsArray);

			// get lists of records based on region and sorted by Income
			for (String region : regionSet) {
				filteredBankRecordsByRegion.add(
						bankRecords.stream().filter(p -> p.getRegion().equals(region)).toArray(BankRecords[]::new));

			}

			// avg Income by region
			GetAvgIncomeByRegion();
			WriteToFile("\n");
			System.out.println();

			// min Income per region
			GetMinIncomeByRegion();
			WriteToFile("\n");
			System.out.println();

			// max Income per region
			GetMaxIncomeByRegion();
			WriteToFile("\n");
			System.out.println();

			// female with mortgage and savings account per region
			GetFemaleWithMortAndSavingAccountByRegion();
			WriteToFile("\n");
			System.out.println();

			// males with car and 1 child per region
			GetMalesWithCarAndAChild();
			WriteToFile("\n");

		} catch (Exception e) {
			System.out.println("Something went wrong! " + e.getMessage());
		}

		try {
			WriteToFile(
					"---------------------------------------------------------------------------\nThank You for using the Application!!\n");
			String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
			WriteToFile("Cur dt=" + timeStamp + "\nProgrammed by Pooja Gupta (A20413675)\n");
		} catch (Exception e) {
			System.out.println("Something went wrong! " + e.getMessage());
		}
		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}