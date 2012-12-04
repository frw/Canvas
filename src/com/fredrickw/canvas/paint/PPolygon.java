package com.fredrickw.canvas.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

/**
 * 
 * @author Frederick
 */
public class PPolygon extends Paintable {

	public int[] xs, ys;
	private GeneralPath poly;

	public PPolygon(int[] xs, int[] ys, Color color, Color strokeColor,
			BasicStroke stroke) {
		this.xs = xs;
		this.ys = ys;
		this.color = color;
		this.strokeColor = strokeColor;
		this.stroke = stroke;
		x = xs[0];
		y = ys[0];
		w = x;
		h = y;
		for (int i = 1; i < xs.length; i++) {
			if (xs[i] < x)
				x = xs[i];
			else if (xs[i] > w)
				w = xs[i];
			if (ys[i] < y)
				y = ys[i];
			else if (ys[i] > h)
				h = ys[i];
		}
		w -= x;
		h -= y;
		poly = new GeneralPath();
		update();
	}

	@Override
	public String getListText() {
		return "Polygon (" + x + ", " + y + ", " + w + ", " + h + ")";
	}

	@Override
	public Paintable duplicate() {
		return new PPolygon(xs.clone(), ys.clone(), color, strokeColor, stroke);
	}

	public void move(int xAmount, int yAmount) {
		for (int i = 0; i < xs.length; i++) {
			xs[i] += xAmount;
			ys[i] += yAmount;
		}
		update();
	}

	public void update() {
		poly.reset();
		poly.moveTo(xs[0], ys[0]);
		for (int i = 1; i < xs.length; i++)
			poly.lineTo(xs[i], ys[i]);
		poly.closePath();
	}

	@Override
	public String toOutput() {
		return "layer POLYGON" + PaintIO.NEWLINE + PaintIO.intArrayToString(xs)
				+ PaintIO.NEWLINE + PaintIO.intArrayToString(ys)
				+ PaintIO.NEWLINE + PaintIO.colorToString(color)
				+ PaintIO.NEWLINE + PaintIO.colorToString(strokeColor)
				+ PaintIO.NEWLINE + PaintIO.strokeToString(stroke);
	}

	@Override
	public void paintTo(Graphics2D g) {
		if (color != null) {
			g.setPaint(color);
			g.fill(poly);
		}
		if (stroke != null && strokeColor != null) {
			g.setStroke(stroke);
			g.setPaint(strokeColor);
			g.draw(poly);
		}
	}

	@Override
	public String getDrawCode() {
		throw new UnsupportedOperationException("Polygons should not use this!");
	}

	@Override
	public String getFillCode() {
		throw new UnsupportedOperationException(
				"Polygons should not use this!!");
	}

}
