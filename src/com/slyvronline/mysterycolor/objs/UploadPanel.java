package com.slyvronline.mysterycolor.objs;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.slyvronline.mysterycolor.MainGUI;

public class UploadPanel extends JPanel implements ActionListener{

	static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
	
	public UploadPanel() {
		super(new BorderLayout());
		setupPanel();
	}
	
	private void setupPanel() {
		//Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Create a file chooser
        fc = new JFileChooser();

        //Uncomment one of the following lines to try a different
        //file selection mode.  The first allows just directories
        //to be selected (and, at least in the Java look and feel,
        //shown).  The second allows both files and directories
        //to be selected.  If you leave these lines commented out,
        //then the default mode (FILES_ONLY) will be used.
        //
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //Create the open button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        openButton = new JButton("Choose file to convert");
        openButton.addActionListener(this);

        //Create the save button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        saveButton = new JButton("Save a File...");
        saveButton.addActionListener(this);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);
        //buttonPanel.add(saveButton);

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
	}
	
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return false;
		}
		
		String extension = getExtension(f);
		
		if (extension != null) {
	        if (extension.equals("tiff") ||
	            extension.equals("tif") ||
	            extension.equals("gif") ||
	            extension.equals("jpeg") ||
	            extension.equals("jpg") ||
	            extension.equals("png")) {
	                return true;
	        } else {
	            return false;
	        }
	    }
		
		return false;
	}
	
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');
		
		if (i > 0 &&  i < s.length() - 1) {
		    ext = s.substring(i+1).toLowerCase();
		}
		return ext;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		//Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + newline);
                
                if (accept(file)) {
                	processImageFile(file);
                }
                else {
                	log.append("Invalid file type.  Please select an image file" + newline);
                }
                
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

        //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
                log.append("Saving: " + file.getName() + "." + newline);
            } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
	}
	
	public void processImageFile(File file) {
		log.append("File "+file.getName()+" has been uploaded and is converting"+newline);
    	try {
        	MainGUI.uploadedImgs.add(new Img(file, getExtension(file)));
        	MainGUI.processImgs();
        	log.append("File "+file.getName()+" has been converted"+newline);
    	}
    	catch(Exception ex) {
    		log.append("ERROR! "+ex+newline);
    		ex.printStackTrace();
    		log.append("Conversion failed!"+newline);
    	}
	}
	
}
