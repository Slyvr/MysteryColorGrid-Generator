package com.slyvronline.mysterycolor;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class FastRGB {

	private int width;
	private int height;
	private boolean hasAlphaChannel;
	private int pixelLength;
	private byte[] pixels;

	public FastRGB(BufferedImage image) {

		pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		width = image.getWidth();
		height = image.getHeight();
		hasAlphaChannel = image.getAlphaRaster() != null;
		pixelLength = 3;
		if (hasAlphaChannel) {
			pixelLength = 4;
		}

	}

	public int getRGBInteger(int x, int y) {
		int pos = (y * pixelLength * width) + (x * pixelLength);

		int argb = -16777216; // 255 alpha
		if (hasAlphaChannel) {
			argb = (((int) pixels[pos++] & 0xff) << 24); // alpha
		}

		argb += ((int) pixels[pos++] & 0xff); // blue
		argb += (((int) pixels[pos++] & 0xff) << 8); // green
		argb += (((int) pixels[pos++] & 0xff) << 16); // red
		return argb;
	}

	public short[] getRGB(int x, int y) {
		int pos = (y * pixelLength * width) + (x * pixelLength);

		short rgb[] = new short[4];
		if (hasAlphaChannel)
			rgb[3] = (short) (pixels[pos++] & 0xFF); // Alpha
		rgb[2] = (short) (pixels[pos++] & 0xFF); // Blue
		rgb[1] = (short) (pixels[pos++] & 0xFF); // Green
		rgb[0] = (short) (pixels[pos++] & 0xFF); // Red
		return rgb;
	}
}