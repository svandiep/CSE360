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
import javax.swing.JDialog;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.*;

/***
 * Displays the results of successful calculation as a table of
 * paths and their durations ordered by duration descending.
 *
 * Table is read-only.
 */
public class Paths extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;

	public void CloseFrame(){
	    super.dispose();
	}

	public Paths(ArrayList<Result> results) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		this.setTitle("Path Analysis");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
		
		table.getColumnModel().getColumn(0).setPreferredWidth(350);
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
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
