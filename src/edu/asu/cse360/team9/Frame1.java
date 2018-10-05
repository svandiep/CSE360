package edu.asu.cse360.team9;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.TextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;


public class Frame1 {

	private JFrame frmNetworkPathAnalyzer;
	private JTable networkTable;
	private DefaultTableModel model;
	private Controller controller;

	/**
	 * Launch the application.
	 */
	private void calculate() {
		// Don't want user calculating when there's nothing there,
		// and an edit will create a new row, so a valid graph will
		// have at least a row count of 2.
		if(model.getRowCount() == 1)
			return;

		
		// We will build the list of records from the table data on the form,
		// and then provide it to the controller to do the calculation.
		// The result will be passed to the Paths frame on launch to be displayed.
		controller = new Controller();
		ArrayList<Record> records = new ArrayList<Record>();
	
		if (networkTable.isEditing())
		    networkTable.getCellEditor().stopCellEditing();
		
		int duration = 0;
		String activity;
		String dependency;
		int rows = model.getRowCount();
		for(int idx = 0; idx < rows; idx++)
		{
			if(model.getValueAt(idx, 0).toString().isEmpty())
			{
				// We skip rows that don't have an activity.
				continue;
			}
			
			activity = model.getValueAt(idx, 0).toString();
			
			try {
				duration = Integer.parseInt(model.getValueAt(idx, 2).toString());
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Invalid duration provided.");
			}
			
			dependency = model.getValueAt(idx, 1).toString();
			records.add(new Record(activity, duration, dependency));
		}
		
		ArrayList<Result> results = new ArrayList<>();
		try {
			controller.doCalculation(records);
			results = controller.getResults();
			
			// Our results table:
			Paths pathFrame = new Paths(results);
			pathFrame.setVisible(true);
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		
		// Our results table:
		//Paths pathFrame = new Paths(results);
		//pathFrame.setVisible(true);
	}

		
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frmNetworkPathAnalyzer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNetworkPathAnalyzer = new JFrame();
		frmNetworkPathAnalyzer.setTitle("Network Path Analyzer");
		frmNetworkPathAnalyzer.setBounds(100, 100, 800, 459);
		frmNetworkPathAnalyzer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNetworkPathAnalyzer.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Calculate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		});
		btnNewButton.setBounds(449, 329, 97, 25);
		frmNetworkPathAnalyzer.getContentPane().add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				model.addRow(new Object[] { "", "", ""});
			}
		});
		btnClear.setBounds(340, 329, 97, 25);
		frmNetworkPathAnalyzer.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 62, 701, 240);
		frmNetworkPathAnalyzer.getContentPane().add(scrollPane);
		
		networkTable = new JTable();
		scrollPane.setViewportView(networkTable);
		model = new DefaultTableModel(
				new Object[][] { {"", "", ""} },
				
				new String[]  { "Activity", "Dependencies", "Duration" }
		);
        networkTable.setModel(model);
        networkTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        networkTable.getColumnModel().getColumn(1).setPreferredWidth(400);
        networkTable.getColumnModel().getColumn(2).setPreferredWidth(15);
		model.addTableModelListener(new TableModelListener() {
			  @Override
			  public void tableChanged(TableModelEvent e) {
			    if (e.getType() == TableModelEvent.UPDATE && (e.getLastRow() + 1) == model.getRowCount()) {
			    	System.out.println("tableChanged()");
			    	
			    	model.addRow(new Object[] { "", "", ""});
			    }
			  }
		});
		
		JMenuBar menuBar = new JMenuBar();
		frmNetworkPathAnalyzer.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewDiagram = new JMenuItem("New Diagram");
		mntmNewDiagram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.setRowCount(0);
				model.addRow(new Object[] { "", "", ""});	
			}
		});
		mnFile.add(mntmNewDiagram);
		
		JMenuItem mntmCalculate = new JMenuItem("Calculate");
		mntmCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		});
		mnFile.add(mntmCalculate);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0); 
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmUserGuide = new JMenuItem("User Guide");
		mnHelp.add(mntmUserGuide);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmNetworkPathAnalyzer, "Network Path Analyzer, v1.0\r\n" + 
						"Brandon Dressman, Scott VanDiepenbos and Michael Sneberger\r\n" + 
						"Copyright 2018");
			}
		});
		mnHelp.add(mntmAbout);
	}
}
