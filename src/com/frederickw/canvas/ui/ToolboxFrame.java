package com.frederickw.canvas.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import com.frederickw.canvas.paint.PCircle;
import com.frederickw.canvas.paint.PImage;
import com.frederickw.canvas.paint.PLine;
import com.frederickw.canvas.paint.PPolygon;
import com.frederickw.canvas.paint.PRectangle;
import com.frederickw.canvas.paint.PRoundRect;
import com.frederickw.canvas.paint.PString;
import com.frederickw.canvas.paint.PaintIO;
import com.frederickw.canvas.paint.Paintable;


/**
 * 
 * @author Frederick
 */
public class ToolboxFrame extends JFrame {

	private static final long serialVersionUID = 1422885470716601812L;

	public PaintableListModel layers;
	public Canvas canvas;
	private CanvasFrame canvasFrame;
	private ColorChooserDialog colorChooser;
	private GridDialog gridOptions;
	private RRDialog roundRectOptions;
	private JFileChooser openDialog;
	private JFileChooser saveDialog;
	private FileExtensionFilter defaultFileFilter;
	private File file;
	private Image loadedImage;
	private String imageURL;
	private BufferedImage background;
	private JPanel dummy;
	private boolean editing;

	public ToolboxFrame() {
		background = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
		Graphics g = background.getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 16, 16);
		dummy = new JPanel();
		dummy.setBackground(Color.LIGHT_GRAY);
		initComponents();
		fontDropDown.setSelectedItem("Arial");
		updateFont();
		canvasFrame = new CanvasFrame(this);
		canvasFrame.setVisible(true);
		canvasFrame.setLocation(10, 10);
		Point loc = canvasFrame.getLocation();
		setLocation(loc.x + canvasFrame.getWidth(), loc.y);
		colorChooser = new ColorChooserDialog(this, true);
		gridOptions = new GridDialog(this, true, colorChooser);

		defaultFileFilter = new FileExtensionFilter("Canvas Project File",
				".cp");

		openDialog = new JFileChooser();
		openDialog.setAcceptAllFileFilterUsed(false);
		openDialog.setFileFilter(defaultFileFilter);

