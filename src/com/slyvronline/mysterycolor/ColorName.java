package com.slyvronline.mysterycolor;

import java.awt.Color;

public class ColorName {
	public int r, g, b;
	public String name;
	public Color color;

	public ColorName(String name, int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.name = name;
		this.color = new Color(r,g,b);
	}

	public int computeMSE(int pixR, int pixG, int pixB) {
		return (int) (((pixR - r) * (pixR - r) + (pixG - g) * (pixG - g) + (pixB - b) * (pixB - b)) / 3);
	}

	public int getR() {
		return r;
	}

	public int getG() {
		return g;
	}

	public int getB() {
		return b;
	}

	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
}