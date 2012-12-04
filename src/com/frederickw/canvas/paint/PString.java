package com.frederickw.canvas.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * 
 * @author Frederick
 */
public class PString extends Paintable {

	public String text;
	public Font font;

	public PString(String text, int x, int y, Color color, Font font) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.color = color;
		this.font = font;
	}

	@Override
	public String getListText() {
		return "String (\"" + text + "\", " + x + ", " + y + ")";
	}

	@Override
	public Paintable duplicate() {
		return new PString(text, x, y, color, font);
	}

	@Override
	public String getFillCode() {
		return "g.drawString(\"" + text.replaceAll("\"", "\\\"") + "\", " + x
				+ ", " + y + ");";
	}

	@Override
	public String getDrawCode() {
		return "";
	}

	@Override
	public void paintTo(Graphics2D g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, x, y);
	}

	@Override
	public String toOutput() {
		return "layer STRING" + PaintIO.NEWLINE + x + PaintIO.NEWLINE + y
				+ PaintIO.NEWLINE + text + PaintIO.NEWLINE
				+ PaintIO.colorToString(color) + PaintIO.NEWLINE
				+ PaintIO.fontToString(font);
	}

}
