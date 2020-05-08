package com.slyvronline.mysterycolor;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.slyvronline.mysterycolor.objs.ImagesPanel;
import com.slyvronline.mysterycolor.objs.Img;
import com.slyvronline.mysterycolor.objs.UploadPanel; 

public class MainGUI {
    
	public static ArrayList<Img> uploadedImgs;
	public static JFrame frame;
	public static UploadPanel panUpload;
	public static ImagesPanel panImgs;
	
    public static void main(String[] args) {
    	
    	setup();
    	
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
    
    private static void setup() {
    	if (uploadedImgs == null) uploadedImgs = new ArrayList<Img>();
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Mystery Color Grid Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024,768);

        panUpload = new UploadPanel();
        panImgs = new ImagesPanel();
        
        //Add content to the window.
        frame.add(panUpload, BorderLayout.PAGE_START);
        frame.add(panImgs, BorderLayout.CENTER);

        //Display the window.
        //frame.pack();
        frame.setVisible(true);
    }
    
    public static void processImgs() {
    	for(Img img : uploadedImgs) {
    		if (img.getBuffImgOut() == null) {
    			img.convert();
    			panImgs.setImg(img);
    		}
    	}
    }

}