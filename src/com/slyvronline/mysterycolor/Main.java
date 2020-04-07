package com.slyvronline.mysterycolor;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("content/in/20200407.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		int w = img.getWidth();
		int h = img.getHeight();
		int gridsize = 17;
		
		int grid_w = w/17;
		int grid_h = h/17;
		
		FastRGB rgb = new FastRGB(img);
		
		for(int y=0; y<grid_h; y++) {
			for (int x=0; x<grid_w; x++) {
				short[] pixel = rgb.getRGB(x*gridsize, y*gridsize);
				short red = pixel[0];
				short green = pixel[1];
				short blue = pixel[2];
				short alpha = pixel[3];
				System.out.print("["+red+"-"+green+"-"+blue+"]");
			}
			System.out.println();
		}
		
		BufferedImage outImage = new BufferedImage(grid_w, grid_h, BufferedImage.TYPE_INT_RGB);
		for(int y=0; y<grid_h; y++) {
			for (int x=0; x<grid_w; x++) {
				int pixel = rgb.getRGBInteger(x*gridsize, y*gridsize);
				outImage.setRGB(x, y, pixel);
			}
		}
		
		File outFile = new File("content/out/20200407.png");
		try {
			ImageIO.write(outImage, "png", outFile);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
