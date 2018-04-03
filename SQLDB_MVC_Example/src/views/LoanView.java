package views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * @author Pooja Gupta
 * Date:04/03/2018
 * Lab: #4
 * File: LoanView.java
 *
 */
public class LoanView {

	public void runView(ResultSet resultSet) throws SQLException {
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Vector<String> column = new Vector<String>();

		java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
		int columns = metaData.getColumnCount();
		// get columns from the table metadata
		for (int i = 1; i <= columns; i++) {
			column.add(metaData.getColumnName(i));
		}
		// get row data from table
		while (resultSet.next()) {
			Vector<Object> row = new Vector<Object>(columns);
			for (int i = 1; i <= columns; i++) {
				row.addElement((resultSet.getObject(i)));
			}
			data.addElement(row);
		}

		DefaultTableModel model = new DefaultTableModel(data, column);
		JTable table = new JTable(model);
		JFrame frame = new JFrame("Loan Details");
		frame.setSize(700, 200);
		frame.add(new JScrollPane(table));
		frame.setDefaultCloseOperation(0);
		frame.pack();
		frame.setVisible(true);
		resultSet.close();

	}

}
