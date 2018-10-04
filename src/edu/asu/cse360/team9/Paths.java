package edu.asu.cse360.team9;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	public void CloseFrame(){
	    super.dispose();
	}
	/**
	 * Create the frame.
	 */
	public Paths(ArrayList<Result> results) {
		
		// Keep reference to controller so the results can be polled later.
		//this.controller = controller;
		
		this.setTitle("Path Analysis");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseFrame();
			}
		});
		btnClose.setBounds(450, 238, 97, 25);
		contentPane.add(btnClose);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 13, 513, 212);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(new DefaultTableModel(
			null,				// Do not want any rows initially for this table.
			new String[] {
				"Path", "Duration"
			}
		) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		model = (DefaultTableModel) table.getModel();
		
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(62);
		table.getColumnModel().getColumn(1).setMinWidth(10);
		table.getColumnModel().getColumn(1).setMaxWidth(2147483644);
		model.addTableModelListener(new TableModelListener() {
			  @Override
			  public void tableChanged(TableModelEvent e) {
			    if (e.getType() == TableModelEvent.UPDATE && (e.getLastRow() + 1) == model.getRowCount()) {
			    	System.out.println("tableChanged()");
			    	
			    	model.addRow(new Object[] { "", ""});
			    }
			  }
			});
		
		// We will use the populated data to fill the table
		// Currently using dummy data for showing example.
		//ArrayList<Result> results = controller.getResults();
		for(Result r : results)
		{
			model.addRow(new Object[] { r.getPath(), r.getDuration() } );
		}
	}
}
