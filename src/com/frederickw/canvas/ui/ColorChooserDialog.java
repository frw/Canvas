/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ColorChooserDialog.java
 *
 * Created on Jul 29, 2010, 9:29:18 PM
 */

package com.frederickw.canvas.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.LayoutStyle;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author Frederick
 */
public class ColorChooserDialog extends JDialog implements ChangeListener {

	private static final long serialVersionUID = -3707955952843268940L;

	/** Creates new form ColorChooserDialog */
	private JPanel target;
	private ToolboxFrame parent;

	public ColorChooserDialog(ToolboxFrame parent, boolean modal) {
		super(parent, modal);
		this.parent = parent;
		initComponents();
	}

	public void setTarget(JPanel target) {
		this.target = target;
		setLocationRelativeTo(parent);
		colorChooser.setColor(target.getBackground());
		colorChooser.getSelectionModel().addChangeListener(this);
		alphaSlider.setValue(target.getBackground().getAlpha());
		alphaLabel.setText("" + alphaSlider.getValue());
	}

	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		colorChooser = new JColorChooser();
		jLabel1 = new JLabel();
		alphaSlider = new JSlider();
		alphaLabel = new JLabel();
		setButton = new JButton();
		cancelButton = new JButton();

		jLabel1.setText("Alpha:");

		alphaSlider.setMaximum(255);
		alphaSlider.setOrientation(JSlider.VERTICAL);
		alphaSlider.setValue(255);
		alphaSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				alphaSliderStateChanged(evt);
			}
		});

		alphaLabel.setText("255");

		setButton.setText("Ok");
		setButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				setButtonActionPerformed(evt);
			}
		});

		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGap(0, 486, Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		colorChooser,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel1)
																				.addComponent(
																						alphaSlider,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						alphaLabel)))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		setButton)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		cancelButton)))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGap(0, 391, Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(25, 25,
																		25)
																.addComponent(
																		jLabel1)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		alphaSlider,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		alphaLabel))
												.addComponent(
														colorChooser,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(setButton)
												.addComponent(cancelButton))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void alphaSliderStateChanged(ChangeEvent evt) {// GEN-FIRST:event_alphaSliderStateChanged
		Color c = colorChooser.getColor();
		colorChooser.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(),
				alphaSlider.getValue()));
		alphaLabel.setText("" + alphaSlider.getValue());
	}// GEN-LAST:event_alphaSliderStateChanged

	private void cancelButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
		setVisible(false);
	}// GEN-LAST:event_cancelButtonActionPerformed

	@Override
	public void stateChanged(ChangeEvent e) {
		alphaSlider.setValue(colorChooser.getColor().getAlpha());
	}

	private void setButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_setButtonActionPerformed
		target.setBackground(colorChooser.getColor());
		parent.colorChosen(target);
		setVisible(false);
	}// GEN-LAST:event_setButtonActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JLabel alphaLabel;
	private JSlider alphaSlider;
	private JButton cancelButton;
	private JColorChooser colorChooser;
	private JLabel jLabel1;
	private JButton setButton;
	// End of variables declaration//GEN-END:variables

}
