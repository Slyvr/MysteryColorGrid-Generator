package com.slyvronline.mysterycolor.objs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ImagesPanel extends JPanel implements ActionListener{

	final String newline = "\n";
    JLabel inImg;
    JLabel recolorImg;
    JLabel outImg;
    JLabel lblIn;
    JLabel lblRecolor;
    JLabel lblOut;
    JTextArea log;
    JFileChooser fc;
    Img img;
	
	public ImagesPanel() {
		super(new BorderLayout());
		setupPanel();
	}
	
	private void setupPanel() {
        inImg = new JLabel(new ImageIcon());
        recolorImg = new JLabel(new ImageIcon());
        outImg = new JLabel(new ImageIcon());
        
        lblIn = new JLabel("Input Image");
        lblRecolor = new JLabel("Processed Colors");
        lblOut = new JLabel("Output Image");

        JPanel imgPanel = new JPanel();
        imgPanel.add(lblIn);
        imgPanel.add(inImg);
        imgPanel.add(lblRecolor);
        imgPanel.add(recolorImg);
        //imgPanel.add(lblOut);
        //imgPanel.add(outImg);

        //Add the buttons and the log to this panel.
        add(imgPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void setImg(Img img) {
		this.img = img;
		int scaleIncrease = 10;
		this.inImg.setIcon(new ImageIcon(img.getBuffImgIn().getScaledInstance(img.getBuffImgIn().getWidth()*scaleIncrease, img.getBuffImgIn().getHeight()*scaleIncrease, Image.SCALE_DEFAULT)));
		this.recolorImg.setIcon(new ImageIcon(img.getBuffImgRecolor().getScaledInstance(img.getBuffImgRecolor().getWidth()*scaleIncrease, img.getBuffImgRecolor().getHeight()*scaleIncrease, Image.SCALE_DEFAULT)));
		//this.outImg.setIcon(new ImageIcon(img.getBuffImgOut()));
	}
}
