package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.Zip;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5844691620247728007L;
	
	JPanel panel = new JPanel();
	ArrayList<File> fileList;
	
	public MainWindow(){
		
		fileList = new ArrayList<File>();
		initUI();
		
	}

	private void initUI() {
		// TODO Auto-generated method stub
		
		panel = new JPanel();
		
		// changing layout to nothing b/c allows for abs positioning
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		// panel for adding files
		final JPanel filePanel = new JPanel();
		filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.X_AXIS));
		final JButton addFileButton = new JButton("Add a file(s) to file list to be zipped. "); // creating a button
		final JTextField fileBox = new JTextField(24); // adding a text field
		fileBox.setEditable(false);
		
		// adding both componets to filePanel
		filePanel.add(addFileButton); 
		filePanel.add(fileBox);
		
		// and then adding filePanel to panel
		panel.add(filePanel);
		
		
		
		// panel for saving
		final JPanel savePanel = new JPanel();
		savePanel.setLayout(new BoxLayout(savePanel,BoxLayout.X_AXIS));
		final JButton saveFileButton = new JButton("Find a location and name for the zip file to be created: ");
		final JTextField saveFileBox = new JTextField(24);
		
		savePanel.add(saveFileButton);
		savePanel.add(saveFileBox);
		
		panel.add(savePanel);
		
		// button panels, for creating zip & clearing files
		
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		final JButton createButton = new JButton("Create zip file");
		buttonPanel.add(createButton);
		
		final JButton clearFilesButton = new JButton("Clear file list");
		buttonPanel.add(clearFilesButton);
		
		panel.add(buttonPanel);
		
		
		// Adding action to addButton
		
		addFileButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent event){
				
				JFileChooser fc = new JFileChooser();
				// will let the user select multi files
				fc.setMultiSelectionEnabled(true);
				int returnVal = fc.showOpenDialog(MainWindow.this);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					
					File files[] = fc.getSelectedFiles();
					for(int i = 0; i < files.length; i ++){
						
						File file = files[i];
						fileList.add(file);
						String fileName = file.getName();
						
						// updating fileBox
						String currentText = fileBox.getText();
						if(currentText.length() != 0){
							
							fileBox.setText(currentText+", "+fileName);
							
						}
						else{
							
							fileBox.setText(fileName);
							
						}
						
					}
					
					
				}
				
				
			}
			
			
			
			
		});
		
		
		// creating a zip file 
		
		createButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent event){
				
				Zip zip = new Zip(fileList, saveFileBox.getText());
				String result = zip.zipFiles();
				JOptionPane.showMessageDialog(null,result);
					
			}
			
		});
		
		
		// save file location 
		
		saveFileButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent event){
				
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(MainWindow.this);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					
					File file = fc.getSelectedFile();
					saveFileBox.setText(file.getAbsolutePath());
					
					
				}
				
				
			}
			
		});
		
		
		// clearing loaded files and invertedIndex
		
		clearFilesButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent event){
				
				fileBox.setText(""); // empties the files
				
				
			}
			
		});
		
		add(panel);
		pack();
		setTitle("Zipper");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

}
