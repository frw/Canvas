/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.frederickw.canvas.ui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author Frederick
 */
public class CanvasFrame extends JFrame {

	private static final long serialVersionUID = -2250569186316126655L;

	public CanvasFrame(ToolboxFrame toolbox) {
		setTitle("Canvas");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		final JFrame t = this;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				if (JOptionPane.showConfirmDialog(t,
						"Do you really want to exit?", "Confirm",
						JOptionPane.YES_NO_OPTION) == 0)
					System.exit(0);
			}
		});
		toolbox.canvas = new Canvas(toolbox);
		toolbox.canvas.setPreferredSize(new Dimension(Canvas.WIDTH,
				Canvas.HEIGHT + Canvas.INFO_BAR_HEIGHT));
		toolbox.canvas.init();
		setResizable(false);
		add(toolbox.canvas);
		pack();
	}

}
