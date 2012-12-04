package com.frederickw.canvas.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * @author Frederick
 */
public abstract class Paintable {
	public BasicStroke stroke;
	public Color color, strokeColor;
	public int x, y, w, h;
	public Paintable shadow;

	public abstract String getListText();

	public abstract void paintTo(Graphics2D g);

	public abstract Paintable duplicate();

	public abstract String toOutput();

	public abstract String getDrawCode();

	public abstract String getFillCode();
}
