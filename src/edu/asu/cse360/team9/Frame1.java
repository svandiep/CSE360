package edu.asu.cse360.team9;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
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
	private JTable table_1;
	private Controller controller;

	/**
	 * Launch the application.
	 */
	
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
		controller = new Controller();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNetworkPathAnalyzer = new JFrame();
		frmNetworkPathAnalyzer.setTitle("Network Path Analyzer");
		frmNetworkPathAnalyzer.setBounds(100, 100, 587, 459);
		frmNetworkPathAnalyzer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNetworkPathAnalyzer.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Calculate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Cycle detected!");
				
				// We will build the list of records from the table data on the form,
				// and then provide it to the controller to do the calculation.
				// The result will be passed to the Paths frame on launch to be displayed.
				ArrayList<Record> records = new ArrayList<Record>();
				
				// From PERT slides:
				/*
				 	Activity		Predecessor		Weeks		comment
				 	{dest}			{source}
				 	A				--				2			0 (not entirely sure about this yet)
				 	B				A				4			1
				 	C				B				5			2
				 	D				B,F				6			3
				 	E				C,D				3			4
				 	F				A				5			5
				 	
				 	
				 	   
				 	Ex: 
				 	
				 	
				 	     2         4        5
				 	[A] ----> [B] ----> [C] -------+
				 	 |         |   4               +----> [E] 
				 	 |         +------+          6 |
				 	 |      2         +----> [D] --+
				     +--------> [F] --+ 5
				 
				 
				 	Algorithm:
				 	1) Build paired list of activities and durations P
				 	2) Go through list, and build edge where source is predecessor, destination
				 	   is activity, and duration is P[predecessor].duration.
				 
				 */
				//Get data from table with loop
				int i = 0;
				int duration;
				String activity;
				String dependency;
				while(table_1.getModel().getValueAt(i, 0) != null){
					try
					{
				activity = table_1.getModel().getValueAt(i, 0).toString();
					}
				      catch (Exception e1) {
				    	  JOptionPane.showMessageDialog(null, "Invalid Activity");
				    	  activity = "";
				      }
					try
					{
				duration = Integer.parseInt( table_1.getValueAt(i, 1).toString() );
					}
				      catch (Exception e1) {
				    	  JOptionPane.showMessageDialog(null, "Invalid Duration");
				    	  duration = 0;
				      }
				if(table_1.getModel().getValueAt(i, 2) != null) {	
				dependency = table_1.getModel().getValueAt(i, 2).toString();
				}
				else {dependency = "";}
				records.add(new Record(activity, duration, dependency));
				i++;
				}
				
				// This is all PROOF-OF-CONCEPT -- NOT FINAL:
				//records.add(new Record("A", 2, ""));
				//records.add(new Record("B", 4, "A"));
				//records.add(new Record("C", 5, "B"));
				//records.add(new Record("D", 6, "B,F"));
				//records.add(new Record("E", 3, "C,D"));
				//records.add(new Record("F", 5, "A"));
				
				controller.doCalculation(records);
				
				// Our results table:
				/*
				Paths pathFrame = new Paths(controller);
				pathFrame.setVisible(true);
				*/
				
				// PROOF OF CONCEPT - NOT FINAL:
				ArrayList<Result> results = controller.getResults();
				for(Result r : results)
				{
					System.out.println("The Path: " + r.getPath() + " takes " + r.getDuration() + " weeks.");
				}
			}
		});
		btnNewButton.setBounds(449, 329, 97, 25);
		frmNetworkPathAnalyzer.getContentPane().add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(340, 329, 97, 25);
		frmNetworkPathAnalyzer.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 60, 509, 240);
		frmNetworkPathAnalyzer.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[]  {
				"Activity", "Duration", "Dependencies"
			}
		));
		
		JMenuBar menuBar = new JMenuBar();
		frmNetworkPathAnalyzer.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewDiagram = new JMenuItem("New Diagram");
		mnFile.add(mntmNewDiagram);
		
		JMenuItem mntmCalculate = new JMenuItem("Calculate");
		mnFile.add(mntmCalculate);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0); 
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmVersion = new JMenuItem("Version");
		mnAbout.add(mntmVersion);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmUserGuide = new JMenuItem("User Guide");
		mnHelp.add(mntmUserGuide);
	}
}
