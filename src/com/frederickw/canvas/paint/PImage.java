/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.frederickw.canvas.paint;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * 
 * @author Frederick
 */
public class PImage extends Paintable {
	public static Image IMG_ERROR_IMG = null;
	public static ImageObserver CANVAS = null;
	public static HashMap<String, Image> LOADED_IMAGES = new HashMap<String, Image>();

	public static Image getImageFrom(String url) {
		// Do not load an image already loaded.
		if (LOADED_IMAGES.containsKey(url))
			return LOADED_IMAGES.get(url);
		BufferedImage img = null;
		URL imgURL = null;
		try {
			imgURL = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("Invalid image URL.");
			return null;
		}
		try {
			img = ImageIO.read(imgURL);
		} catch (IOException e) {
			System.out.println("Error reading image.");
			return IMG_ERROR_IMG;
		}
		LOADED_IMAGES.put(url, img);
		return img;
	}

	public Image image;
	public String url;

	public PImage(String imageURL, int x, int y) {
		image = getImageFrom(imageURL);
		url = imageURL;
		this.x = x;
		this.y = y;
		w = image.getWidth(CANVAS);
		h = image.getHeight(CANVAS);
	}

	public PImage(Image img, String url, int x, int y) {
		image = img;
		this.url = url;
		this.x = x;
		this.y = y;
		w = image.getWidth(CANVAS);
		h = image.getHeight(CANVAS);
	}

	@Override
	public String getListText() {
		return "Image (" + x + ", " + y + ")";
	}

	@Override
	public void paintTo(Graphics2D g) {
		g.drawImage(image, x, y, CANVAS);
	}

	@Override
	public Paintable duplicate() {
		return new PImage(image, url, x, y);
	}

	@Override
	public String toOutput() {
		return "layer IMAGE" + PaintIO.NEWLINE + url + PaintIO.NEWLINE + x
				+ PaintIO.NEWLINE + y;
	}

	@Override
	public String getDrawCode() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String getFillCode() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
