package edu.asu.cse360.team9;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalityType;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Report extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	public JTextField textField_1;
	public JButton btnNewButton;
	
	private ArrayList<Record> records;
	private ArrayList<Result> results;
	private String filename = "";
	private String title = "";

	OutputStream out = new OutputStream() {
        @Override
        public void write(int b) throws IOException {
        }
    };

    class JTextFieldPrintStream extends PrintStream {
        public JTextFieldPrintStream(OutputStream out) {
            super(out);
        }
        @Override
        public void println(String x) {
        	textField_1.setText(x);
        }
    };
    
    /*
     * Function to get complete path to save report
     */
	protected void saveToFile() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Save file as");
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			filename = jfc.getSelectedFile().toString();
			   
			if (!filename .endsWith(".txt"))// append .txt if filename does not have it
				filename += ".txt";
			
			JTextFieldPrintStream print = new JTextFieldPrintStream(out);
	        System.setOut(print);
			System.out.println(filename);
		}
	  }
	/**
	 *  Close the Dialog 
	 */
	public void CloseFrame(){
	    super.dispose();
	}
	
	private boolean createReport()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime createdOn = LocalDateTime.now();
		
		Reporter reporter = new Reporter(records, results, filename, title, createdOn.format(dtf));
		try
		{
			reporter.write();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 1);
			return false;
		}
		return true;
	}
	
	public Report(ArrayList<Record> records, ArrayList<Result> results) {
		this();
		
		this.records = records;
		this.results = results;
	}
	
	/**
	 * Create the dialog.
	 */
	public Report() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		this.setTitle("Save Report");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Report Name:");
		lblNewLabel.setBounds(33, 38, 91, 16);
		contentPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(131, 35, 192, 22);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Save To:");
		lblNewLabel_1.setBounds(33, 112, 56, 16);
		contentPanel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(131, 109, 192, 22);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveToFile();
			}
		});
		btnNewButton.setBounds(335, 108, 97, 24);
		contentPanel.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Run Report");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						boolean success = createReport();
						if(success)
						{
							JOptionPane.showMessageDialog(null, "Your report has been created.", "Report Saved", 1);
							CloseFrame();
						}
					}
				});
				okButton.setActionCommand("Run Report");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						CloseFrame();
					}

				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
