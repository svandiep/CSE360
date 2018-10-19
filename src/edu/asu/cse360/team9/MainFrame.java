package edu.asu.cse360.team9;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

/**
 * Main frame of application, which provides user ability to enter a collection
 * of activities by rows along with their dependencies and durations.
 * 
 * Upon completion, user may click calculate or clear
 * 	- Calculate: Identifies all paths between start and final activities.
 *  - Clear: Removes all table data.
 *  
 *  Additionally, menu items are provided:
 *  - New Diagram: See Clear
 *  - Calculate: See Calculate
 *  - Exit: exits the application
 *  
 *  - About: Shows application authors, version, and copyright.
 *  - User Guide: Displays user guide
 *
 */
public class MainFrame {

	private JFrame frmNetworkPathAnalyzer;
	private JTable networkTable;
	
	
	// Data of the table:
	private DefaultTableModel model;
	
	// Bridge between GUI and model of source code.
	private Controller controller;
	
	
	// Performs the calculation of a given collection of rows, along
	// with error handling.
	private void calculate() {

		
		// We will build the list of records from the table data on the form,
		// and then provide it to the controller to do the calculation.
		// The result will be passed to the Paths frame on launch to be displayed.
		controller = new Controller();
		ArrayList<Record> records = new ArrayList<Record>();
	
		// When user is working in a cell, and selects calculate, their current edit
		// is not saved, so this forces a commit on the table.
		if (networkTable.isEditing())
		    networkTable.getCellEditor().stopCellEditing();
		
		int duration = 0;
		String activity;
		String dependency;
		
		// We require that dependencies exist as activities, so first step is to load
		// all activities into a list.
		ArrayList<String> activities = new ArrayList<>();
		activities.add("");
		for(int idx = 0; idx < model.getRowCount() - 1; idx++) {
			// We skip rows that don't have an activity.
			if(model.getValueAt(idx, 0).toString().isEmpty())
				continue;
			
			activity = model.getValueAt(idx, 0).toString();
			activities.add(activity.trim());
		}
		
		
		// Iterate over all rows in the table:
		for(int idx = 0; idx < activities.size(); idx++)
		{
			activity = activities.get(idx);
			// We skip rows that don't have an activity.
			if(activity.isEmpty())
				continue;
			
			dependency = model.getValueAt(idx - 1, 1).toString();
			
			try {
				duration = Integer.parseInt(model.getValueAt(idx - 1, 2).toString());
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Invalid duration provided at row " + (idx+1) , "Error", 1);
				return;
			}
			
			//Check if dependency is an activity
			//Throws error if dependency is not found in activity array
			String[] dependencies = dependency.split(",");

			for(int i = 0; i < dependencies.length; i++) {
				dependencies[i] = dependencies[i].trim();
				if(!activities.contains(dependencies[i])) {
					JOptionPane.showMessageDialog(null, "Dependency \"" + dependencies[i] + "\" at row " + (idx+1) + " is not an activity.", "Error", 1);
					return;
				}
			}
			
			// With properly parsed data, we construct a Record object to be
			// used in calculations.
			records.add(new Record(activity, duration, dependencies));
		}
		
		if(records.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please provide a network configuration.", "Error", 1);
			return;
		}
		
		ArrayList<Result> results = new ArrayList<>();
		try {
			controller.populateGraph(records);	//graph is now built
			results = controller.getResults();
			
			// Our results table:
			Paths pathFrame = new Paths(results);
			pathFrame.setLocationRelativeTo(frmNetworkPathAnalyzer);	//center results frame
			pathFrame.setVisible(true);
		}
		catch(IllegalArgumentException ex) { 
			JOptionPane.showMessageDialog(null, "Network graph is not connected.", "Error", 1);
			return;
		}
		// TODO: Handle exceptions better. This is too general.
		catch(Exception ex) {
			System.out.println("General exception: ");
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmNetworkPathAnalyzer.setLocationRelativeTo(null);	//center entry frame
					window.frmNetworkPathAnalyzer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		initialize();
	}

	private void initialize() {
		frmNetworkPathAnalyzer = new JFrame();
		frmNetworkPathAnalyzer.setResizable(false);
		frmNetworkPathAnalyzer.setTitle("Network Path Analyzer");
//		frmNetworkPathAnalyzer.setBounds(100, 100, 800, 459);
		frmNetworkPathAnalyzer.setSize(800, 459);
		frmNetworkPathAnalyzer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNetworkPathAnalyzer.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Calculate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		});
		btnNewButton.setBounds(642, 329, 97, 25);
		frmNetworkPathAnalyzer.getContentPane().add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				model.addRow(new Object[] { "", "", ""});
			}
		});
		btnClear.setBounds(531, 329, 97, 25);
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
		mntmUserGuide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().open(new java.io.File("Resources/TeamProjectPhase1v2.0.pdf"));
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "Resource not available", "Error", 1);
					return;
				}
			}
		});
		mnHelp.add(mntmUserGuide);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmNetworkPathAnalyzer, "Network Path Analyzer, v1.0\r\n" + 
						"Brandon Dressman, Scott VanDiepenbos and Michael Sneberger\r\n" + 
						"Copyright 2018","About", 1);
			}
		});
		mnHelp.add(mntmAbout);
	}
}
