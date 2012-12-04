package com.frederickw.canvas.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.frederickw.canvas.ui.Canvas;
import com.frederickw.canvas.ui.ToolboxFrame;


/**
 * 
 * @author Frederick
 */
public class PaintIO {
	private static final Pattern NUMBER = Pattern.compile("(-?[0-9]+)");

	public static String NEWLINE = "\r\n";

	public static boolean saveLayersAs(ArrayList<Paintable> layers, File f,
			ToolboxFrame parent) {
		String name = f.getName();
		String ext = name.substring(name.lastIndexOf('.') + 1);
		if (ext.equals("cp")) {
			String output = "";
			if (parent.aaAllOp.isSelected())
				output += "AA ALL" + NEWLINE;
			else if (parent.aaTextOp.isSelected())
				output += "AA TEXT" + NEWLINE;
			int index;
			for (int i = 0; i < layers.size(); i++) {
				output += layers.get(i).toOutput();
				if (layers.get(i).shadow != null) {
					index = layers.indexOf(layers.get(i).shadow);
					if (index >= 0)
						output += NEWLINE + "SHADOW " + index;
				}
				if (i < layers.size() - 1)
					output += NEWLINE;
			}
			try {
				FileWriter out = new FileWriter(f);
				out.write(output);
				out.close();
			} catch (IOException e) {
				return false;
			}
		} else {
			BufferedImage image = parent.canvas.getBuffer().getSubimage(0, 0,
					Canvas.WIDTH, Canvas.HEIGHT);
			try {
				ImageIO.write(image, ext, f);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	private static final String[] TAGS = { "STRING", "RECTANGLE", "ROUNDRECT",
			"CIRCLE", "LINE", "POLYGON", "IMAGE" };
	private static final int TEXT = 0, RECT = 1, RRECT = 2, CIRCLE = 3,
			LINE = 4, POLY = 5, IMAGE = 6;

	public static ArrayList<Paintable> open(File f, ToolboxFrame parent) {
		ArrayList<Paintable> layers = new ArrayList<Paintable>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			String line;
			ArrayList<String> lines = new ArrayList<String>();
			ArrayList<Integer> shadowIndexes = new ArrayList<Integer>();
			ArrayList<Integer> indexesWithShadow = new ArrayList<Integer>();
			int type = -1, count = 0;
			boolean firstLine = true;
			while ((line = in.readLine()) != null) {
				if (firstLine) {
					if (line.equals("AA ALL"))
						parent.aaAllOp.setSelected(true);
					else if (line.equals("AA TEXT"))
						parent.aaTextOp.setSelected(true);
				}
				if (line.startsWith("layer ")) {
					count++;
					if (lines.size() > 0)
						layers.add(parse(lines, type));
					lines.clear();
					String tag = line.substring(6);
					for (type = 0; type < TAGS.length; type++)
						if (TAGS[type].equals(tag))
							break;
				} else if (line.startsWith("SHADOW ")) {
					indexesWithShadow.add(new Integer(count - 1));
					shadowIndexes.add(new Integer(line.substring(7)));
				} else if (!firstLine)
					lines.add(line);
				firstLine = false;
			}
			if (lines.size() > 0)
				layers.add(parse(lines, type));
			in.close();
			for (int i = 0; i < shadowIndexes.size(); i++)
				layers.get(indexesWithShadow.get(i)).shadow = layers
						.get(shadowIndexes.get(i));
		} catch (IOException e) {
			return null;
		}
		return layers;
	}

	public static Paintable parse(ArrayList<String> lines, int type) {
		switch (type) {
		case TEXT:
			return new PString(lines.get(2), toInt(lines.get(0)),
					toInt(lines.get(1)), stringToColor(lines.get(3)), new Font(
							lines.get(4), toInt(lines.get(6)),
							toInt(lines.get(5))));
		case RECT:
			return new PRectangle(toInt(lines.get(0)), toInt(lines.get(1)),
					toInt(lines.get(2)), toInt(lines.get(3)),
					stringToColor(lines.get(4)), stringToColor(lines.get(5)),
					strokeFrom(lines.get(6)));
		case RRECT:
			return new PRoundRect(toInt(lines.get(0)), toInt(lines.get(1)),
					toInt(lines.get(2)), toInt(lines.get(3)),
					toInt(lines.get(4)), toInt(lines.get(5)),
					stringToColor(lines.get(6)), stringToColor(lines.get(7)),
					strokeFrom(lines.get(8)));
		case CIRCLE:
			return new PCircle(toInt(lines.get(0)), toInt(lines.get(1)),
					toInt(lines.get(2)), toInt(lines.get(3)),
					stringToColor(lines.get(4)), stringToColor(lines.get(5)),
					strokeFrom(lines.get(6)));
		case LINE:
			return new PLine(toInt(lines.get(0)), toInt(lines.get(1)),
					toInt(lines.get(2)), toInt(lines.get(3)),
					stringToColor(lines.get(4)), strokeFrom(lines.get(5)));
		case POLY:
			return new PPolygon(stringToIntArray(lines.get(0)),
					stringToIntArray(lines.get(1)),
					stringToColor(lines.get(2)), stringToColor(lines.get(3)),
					strokeFrom(lines.get(4)));
		case IMAGE:
			return new PImage(lines.get(0), toInt(lines.get(1)),
					toInt(lines.get(2)));
		}
		return null;
	}

	public static BasicStroke strokeFrom(String text) {
		if (text.equals("NO STROKE"))
			return null;
		return new BasicStroke(toInt(text));
	}

	public static String intArrayToString(int[] ints) {
		String text = "";
		for (int i = 0; i < ints.length; i++) {
			text += ints[i];
			if (i < ints.length - 1)
				text += ",";
		}
		return text;
	}

	public static Color stringToColor(String text) {
		if (text.equals("NO COLOR"))
			return null;
		int[] values = stringToIntArray(text);
		if (values.length < 4)
			return null;
		return new Color(values[0], values[1], values[2], values[3]);
	}

	public static int[] stringToIntArray(String text) {
		ArrayList<String> intsAsStrings = new ArrayList<String>();
		Matcher m = NUMBER.matcher(text);
		while (m.find())
			intsAsStrings.add(m.group(1));
		int[] ints = new int[intsAsStrings.size()];
		for (int i = 0; i < ints.length; i++)
			ints[i] = toInt(intsAsStrings.get(i));
		return ints;
	}

	public static int toInt(String text) {
		return Integer.parseInt(text);
	}

	public static String fontToString(Font f) {
		return f.getName() + NEWLINE + f.getSize() + NEWLINE + f.getStyle();
	}

	public static String colorToString(Color c) {
		if (c == null)
			return "NO COLOR";
		return c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + ", "
				+ c.getAlpha();
	}

	public static String strokeToString(BasicStroke bs) {
		if (bs == null)
			return "NO STROKE";
		return (int) bs.getLineWidth() + "";
	}

}
