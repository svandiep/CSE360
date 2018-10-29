package edu.asu.cse360.team9;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalityType;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
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
	private JTextField textField_1;
	public JButton btnNewButton;
	
	protected void saveToFile() {
	    JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
	    fileChooser.setFileFilter(filter);
	    int retval = fileChooser.showSaveDialog(btnNewButton);
	    if (retval == JFileChooser.APPROVE_OPTION) {
	      File file = fileChooser.getSelectedFile();
	      if (file == null) {
	        return;
	      }
	      if (!file.getName().toLowerCase().endsWith(".txt")) {
	        file = new File(file.getParentFile(), file.getName() + ".txt");
	      }
	      try {
	    	  textField.write(new OutputStreamWriter(new FileOutputStream(file),
	            "utf-8"));
	        Desktop.getDesktop().open(file);
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
	  }
	/**
	 *  Close the Dialog 
	 */
	public void CloseFrame(){
	    super.dispose();
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
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				okButton.setActionCommand("OK");
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
