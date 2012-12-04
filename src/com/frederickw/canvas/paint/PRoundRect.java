package com.frederickw.canvas.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * @author Frederick
 */
public class PRoundRect extends Paintable {

	public int arcWidth, arcHeight;

	public PRoundRect(int x, int y, int w, int h, int arcWidth, int arcHeight,
			Color color, Color strokeColor, BasicStroke stroke) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.stroke = stroke;
		this.color = color;
		this.strokeColor = strokeColor;
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
	}

	@Override
	public String getListText() {
		return "Round Rect(" + x + ", " + y + ", " + w + ", " + h + ")";
	}

	@Override
	public Paintable duplicate() {
		return new PRoundRect(x, y, w, h, arcWidth, arcHeight, color,
				strokeColor, stroke);
	}

	@Override
	public String getDrawCode() {
		return "g.drawRoundRect(" + x + ", " + y + ", " + w + ", " + h + ", "
				+ arcWidth + ", " + arcHeight + ");";
	}

	@Override
	public String getFillCode() {
		return "g.fillRoundRect(" + x + ", " + y + ", " + w + ", " + h + ", "
				+ arcWidth + ", " + arcHeight + ");";
	}

	@Override
	public void paintTo(Graphics2D g) {
		if (color != null) {
			g.setPaint(color);
			g.fillRoundRect(x, y, w, h, arcWidth, arcHeight);
		}
		if (stroke != null && strokeColor != null) {
			g.setStroke(stroke);
			g.setPaint(strokeColor);
			g.drawRoundRect(x, y, w, h, arcWidth, arcHeight);
		}
	}

	@Override
	public String toOutput() {
		return "layer ROUNDRECT" + PaintIO.NEWLINE + x + PaintIO.NEWLINE + y
				+ PaintIO.NEWLINE + w + PaintIO.NEWLINE + h + PaintIO.NEWLINE
				+ arcWidth + PaintIO.NEWLINE + arcHeight + PaintIO.NEWLINE
				+ PaintIO.colorToString(color) + PaintIO.NEWLINE
				+ PaintIO.colorToString(strokeColor) + PaintIO.NEWLINE
				+ PaintIO.strokeToString(stroke);
	}
}
