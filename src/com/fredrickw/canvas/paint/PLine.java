/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fredrickw.canvas.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * @author Frederick
 */
public class PLine extends Paintable {

	public int x1, y1, x2, y2;

	public PLine(int x1, int y1, int x2, int y2, Color color, BasicStroke stroke) {
		this.stroke = stroke;
		this.strokeColor = color;
		x = Math.min(x1, x2);
		y = Math.min(y1, y2);
		w = Math.abs(x1 - x2);
		h = Math.abs(y1 - y2);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public void move(int xAmount, int yAmount) {
		x1 += xAmount;
		x2 += xAmount;
		y1 += yAmount;
		y2 += yAmount;
	}

	@Override
	public String getListText() {
		return "Line (" + x1 + ", " + y1 + ", " + x2 + ", " + y2 + ")";
	}

	@Override
	public String getDrawCode() {
		return "g.drawLine(" + x1 + ", " + y1 + ", " + x2 + ", " + y2 + ");";
	}

	@Override
	public String getFillCode() {
		return "";
	}

	@Override
	public String toOutput() {
		return "layer LINE" + PaintIO.NEWLINE + x1 + PaintIO.NEWLINE + y1
				+ PaintIO.NEWLINE + x2 + PaintIO.NEWLINE + y2 + PaintIO.NEWLINE
				+ PaintIO.colorToString(strokeColor) + PaintIO.NEWLINE
				+ PaintIO.strokeToString(stroke);
	}

	@Override
	public Paintable duplicate() {
		return new PLine(x1, y1, x2, y2, color, stroke);
	}

	@Override
	public void paintTo(Graphics2D g) {
		g.setStroke(stroke);
		g.setColor(strokeColor);
		g.drawLine(x1, y1, x2, y2);
	}

}
