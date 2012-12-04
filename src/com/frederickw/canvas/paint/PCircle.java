/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.frederickw.canvas.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * @author Frederick
 */
public class PCircle extends Paintable {

	public PCircle(int x, int y, int w, int h, Color color, Color strokeColor,
			BasicStroke stroke) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.stroke = stroke;
		this.color = color;
		this.strokeColor = strokeColor;
	}

	@Override
	public String getListText() {
		return "Circle (" + x + ", " + y + ", " + w + ", " + h + ")";
	}

	@Override
	public Paintable duplicate() {
		return new PCircle(x, y, w, h, color, strokeColor, stroke);
	}

	@Override
	public String toOutput() {
		return "layer CIRCLE" + PaintIO.NEWLINE + x + PaintIO.NEWLINE + y
				+ PaintIO.NEWLINE + w + PaintIO.NEWLINE + h + PaintIO.NEWLINE
				+ PaintIO.colorToString(color) + PaintIO.NEWLINE
				+ PaintIO.colorToString(strokeColor) + PaintIO.NEWLINE
				+ PaintIO.strokeToString(stroke);
	}

	@Override
	public String getDrawCode() {
		return "g.drawOval(" + x + ", " + y + ", " + w + ", " + h + ");";
	}

	@Override
	public String getFillCode() {
		return "g.fillOval(" + x + ", " + y + ", " + w + ", " + h + ");";
	}

	@Override
	public void paintTo(Graphics2D g) {
		if (color != null) {
			g.setPaint(color);
			g.fillOval(x, y, w, h);
		}
		if (stroke != null && strokeColor != null) {
			g.setStroke(stroke);
			g.setPaint(strokeColor);
			g.drawOval(x, y, w, h);
		}
	}
}
