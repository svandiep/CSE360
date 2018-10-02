package edu.asu.cse360.team9;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.*;

public class Paths extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	
	// Provided via constructor
	private Controller controller;
	
	
	/**
	 * Launch the application.
	 */
	/* No main needed here
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Paths frame = new Paths();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the frame.
	 */
	public Paths(Controller controller) {
		
		// Keep reference to controller so the results can be polled later.
		this.controller = controller;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.setBounds(450, 238, 97, 25);
		contentPane.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 13, 513, 212);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		model = new DefaultTableModel(
				new Object[][] {
				{"", ""}
			},
			new String[] {
				"Path", "Duration"
			}
		);
		table.setModel(model);
		model.addTableModelListener(new TableModelListener() {
			  @Override
			  public void tableChanged(TableModelEvent e) {
			    if (e.getType() == TableModelEvent.UPDATE && (e.getLastRow() + 1) == model.getRowCount()) {
			    	System.out.println("tableChanged()");
			    	
			    	model.addRow(new Object[] { "", ""});
			    }
			  }
			});
		//Column Widths
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(62);
		table.getColumnModel().getColumn(1).setMinWidth(10);
		table.getColumnModel().getColumn(1).setMaxWidth(2147483644);
		
		// We will use the populated data to fill the table
		ArrayList<Result> results = controller.getResults();
	}
}
