package com.frederickw.canvas.ui;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import com.frederickw.canvas.paint.Paintable;


/**
 * 
 * @author Frederick
 */
public class PaintableListModel extends AbstractListModel<String> {

	private static final long serialVersionUID = -6192194442731548900L;

	private ArrayList<Paintable> list;
	private JList<String> parent;
	private ToolboxFrame frame;

	public PaintableListModel(JList<String> parent, ToolboxFrame frame) {
		super();
		list = new ArrayList<Paintable>();
		this.parent = parent;
		this.frame = frame;
	}

	public ArrayList<Paintable> getArrayList() {
		return list;
	}

	public void add(Paintable p) {
		list.add(p);
		parent.updateUI();
		frame.checkLayerTools();
	}

	public void clear() {
		list.clear();
		parent.updateUI();
		frame.checkLayerTools();
	}

	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public String getElementAt(int index) {
		return list.get(convert(index)).getListText();
	}

	public Paintable get(int index) {
		return list.get(index);
	}

	public Paintable getSelected() {
		int index = getSelectedIndex();
		if (!validIndex(index))
			return null;
		return list.get(index);
	}

	public Paintable remove(int index) {
		if (!validIndex(index))
			return null;
		Paintable removed = list.remove(index);
		parent.updateUI();
		frame.checkLayerTools();
		return removed;
	}

	public void add(int index, Paintable p) {
		list.add(index, p);
		parent.updateUI();
		frame.checkLayerTools();
	}

	public Paintable removeSelected() {
		return remove(getSelectedIndex());
	}

	public void moveSelectedUp() {
		int index = getSelectedIndex() + 1;
		if (index >= list.size())
			return;
		Paintable selected = removeSelected();
		if (selected != null) {
			add(index, selected);
			parent.setSelectedIndex(convert(index));
		}
	}

	public void insert(Paintable p) {
		int index = getSelectedIndex() + 1;
		if (validIndex(index))
			add(index, p);
		else
			add(p);
	}

	public void moveSelectedDown() {
		int index = getSelectedIndex() - 1;
		if (index < 0)
			return;
		Paintable selected = removeSelected();
		if (selected != null) {
			add(index, selected);
			parent.setSelectedIndex(convert(index));
		}
	}

	public int getSelectedIndex() {
		return convert(parent.getSelectedIndex());
	}

	public int convert(int index) {
		return list.size() - 1 - index;
	}

	public boolean validIndex(int index) {
		return index >= 0 && index < list.size();
	}

	public boolean selectedIndexIsValid() {
		return validIndex(getSelectedIndex());
	}

	public void paintAllTo(Graphics2D g) {
		for (Paintable p : list)
			p.paintTo(g);
	}

}