		saveDialog = new JFileChooser();
		saveDialog.setAcceptAllFileFilterUsed(false);
		saveDialog.setFileFilter(defaultFileFilter);
		saveDialog.addChoosableFileFilter(new FileExtensionFilter("JPEG File",
				".jpg"));
		saveDialog.addChoosableFileFilter(new FileExtensionFilter("PNG File",
				".png"));
		saveDialog.addChoosableFileFilter(new FileExtensionFilter("BMP File",
				".bmp"));
		saveDialog.addChoosableFileFilter(new FileExtensionFilter("GIF File",
				".gif"));
		roundRectOptions = new RRDialog(this, true);
	}

	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		toolsGroup = new ButtonGroup();
		antialiasingGroup = new ButtonGroup();
		jScrollPane1 = new JScrollPane();
		layerList = new JList<String>();
		layerUpButton = new JButton();
		layerDownButton = new JButton();
		lineButton = new JToggleButton();
		deleteLayerButton = new JButton();
		polygonButton = new JToggleButton();
		stringButton = new JToggleButton();
		rectButton = new JToggleButton();
		circleButton = new JToggleButton();
		moveButton = new JToggleButton();
		fillBox = new JCheckBox();
		fillColorPanel = new JPanel();
		strokeColorPanel = new JPanel();
		strokeBox = new JCheckBox();
		strokeSpinner = new JSpinner();
		jLabel1 = new JLabel();
		fontDropDown = new JComboBox<String>();
		boldButton = new JToggleButton();
		italicsButton = new JToggleButton();
		fontSize = new JSpinner();
		editButton = new JToggleButton();
		textBox = new JTextField();
		jLabel2 = new JLabel();
		duplicateButton = new JButton();
		jLabel3 = new JLabel();
		shadowButton = new JButton();
		shadowColorPanel = new JPanel();
		jLabel4 = new JLabel();
		shadowX = new JSpinner();
		shadowY = new JSpinner();
		jLabel5 = new JLabel();
		jLabel6 = new JLabel();
		roundRectButton = new JToggleButton();
		rroptionsButton = new JButton();
		resizeButton = new JToggleButton();
		imageButton = new JToggleButton();
		jMenuBar1 = new JMenuBar();
		fileMenu = new JMenu();
		clearOption = new JMenuItem();
		jSeparator3 = new JSeparator();
		openOption = new JMenuItem();
		saveOption = new JMenuItem();
		saveAsOption = new JMenuItem();
		viewMenu = new JMenu();
		noneOp = new JRadioButtonMenuItem();
		aaAllOp = new JRadioButtonMenuItem();
		aaTextOp = new JRadioButtonMenuItem();
		jSeparator1 = new JSeparator();
		showGridBox = new JCheckBoxMenuItem();
		snap2GridBox = new JCheckBoxMenuItem();
		gridOptionsOption = new JMenuItem();
		jSeparator4 = new JSeparator();
		backgroundOp = new JMenuItem();

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setTitle("Toolbox");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		layers = new PaintableListModel(layerList, this);
		layerList.setModel(layers);
		layerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		layerList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent evt) {
				layerListValueChanged(evt);
			}
		});
		layerList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
				layerListKeyReleased(evt);
			}
		});
		jScrollPane1.setViewportView(layerList);

		layerUpButton.setIcon(new ImageIcon(getClass().getResource(
				"images/layerup.png"))); // NOI18N
		layerUpButton.setToolTipText("Move selected layer up.");
		layerUpButton.setEnabled(false);
		layerUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				layerUpButtonActionPerformed(evt);
			}
		});

		layerDownButton.setIcon(new ImageIcon(getClass().getResource(
				"images/layerdown.png"))); // NOI18N
		layerDownButton.setToolTipText("Move selected layer down.");
		layerDownButton.setEnabled(false);
		layerDownButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				layerDownButtonActionPerformed(evt);
			}
		});

		toolsGroup.add(lineButton);
		lineButton.setIcon(new ImageIcon(getClass().getResource(
				"images/line.png"))); // NOI18N
		lineButton.setToolTipText("Draw a line.");
		lineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				lineButtonActionPerformed(evt);
			}
		});

		deleteLayerButton.setIcon(new ImageIcon(getClass().getResource(
				"images/trashcan.png"))); // NOI18N
		deleteLayerButton.setToolTipText("Delete the selected layer.");
		deleteLayerButton.setEnabled(false);
		deleteLayerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				deleteLayerButtonActionPerformed(evt);
			}
		});

		toolsGroup.add(polygonButton);
		polygonButton.setIcon(new ImageIcon(getClass().getResource(
				"images/polygon.png"))); // NOI18N
		polygonButton.setToolTipText("Draw a polygon.");
		polygonButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				polygonButtonActionPerformed(evt);
			}
		});

		toolsGroup.add(stringButton);
		stringButton.setIcon(new ImageIcon(getClass().getResource(
				"images/string.png"))); // NOI18N
		stringButton.setToolTipText("Draw a string.");
		stringButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				stringButtonActionPerformed(evt);
			}
		});

		toolsGroup.add(rectButton);
		rectButton.setIcon(new ImageIcon(getClass().getResource(
				"images/rect.png"))); // NOI18N
		rectButton.setToolTipText("Draw a rectangle.");
		rectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				rectButtonActionPerformed(evt);
			}
		});

		toolsGroup.add(circleButton);
		circleButton.setIcon(new ImageIcon(getClass().getResource(
				"images/circle.png"))); // NOI18N
		circleButton.setToolTipText("Draw a circle or oval.");
		circleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				circleButtonActionPerformed(evt);
			}
		});

		toolsGroup.add(moveButton);
		moveButton.setIcon(new ImageIcon(getClass().getResource(
				"images/move.png"))); // NOI18N
		moveButton.setToolTipText("Move selected layer.");
		moveButton.setEnabled(false);
		moveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				moveButtonActionPerformed(evt);
			}
		});

		fillBox.setSelected(true);
		fillBox.setText("Fill");
		fillBox.setToolTipText("Toggles whether or not this layer has a fill.");
		fillBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				fillBoxActionPerformed(evt);
			}
		});

		fillColorPanel.setBackground(new Color(255, 255, 255));
		fillColorPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0,
				0)));
		fillColorPanel
				.setToolTipText("Choose a fill color. This is also used when drawing text.");
		fillColorPanel.setPreferredSize(new Dimension(26, 25));
		fillColorPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent evt) {
				fillColorPanelMouseReleased(evt);
			}
		});

		GroupLayout fillColorPanelLayout = new GroupLayout(fillColorPanel);
		fillColorPanel.setLayout(fillColorPanelLayout);
		fillColorPanelLayout.setHorizontalGroup(fillColorPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,
						23, Short.MAX_VALUE));
		fillColorPanelLayout.setVerticalGroup(fillColorPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,
						23, Short.MAX_VALUE));

		strokeColorPanel.setBackground(new Color(0, 0, 0));
		strokeColorPanel.setBorder(BorderFactory.createLineBorder(new Color(0,
				0, 0)));
		strokeColorPanel
				.setToolTipText("Choose a stroke color. This is also used when drawing lines.");
		strokeColorPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent evt) {
				strokeColorPanelMouseReleased(evt);
			}
		});

		GroupLayout strokeColorPanelLayout = new GroupLayout(strokeColorPanel);
		strokeColorPanel.setLayout(strokeColorPanelLayout);
		strokeColorPanelLayout.setHorizontalGroup(strokeColorPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,
						23, Short.MAX_VALUE));
		strokeColorPanelLayout.setVerticalGroup(strokeColorPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,
						23, Short.MAX_VALUE));

		strokeBox.setSelected(true);
		strokeBox.setText("Stroke");
		strokeBox
				.setToolTipText("Toggles whether or not this layer has a stroke.");
		strokeBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				strokeBoxActionPerformed(evt);
			}
		});

		strokeSpinner.setModel(new SpinnerNumberModel(1, 1, 25, 1));
		strokeSpinner
				.setToolTipText("Changes how thick the stroke around this layer is.");
		strokeSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				strokeSpinnerStateChanged(evt);
			}
		});

		jLabel1.setText("Font:");

		fontDropDown.setModel(new DefaultComboBoxModel<String>(
				GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getAvailableFontFamilyNames()));
		fontDropDown.setToolTipText("Choose a font.");
		fontDropDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				fontDropDownActionPerformed(evt);
			}
		});

		boldButton.setFont(new Font("Tahoma", 1, 11));
		boldButton.setText("B");
		boldButton.setToolTipText("Toggles bold text.");
		boldButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				boldButtonActionPerformed(evt);
			}
		});

		italicsButton.setFont(new Font("Tahoma", 2, 11));
		italicsButton.setText("I");
		italicsButton.setToolTipText("Toggles italicized text.");
		italicsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				italicsButtonActionPerformed(evt);
			}
		});

		fontSize.setModel(new SpinnerNumberModel(9, 7, 32, 1));
		fontSize.setToolTipText("Change the size of your font. (Will not be shown in the preview)");
		fontSize.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				fontSizeStateChanged(evt);
			}
		});

		toolsGroup.add(editButton);
		editButton.setIcon(new ImageIcon(getClass().getResource(
				"images/edit.png"))); // NOI18N
		editButton
				.setToolTipText("Edit selected layer's colors, stroke, font, and text.");
		editButton.setEnabled(false);
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				editButtonActionPerformed(evt);
			}
		});

		textBox.setToolTipText("Text here will be drawn with the text tool.");
		textBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
				textBoxKeyReleased(evt);
			}
		});

		jLabel2.setText("Text:");

		duplicateButton.setIcon(new ImageIcon(getClass().getResource(
				"images/duplicate.png"))); // NOI18N
		duplicateButton.setToolTipText("Duplicate selected layer.");
		duplicateButton.setEnabled(false);
		duplicateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				duplicateButtonActionPerformed(evt);
			}
		});

		jLabel3.setText("Layers:");

		shadowButton.setIcon(new ImageIcon(getClass().getResource(
				"images/shadow.png"))); // NOI18N
		shadowButton
				.setToolTipText("<html>Creates a shadow for the selected layer.<br>\nThe new shadow layer will be moved as you move the selected layer.");
		shadowButton.setEnabled(false);
		shadowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				shadowButtonActionPerformed(evt);
			}
		});

		shadowColorPanel.setBackground(new Color(0, 0, 0));
		shadowColorPanel.setBorder(BorderFactory.createLineBorder(new Color(0,
				0, 0)));
		shadowColorPanel
				.setToolTipText("Choose a stroke color. This is also used when drawing lines.");
		shadowColorPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent evt) {
				shadowColorPanelMouseReleased(evt);
			}
		});

		GroupLayout shadowColorPanelLayout = new GroupLayout(shadowColorPanel);
		shadowColorPanel.setLayout(shadowColorPanelLayout);
		shadowColorPanelLayout.setHorizontalGroup(shadowColorPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,
						23, Short.MAX_VALUE));
		shadowColorPanelLayout.setVerticalGroup(shadowColorPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,
						23, Short.MAX_VALUE));

		jLabel4.setText("Shadow:");

		shadowX.setModel(new SpinnerNumberModel(Integer.valueOf(3), null, null,
				Integer.valueOf(1)));

		shadowY.setModel(new SpinnerNumberModel(Integer.valueOf(3), null, null,
				Integer.valueOf(1)));

		jLabel5.setText("X:");

		jLabel6.setText("Y:");

		toolsGroup.add(roundRectButton);
		roundRectButton.setIcon(new ImageIcon(getClass().getResource(
				"images/roundrect.png"))); // NOI18N
		roundRectButton.setToolTipText("Draw a rounded rectangle.");
		roundRectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				roundRectButtonActionPerformed(evt);
			}
		});

		rroptionsButton.setIcon(new ImageIcon(getClass().getResource(
				"images/roundrectops.png"))); // NOI18N
		rroptionsButton
				.setToolTipText("Change the arc width and height of rounded rectangles.");
		rroptionsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				rroptionsButtonActionPerformed(evt);
			}
		});

		toolsGroup.add(resizeButton);
		resizeButton.setIcon(new ImageIcon(getClass().getResource(
				"images/resize.png"))); // NOI18N
		resizeButton
				.setToolTipText("<html>Resize selected layer.<br>\n(Only usable on rectangles, rounded rectangles, and ovals)");
		resizeButton.setEnabled(false);
		resizeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				resizeButtonActionPerformed(evt);
			}
		});

		toolsGroup.add(imageButton);
		imageButton.setIcon(new ImageIcon(getClass().getResource(
				"images/image.png"))); // NOI18N
		imageButton.setToolTipText("Load and draw an image from a URL.");
		imageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				imageButtonActionPerformed(evt);
			}
		});

		fileMenu.setText("File");

		clearOption.setText("Clear");
		clearOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				clearOptionActionPerformed(evt);
			}
		});
		fileMenu.add(clearOption);
		fileMenu.add(jSeparator3);

		openOption.setText("Open...");
		openOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				openOptionActionPerformed(evt);
			}
		});
		fileMenu.add(openOption);

		saveOption.setText("Save");
		saveOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				saveOptionActionPerformed(evt);
			}
		});
		fileMenu.add(saveOption);

		saveAsOption.setText("Save As...");
		saveAsOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				saveAsOptionActionPerformed(evt);
			}
		});
		fileMenu.add(saveAsOption);

		jMenuBar1.add(fileMenu);

		viewMenu.setText("View");

		antialiasingGroup.add(noneOp);
		noneOp.setSelected(true);
		noneOp.setText("No anti-aliasing");
		viewMenu.add(noneOp);

		antialiasingGroup.add(aaAllOp);
		aaAllOp.setText("Anti-alias Shapes and Text");
		viewMenu.add(aaAllOp);

		antialiasingGroup.add(aaTextOp);
		aaTextOp.setText("Anti-alias Text");
		viewMenu.add(aaTextOp);
		viewMenu.add(jSeparator1);

		showGridBox.setText("Show Grid");
		viewMenu.add(showGridBox);

		snap2GridBox.setText("Snap-to Grid");
		viewMenu.add(snap2GridBox);

		gridOptionsOption.setText("Grid Options...");
		gridOptionsOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				gridOptionsOptionActionPerformed(evt);
			}
		});
		viewMenu.add(gridOptionsOption);
		viewMenu.add(jSeparator4);

		backgroundOp.setIcon(new ImageIcon(background));
		backgroundOp.setText("Change Background Color");
		backgroundOp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				backgroundOpActionPerformed(evt);
			}
		});
		viewMenu.add(backgroundOp);

		jMenuBar1.add(viewMenu);

		setJMenuBar(jMenuBar1);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createParallelGroup(
																GroupLayout.Alignment.TRAILING)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addComponent(
																						stringButton,
																						GroupLayout.PREFERRED_SIZE,
																						27,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						rectButton,
																						GroupLayout.PREFERRED_SIZE,
																						27,
																						GroupLayout.PREFERRED_SIZE))
																.addComponent(
																		roundRectButton,
																		GroupLayout.PREFERRED_SIZE,
																		27,
																		GroupLayout.PREFERRED_SIZE))
												.addComponent(
														circleButton,
														GroupLayout.PREFERRED_SIZE,
														27,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														lineButton,
														GroupLayout.PREFERRED_SIZE,
														27,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														polygonButton,
														GroupLayout.PREFERRED_SIZE,
														27,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														moveButton,
														GroupLayout.PREFERRED_SIZE,
														27,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														resizeButton,
														GroupLayout.PREFERRED_SIZE,
														27,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														imageButton,
														GroupLayout.PREFERRED_SIZE,
														27,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel4)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		shadowColorPanel,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jLabel5)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		shadowX,
																		GroupLayout.PREFERRED_SIZE,
																		40,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		jLabel6)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		shadowY,
																		GroupLayout.PREFERRED_SIZE,
																		40,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createParallelGroup(
																GroupLayout.Alignment.LEADING,
																false)
																.addGroup(
																		layout.createSequentialGroup()
																				.addComponent(
																						strokeColorPanel,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						strokeBox)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						strokeSpinner,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						rroptionsButton,
																						GroupLayout.PREFERRED_SIZE,
																						28,
																						GroupLayout.PREFERRED_SIZE))
																.addGroup(
																		layout.createSequentialGroup()
																				.addComponent(
																						fillColorPanel,
																						GroupLayout.PREFERRED_SIZE,
																						25,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						fillBox))
																.addComponent(
																		jLabel1)
																.addGroup(
																		layout.createSequentialGroup()
																				.addComponent(
																						jLabel2)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						textBox,
																						GroupLayout.DEFAULT_SIZE,
																						170,
																						Short.MAX_VALUE))
																.addComponent(
																		fontDropDown,
																		0,
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(10, 10,
																		10)
																.addComponent(
																		boldButton)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		italicsButton)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		fontSize,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createParallelGroup(
																GroupLayout.Alignment.LEADING,
																false)
																.addComponent(
																		jScrollPane1,
																		0,
																		0,
																		Short.MAX_VALUE)
																.addGroup(
																		GroupLayout.Alignment.TRAILING,
																		layout.createSequentialGroup()
																				.addComponent(
																						layerUpButton,
																						GroupLayout.PREFERRED_SIZE,
																						28,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						layerDownButton,
																						GroupLayout.PREFERRED_SIZE,
																						28,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						editButton,
																						GroupLayout.PREFERRED_SIZE,
																						28,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						duplicateButton,
																						GroupLayout.PREFERRED_SIZE,
																						28,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						shadowButton,
																						GroupLayout.PREFERRED_SIZE,
																						28,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						deleteLayerButton,
																						GroupLayout.PREFERRED_SIZE,
																						28,
																						GroupLayout.PREFERRED_SIZE))
																.addComponent(
																		jLabel3)))
								.addContainerGap(20, Short.MAX_VALUE)));

		layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {
				circleButton, lineButton, moveButton, polygonButton,
				rectButton, stringButton });

		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										fillColorPanel,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(
																										layout.createParallelGroup(
																												GroupLayout.Alignment.TRAILING)
																												.addGroup(
																														layout.createParallelGroup(
																																GroupLayout.Alignment.BASELINE)
																																.addComponent(
																																		strokeBox)
																																.addComponent(
																																		strokeSpinner,
																																		GroupLayout.PREFERRED_SIZE,
																																		GroupLayout.DEFAULT_SIZE,
																																		GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																		rroptionsButton))
																												.addComponent(
																														strokeColorPanel,
																														GroupLayout.PREFERRED_SIZE,
																														GroupLayout.DEFAULT_SIZE,
																														GroupLayout.PREFERRED_SIZE)))
																				.addComponent(
																						fillBox))
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						textBox,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel2))
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jLabel1)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		fontDropDown,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						boldButton)
																				.addComponent(
																						italicsButton)
																				.addComponent(
																						fontSize,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jLabel3)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jScrollPane1,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createParallelGroup(
																								GroupLayout.Alignment.TRAILING)
																								.addComponent(
																										editButton,
																										GroupLayout.Alignment.LEADING)
																								.addComponent(
																										shadowButton)
																								.addComponent(
																										duplicateButton)
																								.addComponent(
																										deleteLayerButton))
																				.addComponent(
																						layerUpButton)
																				.addComponent(
																						layerDownButton)))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		stringButton)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		rectButton)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		roundRectButton)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		circleButton)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		lineButton)
																.addGap(5, 5, 5)
																.addComponent(
																		polygonButton)
																.addGap(5, 5, 5)
																.addComponent(
																		imageButton)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		moveButton)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		resizeButton)))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.TRAILING)
												.addGroup(
														layout.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
																.addComponent(
																		shadowX,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		jLabel5)
																.addComponent(
																		jLabel6)
																.addComponent(
																		shadowY,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addComponent(
														shadowColorPanel,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel4))
								.addContainerGap(22, Short.MAX_VALUE)));

		layout.linkSize(SwingConstants.VERTICAL, new Component[] {
				circleButton, lineButton, moveButton, polygonButton,
				rectButton, stringButton });

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void formWindowClosing(WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
		if (JOptionPane.showConfirmDialog(this, "Do you really want to exit?",
				"Confirm", JOptionPane.YES_NO_OPTION) == 0)
			System.exit(0);
	}// GEN-LAST:event_formWindowClosing

	private void layerUpButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_layerUpButtonActionPerformed
		layers.moveSelectedUp();
	}// GEN-LAST:event_layerUpButtonActionPerformed

	private void deleteLayerButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_deleteLayerButtonActionPerformed
		layers.removeSelected();
		checkLayerTools();
	}// GEN-LAST:event_deleteLayerButtonActionPerformed

	private void layerDownButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_layerDownButtonActionPerformed
		layers.moveSelectedDown();
	}// GEN-LAST:event_layerDownButtonActionPerformed

	private void fillBoxActionPerformed(ActionEvent evt) {// GEN-FIRST:event_fillBoxActionPerformed
		if (!strokeBox.isSelected() || stringButton.isSelected())
			fillBox.setSelected(true);
		if (editing
				&& !(layers.getSelected() instanceof PLine || layers
						.getSelected() instanceof PImage)) {
			layers.getSelected().color = fillBox.isSelected() ? fillColorPanel
					.getBackground() : null;
		}
	}// GEN-LAST:event_fillBoxActionPerformed

	private void strokeBoxActionPerformed(ActionEvent evt) {// GEN-FIRST:event_strokeBoxActionPerformed
		if (!fillBox.isSelected() || lineButton.isSelected())
			strokeBox.setSelected(true);
		if (editing
				&& !(layers.getSelected() instanceof PString || layers
						.getSelected() instanceof PImage)) {
			layers.getSelected().strokeColor = strokeBox.isSelected() ? strokeColorPanel
					.getBackground() : null;
			layers.getSelected().stroke = getStroke();
		}
	}// GEN-LAST:event_strokeBoxActionPerformed

	private void fontDropDownActionPerformed(ActionEvent evt) {// GEN-FIRST:event_fontDropDownActionPerformed
		updateFont();
	}// GEN-LAST:event_fontDropDownActionPerformed

	private void boldButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_boldButtonActionPerformed
		updateFont();
	}// GEN-LAST:event_boldButtonActionPerformed

	private void italicsButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_italicsButtonActionPerformed
		updateFont();
	}// GEN-LAST:event_italicsButtonActionPerformed

	private void editButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_editButtonActionPerformed
		editing = editButton.isSelected();
		if (editing)
			setOptions(layers.getSelected());
	}// GEN-LAST:event_editButtonActionPerformed

	private void fillColorPanelMouseReleased(MouseEvent evt) {// GEN-FIRST:event_fillColorPanelMouseReleased
		colorChooser.setTarget(fillColorPanel);
		colorChooser.setVisible(true);
	}// GEN-LAST:event_fillColorPanelMouseReleased

	private void strokeSpinnerStateChanged(ChangeEvent evt) {// GEN-FIRST:event_strokeSpinnerStateChanged
		if (editing
				&& !(layers.getSelected() instanceof PImage || layers
						.getSelected() instanceof PString))
			layers.getSelected().stroke = new BasicStroke(
					((Integer) strokeSpinner.getValue()).intValue());
	}// GEN-LAST:event_strokeSpinnerStateChanged

	private void strokeColorPanelMouseReleased(MouseEvent evt) {// GEN-FIRST:event_strokeColorPanelMouseReleased
		colorChooser.setTarget(strokeColorPanel);
		colorChooser.setVisible(true);
	}// GEN-LAST:event_strokeColorPanelMouseReleased

	private void layerListValueChanged(ListSelectionEvent evt) {// GEN-FIRST:event_layerListValueChanged
		checkLayerTools();
		if (editing)
			setOptions(layers.getSelected());
	}// GEN-LAST:event_layerListValueChanged

	private void duplicateButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_duplicateButtonActionPerformed
		Paintable p = layers.getSelected();
		if (p != null)
			layers.insert(p.duplicate());
	}// GEN-LAST:event_duplicateButtonActionPerformed

	private void fontSizeStateChanged(ChangeEvent evt) {// GEN-FIRST:event_fontSizeStateChanged
		if (editing && layers.getSelected() instanceof PString)
			updateFont();
	}// GEN-LAST:event_fontSizeStateChanged

	private void textBoxKeyReleased(KeyEvent evt) {// GEN-FIRST:event_textBoxKeyReleased
		if (editing && layers.getSelected() instanceof PString) {
			((PString) layers.getSelected()).text = textBox.getText();
		}
	}// GEN-LAST:event_textBoxKeyReleased

	private void moveButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_moveButtonActionPerformed
		editing = false;
		canvas.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		canvas.reset();
	}// GEN-LAST:event_moveButtonActionPerformed

	private void stringButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_stringButtonActionPerformed
		editing = false;
		fillBox.setSelected(true);
		canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		canvas.reset();
	}// GEN-LAST:event_stringButtonActionPerformed

	private void rectButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_rectButtonActionPerformed
		editing = false;
		canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		canvas.reset();
	}// GEN-LAST:event_rectButtonActionPerformed

	private void circleButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_circleButtonActionPerformed
		editing = false;
		canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		canvas.reset();
	}// GEN-LAST:event_circleButtonActionPerformed

	private void lineButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_lineButtonActionPerformed
		editing = false;
		strokeBox.setSelected(true);
		canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		canvas.reset();
	}// GEN-LAST:event_lineButtonActionPerformed

	private void polygonButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_polygonButtonActionPerformed
		editing = false;
		canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		canvas.reset();
	}// GEN-LAST:event_polygonButtonActionPerformed

	private void clearOptionActionPerformed(ActionEvent evt) {// GEN-FIRST:event_clearOptionActionPerformed
		if (layers.getSize() > 0
				&& JOptionPane.showConfirmDialog(this,
						"Do you really want to clear the screen?", "Confirm",
						JOptionPane.YES_NO_OPTION) == 0)
			layers.clear();
	}// GEN-LAST:event_clearOptionActionPerformed

	private void layerListKeyReleased(KeyEvent evt) {// GEN-FIRST:event_layerListKeyReleased
		switch (evt.getKeyCode()) {
		case KeyEvent.VK_DELETE:
			layers.removeSelected();
			break;
		case KeyEvent.VK_U:
			layers.moveSelectedUp();
			break;
		case KeyEvent.VK_D:
			layers.moveSelectedDown();
			break;
		}
	}// GEN-LAST:event_layerListKeyReleased

	private void shadowButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_shadowButtonActionPerformed
		Paintable selection = layers.getSelected();
		if (selection != null) {
			Paintable p;
			if (selection instanceof PImage)
				p = new PRectangle(selection.x, selection.y, selection.w,
						selection.h, Color.BLACK, null, null);
			else
				p = selection.duplicate();
			int moveX = ((Integer) shadowX.getValue()).intValue(), moveY = ((Integer) shadowY
					.getValue()).intValue();
			p.x += moveX;
			p.y += moveY;
			if (p instanceof PPolygon)
				((PPolygon) p).move(moveX, moveY);
			else if (p instanceof PLine)
				((PLine) p).move(moveX, moveY);
			if (p.color != null)
				p.color = shadowColorPanel.getBackground();
			if (p.strokeColor != null)
				p.strokeColor = shadowColorPanel.getBackground();
			selection.shadow = p;
			layers.add(layers.getSelectedIndex(), p);
		}
	}// GEN-LAST:event_shadowButtonActionPerformed

	private void shadowColorPanelMouseReleased(MouseEvent evt) {// GEN-FIRST:event_shadowColorPanelMouseReleased
		colorChooser.setTarget(shadowColorPanel);
		colorChooser.setVisible(true);
	}// GEN-LAST:event_shadowColorPanelMouseReleased

	private void gridOptionsOptionActionPerformed(ActionEvent evt) {// GEN-FIRST:event_gridOptionsOptionActionPerformed
		gridOptions.setLocationRelativeTo(this);
		gridOptions.setVisible(true);
	}// GEN-LAST:event_gridOptionsOptionActionPerformed

	private void roundRectButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_roundRectButtonActionPerformed
		editing = false;
		canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		canvas.reset();
	}// GEN-LAST:event_roundRectButtonActionPerformed

	private void rroptionsButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_rroptionsButtonActionPerformed
		roundRectOptions.setLocationRelativeTo(this);
		roundRectOptions.setVisible(true);
	}// GEN-LAST:event_rroptionsButtonActionPerformed

	private void resizeButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_resizeButtonActionPerformed
		canvas.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
		canvas.reset();
	}// GEN-LAST:event_resizeButtonActionPerformed

	private void openOptionActionPerformed(ActionEvent evt) {// GEN-FIRST:event_openOptionActionPerformed
		if (layers.getSize() > 0
				&& JOptionPane
						.showConfirmDialog(
								this,
								"Opening a project will clear the screen. Are you sure?",
								"Confirm", JOptionPane.YES_NO_OPTION) != 0)
			return;
		open();
	}// GEN-LAST:event_openOptionActionPerformed

	private void saveAsOptionActionPerformed(ActionEvent evt) {// GEN-FIRST:event_saveAsOptionActionPerformed
		saveAs();
	}// GEN-LAST:event_saveAsOptionActionPerformed

	private void saveOptionActionPerformed(ActionEvent evt) {// GEN-FIRST:event_saveOptionActionPerformed
		save();
	}// GEN-LAST:event_saveOptionActionPerformed

	private void imageButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_imageButtonActionPerformed
		editing = false;
		canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		String enteredURL;
		Image loaded = PImage.getImageFrom((enteredURL = JOptionPane
				.showInputDialog(this, "Enter the image's URL.")));
		if (loaded == null) {
			JOptionPane.showMessageDialog(this, "Invalid URL!");
			return;
		} else if (loaded == PImage.IMG_ERROR_IMG)
			JOptionPane
					.showMessageDialog(this,
							"Could not load the image, but you can still add it to your paint.");
		loadedImage = loaded;
		imageURL = enteredURL;
	}// GEN-LAST:event_imageButtonActionPerformed

	private void backgroundOpActionPerformed(ActionEvent evt) {// GEN-FIRST:event_backgroundOpActionPerformed
		colorChooser.setTarget(dummy);
		colorChooser.setVisible(true);
	}// GEN-LAST:event_backgroundOpActionPerformed

	public Image getImage() {
		return loadedImage;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void open() {
		if (openDialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File f = openDialog.getSelectedFile();
			if (!f.exists()) {
				JOptionPane.showMessageDialog(this, "File does not exist!");
				return;
			}
			ArrayList<Paintable> newLayers = PaintIO.open(f, this);
			if (newLayers == null) {
				JOptionPane.showMessageDialog(this, "Error reading file!");
				return;
			}
			file = f;
			canvasFrame.setTitle("Preview of " + f.getName());
			layers.clear();
			layers.getArrayList().addAll(newLayers);
			layerList.updateUI();
			checkLayerTools();
		}
	}

	public void save() {
		if (file == null)
			saveAs();
		else
			PaintIO.saveLayersAs(layers.getArrayList(), file, this);
	}

	public void saveAs() {
		if (saveDialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File f = saveDialog.getSelectedFile();
			FileExtensionFilter fef = (FileExtensionFilter) saveDialog
					.getFileFilter();
			if (fef == null) {
				fef = defaultFileFilter;
			}
			String name = f.getName();
			if (!name.endsWith(fef.ext))
				f = new File(f.getParent(), name + fef.ext);
			if (f.exists()
					&& JOptionPane
							.showConfirmDialog(
									this,
									"Do you really want to overwrite "
											+ f.getName() + "?", "Confirm",
									JOptionPane.YES_NO_OPTION) != 0)
				return;
			if (PaintIO.saveLayersAs(layers.getArrayList(), f, this)) {
				file = f;
				canvasFrame.setTitle("Preview of " + f.getName());
			}
		}
	}

	public void roundRectDialogClosed() {
		if (editing && layers.getSelected() instanceof PRoundRect) {
			PRoundRect p = (PRoundRect) layers.getSelected();
			p.arcWidth = getArcWidth();
			p.arcHeight = getArcHeight();
		}
	}

	public void checkLayerTools() {
		Paintable selection = layers.getSelected();
		boolean enabled = selection != null;

		boolean resizable = selection instanceof PRectangle
				|| selection instanceof PRoundRect
				|| selection instanceof PCircle;
		moveButton.setEnabled(enabled);
		layerUpButton.setEnabled(enabled);
		layerDownButton.setEnabled(enabled);
		deleteLayerButton.setEnabled(enabled);
		editButton.setEnabled(enabled);
		duplicateButton.setEnabled(enabled);
		shadowButton.setEnabled(enabled);
		resizeButton.setEnabled(enabled && resizable);
		if (!fillBox.isEnabled())
			fillBox.setSelected(false);
		editing = enabled && editing;
	}

	public Color getGridColor() {
		return gridOptions.gridColorPanel.getBackground();
	}

	public int getArcWidth() {
		return ((Integer) roundRectOptions.arcWidth.getValue()).intValue();
	}

	public int getArcHeight() {
		return ((Integer) roundRectOptions.arcHeight.getValue()).intValue();
	}

	public Color getCanvasBackground() {
		return dummy.getBackground();
	}

	public void colorChosen(JPanel p) {
		if (p == dummy) {
			Graphics g = background.getGraphics();
			g.setColor(dummy.getBackground());
			g.fillRect(0, 0, 16, 16);
			return;
		}
		if (!editing)
			return;
		if (p == fillColorPanel && fillBox.isSelected()) {
			layers.getSelected().color = fillColorPanel.getBackground();
		} else if (p == strokeColorPanel && strokeBox.isSelected())
			layers.getSelected().strokeColor = strokeColorPanel.getBackground();
		else if (p == shadowColorPanel && layers.selectedIndexIsValid()) {
			Paintable shadow = layers.getSelected().shadow;
			if (shadow.strokeColor != null)
				shadow.strokeColor = shadowColorPanel.getBackground();
			if (shadow.color != null)
				shadow.color = shadowColorPanel.getBackground();
		}
	}

	private void setOptions(Paintable p) {
		if (p.stroke != null && p.strokeColor != null) {
			strokeColorPanel.setBackground(p.strokeColor);
			strokeSpinner.setValue(new Integer((int) p.stroke.getLineWidth()));
			strokeBox.setSelected(true);
		} else
			strokeBox.setSelected(false);
		if (p.color != null) {
			fillColorPanel.setBackground(p.color);
			fillBox.setSelected(true);
		} else
			fillBox.setSelected(false);
		if (p instanceof PString) {
			textBox.setText(((PString) p).text);
			Font f = ((PString) p).font;
			fontDropDown.setSelectedItem(f.getName());
			int flags = f.getStyle();
			boldButton.setSelected((flags & Font.BOLD) > 0);
			italicsButton.setSelected((flags & Font.ITALIC) > 0);
			fontSize.setValue(new Integer(f.getSize()));
		} else if (p instanceof PRoundRect) {
			PRoundRect prr = (PRoundRect) p;
			roundRectOptions.arcWidth.setValue(prr.arcWidth);
			roundRectOptions.arcHeight.setValue(prr.arcHeight);
		}
	}

	public static final int MOVE = 0, TEXT = 1, RECT = 2, CIRCLE = 3, LINE = 4,
			POLY = 5, ROUND = 6, RESIZE = 7, IMAGE = 8;

	public int getSelectedTool() {
		if (moveButton.isSelected())
			return MOVE;
		if (stringButton.isSelected())
			return TEXT;
		if (rectButton.isSelected())
			return RECT;
		if (circleButton.isSelected())
			return CIRCLE;
		if (lineButton.isSelected())
			return LINE;
		if (polygonButton.isSelected())
			return POLY;
		if (roundRectButton.isSelected())
			return ROUND;
		if (resizeButton.isSelected())
			return RESIZE;
		if (imageButton.isSelected())
			return IMAGE;
		return -1;
	}

	public int getGridWidth() {
		return ((Integer) gridOptions.cellWidth.getValue()).intValue();
	}

	public int getGridHeight() {
		return ((Integer) gridOptions.cellHeight.getValue()).intValue();
	}

	public Font getSelectedFont() {
		int flags = 0;
		if (boldButton.isSelected())
			flags |= Font.BOLD;
		if (italicsButton.isSelected())
			flags |= Font.ITALIC;
		return new Font((String) fontDropDown.getSelectedItem(), flags,
				((Integer) fontSize.getValue()).intValue());
	}

	private void updateFont() {
		if (editing && layers.getSelected() instanceof PString) {
			((PString) layers.getSelected()).font = getSelectedFont();
		}
	}

	public Font getPreviewFont() {
		int flags = 0;
		if (boldButton.isSelected())
			flags |= Font.BOLD;
		if (italicsButton.isSelected())
			flags |= Font.ITALIC;
		return new Font((String) fontDropDown.getSelectedItem(), flags, 11);
	}

	public BasicStroke getStroke() {
		if (strokeBox.isSelected())
			return new BasicStroke(
					((Integer) strokeSpinner.getValue()).intValue());
		return null;
	}

	public Color getStrokeColor() {
		if (strokeBox.isSelected())
			return strokeColorPanel.getBackground();
		return null;
	}

	public String getString() {
		return textBox.getText();
	}

	public Color getColor() {
		if (fillBox.isSelected())
			return fillColorPanel.getBackground();
		return null;
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
				}
				new ToolboxFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	public JRadioButtonMenuItem aaAllOp;
	public JRadioButtonMenuItem aaTextOp;
	private ButtonGroup antialiasingGroup;
	private JMenuItem backgroundOp;
	private JToggleButton boldButton;
	private JToggleButton circleButton;
	private JMenuItem clearOption;
	private JButton deleteLayerButton;
	private JButton duplicateButton;
	private JToggleButton editButton;
	private JMenu fileMenu;
	private JCheckBox fillBox;
	private JPanel fillColorPanel;
	private JComboBox<String> fontDropDown;
	private JSpinner fontSize;
	private JMenuItem gridOptionsOption;
	private JToggleButton imageButton;
	private JToggleButton italicsButton;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JMenuBar jMenuBar1;
	private JScrollPane jScrollPane1;
	private JSeparator jSeparator1;
	private JSeparator jSeparator3;
	private JSeparator jSeparator4;
	private JButton layerDownButton;
	private JList<String> layerList;
	private JButton layerUpButton;
	private JToggleButton lineButton;
	private JToggleButton moveButton;
	private JRadioButtonMenuItem noneOp;
	private JMenuItem openOption;
	private JToggleButton polygonButton;
	private JToggleButton rectButton;
	private JToggleButton resizeButton;
	private JToggleButton roundRectButton;
	private JButton rroptionsButton;
	private JMenuItem saveAsOption;
	private JMenuItem saveOption;
	private JButton shadowButton;
	private JPanel shadowColorPanel;
	private JSpinner shadowX;
	private JSpinner shadowY;
	public JCheckBoxMenuItem showGridBox;
	public JCheckBoxMenuItem snap2GridBox;
	private JToggleButton stringButton;
	private JCheckBox strokeBox;
	private JPanel strokeColorPanel;
	private JSpinner strokeSpinner;
	private JTextField textBox;
	private ButtonGroup toolsGroup;
	private JMenu viewMenu;

	// End of variables declaration//GEN-END:variables

	private class FileExtensionFilter extends FileFilter {

		private final String desc;
		public final String ext;

		public FileExtensionFilter(String desc, String ext) {
			this.desc = desc + " (" + ext + ")";
			this.ext = ext;
		}

		@Override
		public boolean accept(File f) {
			return f.isDirectory() || f.getName().endsWith(ext);
		}

		@Override
		public String getDescription() {
			return desc;
		}

	}

}
