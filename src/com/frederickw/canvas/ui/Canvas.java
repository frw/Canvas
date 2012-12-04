/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.frederickw.canvas.ui;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.frederickw.canvas.paint.PCircle;
import com.frederickw.canvas.paint.PImage;
import com.frederickw.canvas.paint.PLine;
import com.frederickw.canvas.paint.PPolygon;
import com.frederickw.canvas.paint.PRectangle;
import com.frederickw.canvas.paint.PRoundRect;
import com.frederickw.canvas.paint.PString;
import com.frederickw.canvas.paint.Paintable;


/**
 * 
 * @author Frederick
 */
public class Canvas extends Applet implements Runnable, MouseMotionListener,
		MouseListener, KeyListener {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int INFO_BAR_HEIGHT = 25;

	private static final long serialVersionUID = 1321612877216258596L;

	private final BufferedImage buffer = new BufferedImage(WIDTH, HEIGHT
			+ INFO_BAR_HEIGHT, BufferedImage.TYPE_INT_ARGB);
	private ToolboxFrame parent;
	private PaintableListModel layers;
	private boolean drawing;
	private ArrayList<Integer> Xs, Ys;
	private GeneralPath polyToDraw;
	private Font f;
	private String mouseInfo = "X: 0, Y: 0";

	public Canvas(ToolboxFrame parent) {
		this.parent = parent;
		this.layers = parent.layers;
		Xs = new ArrayList<Integer>();
		Ys = new ArrayList<Integer>();
		polyToDraw = new GeneralPath();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void init() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		PImage.IMG_ERROR_IMG = tk.getImage(getClass().getResource(
				"images/imageerror.png"));
		PImage.CANVAS = this;
	}

	public BufferedImage getBuffer() {
		return buffer;
	}

	private final BasicStroke defaultStroke = new BasicStroke(1);
	private final RenderingHints all = new RenderingHints(
			RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON),
			textOnly = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON),
			off = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_OFF);
	private final Color SELECTED1 = new Color(255, 255, 255, 150),
			SELECTED2 = new Color(0, 0, 0, 150), BACKGROUND = new Color(236,
					233, 216);
	private Font defaultFont = null;

	@Override
	public void paint(Graphics g1) {
		if (defaultFont == null)
			defaultFont = g1.getFont();
		Graphics2D g = (Graphics2D) buffer.getGraphics();
		if (parent.aaAllOp.isSelected())
			g.setRenderingHints(all);
		else if (parent.aaTextOp.isSelected())
			g.setRenderingHints(textOnly);
		else
			g.setRenderingHints(off);
		// g.setBackground(parent.getCanvasBackground());
		g.setColor(parent.getCanvasBackground());
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for (Paintable p : layers.getArrayList())
			p.paintTo(g);
		Paintable selection = layers.getSelected();
		g.setStroke(defaultStroke);
		if (selection != null) {
			int x, y, w, h;
			if (selection instanceof PString) {
				Rectangle2D r = g.getFontMetrics(((PString) selection).font)
						.getStringBounds(((PString) selection).text, g);
				w = (int) r.getWidth();
				h = (int) r.getHeight();
				x = selection.x;
				y = selection.y - h;
			} else {
				x = selection.x;
				y = selection.y;
				w = selection.w;
				h = selection.h;
			}
			g.setColor(SELECTED1);
			g.drawRect(x - 1, y - 1, w + 2, h + 2);
			g.setColor(SELECTED2);
			g.drawRect(x - 2, y - 2, w + 4, h + 4);
		}
		int x = -1, y = -1, w = -1, h = -1;
		if (drawing) {
			x = Math.min(dragX, pressX);
			y = Math.min(dragY, pressY);
			w = Math.abs(dragX - pressX);
			h = Math.abs(dragY - pressY);
			if (shiftDown) {
				w = Math.min(w, h);
				h = w;
			}
			Color fill = parent.getColor(), stroke = parent.getStrokeColor();
			switch (parent.getSelectedTool()) {
			case ToolboxFrame.LINE:
				g.setColor(parent.getStrokeColor());
				g.setStroke(parent.getStroke());
				g.drawLine(pressX, pressY, dragX, dragY);
				break;
			case ToolboxFrame.CIRCLE:
				fill = parent.getColor();
				stroke = parent.getStrokeColor();
				if (fill != null) {
					g.setColor(fill);
					g.fillOval(x, y, w, h);
				}
				if (stroke != null) {
					g.setColor(stroke);
					g.setStroke(parent.getStroke());
					g.drawOval(x, y, w, h);
				}
				break;
			case ToolboxFrame.RECT:
				fill = parent.getColor();
				stroke = parent.getStrokeColor();
				if (fill != null) {
					g.setColor(fill);
					g.fillRect(x, y, w, h);
				}
				if (stroke != null) {
					g.setColor(stroke);
					g.setStroke(parent.getStroke());
					g.drawRect(x, y, w, h);
				}
				break;
			case ToolboxFrame.ROUND:
				fill = parent.getColor();
				stroke = parent.getStrokeColor();
				if (fill != null) {
					g.setColor(fill);
					g.fillRoundRect(x, y, w, h, parent.getArcWidth(),
							parent.getArcHeight());
				}
				if (stroke != null) {
					g.setColor(stroke);
					g.setStroke(parent.getStroke());
					g.drawRoundRect(x, y, w, h, parent.getArcWidth(),
							parent.getArcHeight());
				}
				break;
			case ToolboxFrame.POLY:
				fill = parent.getColor();
				stroke = parent.getStrokeColor();
				if (fill != null && Xs.size() > 2) {
					g.setColor(fill);
					g.fill(polyToDraw);
				}
				if (stroke != null) {
					if (Xs.size() > 1) {
						g.setColor(stroke);
						g.setStroke(parent.getStroke());
						g.draw(polyToDraw);
					}
				}
				if (Xs.size() > 0) {
					if (parent.getStroke() != null)
						g.setStroke(parent.getStroke());
					else
						g.setStroke(defaultStroke);
					g.drawLine(dragX, dragY, Xs.get(Xs.size() - 1),
							Ys.get(Ys.size() - 1));
					g.setStroke(defaultStroke);
					g.setColor(SELECTED1);
					x = Xs.get(0).intValue();
					y = Ys.get(0).intValue();
					g.drawRect(x - 3, y - 3, 6, 6);
					g.setColor(SELECTED2);
					g.drawRect(x - 4, y - 4, 8, 8);
				}
				break;
			case ToolboxFrame.TEXT:
				g.setColor(parent.getColor());
				g.setFont(f);
				g.drawString(parent.getString(), dragX, dragY);
				break;
			case ToolboxFrame.IMAGE:
				Image img = parent.getImage();
				if (img != null)
					g.drawImage(img, dragX, dragY, this);
				break;
			}
		}
		if (parent.showGridBox.isSelected()) {
			g.setColor(parent.getGridColor());
			for (int i = parent.getGridWidth(); i < getWidth(); i += parent
					.getGridWidth())
				g.drawLine(i, 0, i, HEIGHT);
			for (int i = parent.getGridHeight(); i < getWidth(); i += parent
					.getGridHeight())
				g.drawLine(0, i, WIDTH, i);
		}
		g.setColor(BACKGROUND);
		g.fillRect(0, HEIGHT, WIDTH, INFO_BAR_HEIGHT);
		g.setColor(Color.BLACK);
		g.setFont(defaultFont);

		int startY = 617;
		if (h >= 0 && w >= 0)
			g.drawString(mouseInfo + " - W: " + w + ", H: " + h, 5, startY);
		else
			g.drawString(mouseInfo, 5, startY);
		g.setFont(parent.getPreviewFont());
		g.drawString("Preview Font", 300, startY);

		g1.drawImage(buffer, 0, 0, null);
	}

	private Image dbImage;
	private Graphics dbg;

	@Override
	public void update(Graphics g) {

		// initialize buffer
		if (dbImage == null) {
			dbImage = createImage(this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics();
		}

		// clear screen in background
		dbg.setColor(getBackground());
		dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

		// draw elements in background
		dbg.setColor(getForeground());
		paint(dbg);

		// draw image on the screen
		g.drawImage(dbImage, 0, 0, this);

	}

	public void reset() {
		polyToDraw.reset();
		Xs.clear();
		Ys.clear();
		shiftDown = false;
		drawing = false;
	}

	// <editor-fold defaultstate="collapsed" desc="Mouse">
	private int pressX, pressY;
	private int dragX, dragY;

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseInfo = "X: " + e.getX() + ", Y: " + e.getY();
		Paintable p = layers.getSelected();
		dragX = e.getX();
		dragY = e.getY();
		if (parent.snap2GridBox.isSelected()) {
			dragX += (int) Math.round(parent.getGridWidth() / 2.0);
			dragY += (int) Math.round(parent.getGridHeight() / 2.0);
			dragX = dragX / parent.getGridWidth() * parent.getGridWidth();
			dragY = dragY / parent.getGridHeight() * parent.getGridHeight();
		}
		switch (parent.getSelectedTool()) {
		case ToolboxFrame.MOVE:
			int oldX = p.x,
			oldY = p.y;
			p.x = dragX - pressX;
			p.y = dragY - pressY;
			oldX = p.x - oldX;
			oldY = p.y - oldY;
			if (p instanceof PLine) {
				((PLine) p).move(oldX, oldY);
			} else if (p instanceof PPolygon)
				((PPolygon) p).move(oldX, oldY);
			Paintable shadow = p.shadow;
			if (shadow != null) {
				shadow.x += oldX;
				shadow.y += oldY;
				if (shadow instanceof PLine) {
					((PLine) shadow).move(oldX, oldY);
				} else if (shadow instanceof PPolygon) {
					((PPolygon) shadow).move(oldX, oldY);
				}
			}
			break;
		case ToolboxFrame.RESIZE:
			int xdist = dragX - pressX;
			int ydist = dragY - pressY;
			p.w = startWidth + xdist;
			p.h = startHeight + ydist;
			if (shiftDown) {
				p.w = Math.min(p.w, p.h);
				p.h = p.w;
			}
			shadow = p.shadow;
			if (shadow != null) {
				shadow.w = shadowWidth + xdist;
				shadow.h = shadowHeight + ydist;
			}
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseInfo = "X: " + e.getX() + ", Y: " + e.getY();
		if (parent.getSelectedTool() == ToolboxFrame.POLY) {
			dragX = e.getX();
			dragY = e.getY();
			if (parent.snap2GridBox.isSelected()) {
				dragX += (int) Math.round(parent.getGridWidth() / 2.0);
				dragY += (int) Math.round(parent.getGridHeight() / 2.0);
				dragX = dragX / parent.getGridWidth() * parent.getGridWidth();
				dragY = dragY / parent.getGridHeight() * parent.getGridHeight();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	private int startWidth, startHeight, shadowWidth, shadowHeight;

	@Override
	public void mousePressed(MouseEvent e) {
		pressX = dragX = e.getX();
		pressY = dragY = e.getY();
		if (parent.snap2GridBox.isSelected()) {
			pressX += (int) Math.round(parent.getGridWidth() / 2.0);
			pressY += (int) Math.round(parent.getGridHeight() / 2.0);
			pressX = pressX / parent.getGridWidth() * parent.getGridWidth();
			pressY = pressY / parent.getGridHeight() * parent.getGridHeight();
		}
		switch (parent.getSelectedTool()) {
		case ToolboxFrame.MOVE:
			pressX -= layers.getSelected().x;
			pressY -= layers.getSelected().y;
			break;
		case ToolboxFrame.RESIZE:
			startWidth = layers.getSelected().w;
			startHeight = layers.getSelected().h;
			Paintable shadow = layers.getSelected().shadow;
			if (shadow != null) {
				shadowWidth = shadow.w;
				shadowHeight = shadow.h;
			}
			break;
		case ToolboxFrame.TEXT:
			f = parent.getSelectedFont();
			break;
		case ToolboxFrame.POLY:
			if (polyToDraw.getCurrentPoint() == null) {
				Xs.clear();
				Ys.clear();
			}
			break;
		case ToolboxFrame.IMAGE:
			dragX = pressX;
			dragY = pressY;
			break;
		}
		if (parent.getSelectedTool() > ToolboxFrame.MOVE)
			drawing = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int releaseX = e.getX(), releaseY = e.getY();
		if (parent.snap2GridBox.isSelected()) {
			releaseX += (int) Math.round(parent.getGridWidth() / 2.0);
			releaseY += (int) Math.round(parent.getGridHeight() / 2.0);
			releaseX = releaseX / parent.getGridWidth() * parent.getGridWidth();
			releaseY = releaseY / parent.getGridHeight()
					* parent.getGridHeight();
		}
		drawing = parent.getSelectedTool() == ToolboxFrame.POLY;
		int x = Math.min(pressX, releaseX), y = Math.min(pressY, releaseY), w = Math
				.abs(pressX - releaseX), h = Math.abs(pressY - releaseY);
		if (shiftDown) {
			w = Math.min(w, h);
			h = w;
		}
		switch (parent.getSelectedTool()) {
		case ToolboxFrame.LINE:
			layers.insert(new PLine(pressX, pressY, releaseX, releaseY, parent
					.getStrokeColor(), parent.getStroke()));
			break;
		case ToolboxFrame.RECT:
			layers.insert(new PRectangle(x, y, w, h, parent.getColor(), parent
					.getStrokeColor(), parent.getStroke()));
			break;
		case ToolboxFrame.ROUND:
			layers.insert(new PRoundRect(x, y, w, h, parent.getArcWidth(),
					parent.getArcHeight(), parent.getColor(), parent
							.getStrokeColor(), parent.getStroke()));
			break;
		case ToolboxFrame.CIRCLE:
			layers.insert(new PCircle(x, y, w, h, parent.getColor(), parent
					.getStrokeColor(), parent.getStroke()));
			break;
		case ToolboxFrame.POLY:
			if (Xs.isEmpty()) {
				polyToDraw.moveTo(releaseX, releaseY);
				Xs.add(new Integer(releaseX));
				Ys.add(new Integer(releaseY));
			} else {
				int xdist = Xs.get(0).intValue() - releaseX;
				int ydist = Ys.get(0).intValue() - releaseY;
				double dist = Math.sqrt(xdist * xdist + ydist * ydist);
				if (dist < 3 && Xs.size() > 1) {
					drawing = false;
					polyToDraw.reset();
					layers.insert(new PPolygon(integerALToIntArray(Xs),
							integerALToIntArray(Ys), parent.getColor(), parent
									.getStrokeColor(), parent.getStroke()));
				} else {
					polyToDraw.lineTo(releaseX, releaseY);
					Xs.add(new Integer(releaseX));
					Ys.add(new Integer(releaseY));
				}
			}
			break;
		case ToolboxFrame.TEXT:
			layers.add(new PString(parent.getString(), releaseX, releaseY,
					parent.getColor(), f));
			break;
		case ToolboxFrame.IMAGE:
			Image img = parent.getImage();
			if (img != null) {
				layers.add(new PImage(img, parent.getImageURL(), releaseX,
						releaseY));
			}
			break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public int[] integerALToIntArray(ArrayList<Integer> integers) {
		int[] asInts = new int[integers.size()];
		for (int i = 0; i < asInts.length; i++)
			asInts[i] = integers.get(i).intValue();
		return asInts;
	}

	// </editor-fold>

	@Override
	public void keyTyped(KeyEvent e) {

	}

	boolean shiftDown;

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SHIFT:
			shiftDown = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SHIFT:
			shiftDown = false;
			break;
		case KeyEvent.VK_BACK_SPACE:
			if (drawing && parent.getSelectedTool() == ToolboxFrame.POLY) {
				Xs.remove(Xs.size() - 1);
				Ys.remove(Ys.size() - 1);
				polyToDraw.reset();
				if (Xs.size() == 0)
					drawing = false;
				else {
					polyToDraw.moveTo(Xs.get(0).intValue(), Ys.get(0)
							.intValue());
					for (int i = 1; i < Xs.size(); i++)
						polyToDraw.lineTo(Xs.get(i).intValue(), Ys.get(i)
								.intValue());
				}
			}
			break;
		case KeyEvent.VK_ESCAPE:
			if (drawing && parent.getSelectedTool() == ToolboxFrame.POLY) {
				Xs.clear();
				Ys.clear();
				polyToDraw.reset();
				drawing = false;
			}
			break;
		case KeyEvent.VK_DELETE:
			if (!drawing)
				layers.removeSelected();
			break;
		}
	}
}
