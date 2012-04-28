/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autograph.ui;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class EditNodePanel extends JPanel {

	/**
	 * Creates new form AddNodePanel
	 */
	public EditNodePanel() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	@SuppressWarnings("unchecked")
	private void initComponents() {

		// Setup
		setBackground(new java.awt.Color(255, 255, 255));
		setMinimumSize(new java.awt.Dimension(200, 512));
		setPreferredSize(new java.awt.Dimension(200, 512));

		// The Colors
		labelColor = Color.BLACK;
		fillColor = Color.WHITE;
		borderColor = Color.BLACK;

		// Node Options Panel
		NodeOptionsPanel = new JPanel();
		NodeOptionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		NodeOptionsPanel.setPreferredSize(new java.awt.Dimension(200, 512));

		// Node Options Title Label
		NodeOptionsTitleLabel = new JLabel();
		NodeOptionsTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
		NodeOptionsTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		NodeOptionsTitleLabel.setText("Node Options");

		// Label Text Label
		LabelTextLabel = new JLabel();
		LabelTextLabel.setText("Label Text");

		// Label Text Field
		LabelTextField = new JTextField();

		// Node Shape Label
		NodeShapeLabel = new JLabel();
		NodeShapeLabel.setText("Node Shape");

		// Node Shape Combo Box
		NodeShapeComboBox = new JComboBox();
		NodeShapeComboBox.setModel(new DefaultComboBoxModel(new String[] { "Circle", "Oval", "Square", "Rectangle", "Triangle" }));

		// Border Color Label
		BorderColorLabel = new JLabel();
		BorderColorLabel.setText("Border Color");

		// Border Color Button
		BorderColorBtn = new JButton();
		BorderColorBtn.setText("Select Color");
		BorderColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
		BorderColorBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				BorderColorBtnActionPerformed(evt);
			}
		});

		// Fill Color Label
		FillColorLabel = new JLabel();
		FillColorLabel.setText("Fill Color");

		// Fill Color Button
		FillColorBtn = new JButton();
		FillColorBtn.setText("Select Color");
		FillColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
		FillColorBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				FillColorBtnActionPerformed(evt);
			}
		});

		// Text Color Label
		TextColorLabel = new JLabel();
		TextColorLabel.setText("Text Color");

		// Text Color Button
		TextColorBtn = new JButton();
		TextColorBtn.setText("Select Color");
		TextColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
		TextColorBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				TextColorBtnActionPerformed(evt);
			}
		});

		// Panel Filler
		filler1 = new Box.Filler(new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 32767));

		// Save Button
		SaveButton = new JButton();
		SaveButton.setText("Save Changes");

		// Layout
		javax.swing.GroupLayout AddNodePanelLayout = new GroupLayout(NodeOptionsPanel);
		NodeOptionsPanel.setLayout(AddNodePanelLayout);
		AddNodePanelLayout.setHorizontalGroup(
				AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(AddNodePanelLayout.createSequentialGroup()
						.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))
								.addGap(0, 0, Short.MAX_VALUE))
								.addGroup(AddNodePanelLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(NodeOptionsTitleLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(LabelTextField, 0, GroupLayout.DEFAULT_SIZE, 190)
												.addGroup(javax.swing.GroupLayout.Alignment.LEADING, AddNodePanelLayout.createSequentialGroup()
														.addGap(0, 0, Short.MAX_VALUE)
														.addComponent(SaveButton))
														.addGroup(AddNodePanelLayout.createSequentialGroup()
																.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																		.addGroup(AddNodePanelLayout.createSequentialGroup()
																				.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(LabelTextLabel)
																						.addComponent(BorderColorLabel)
																						.addComponent(FillColorLabel))
																						.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addComponent(FillColorBtn, 0, GroupLayout.DEFAULT_SIZE, 120)
																								.addComponent(BorderColorBtn, 0, GroupLayout.DEFAULT_SIZE, 120)
																								.addComponent(TextColorBtn, 0, GroupLayout.DEFAULT_SIZE, 120)
																								.addComponent(NodeShapeComboBox, 0, GroupLayout.DEFAULT_SIZE, 120)))
																								.addGroup(AddNodePanelLayout.createSequentialGroup()
																										.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(NodeShapeLabel)
																												.addComponent(TextColorLabel))
																												.addGap(18, 18, 18)
																												.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))))
																												.addGap(0, 0, Short.MAX_VALUE)))
																												.addContainerGap())

				);
		AddNodePanelLayout.setVerticalGroup(
				AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(AddNodePanelLayout.createSequentialGroup()
						.addComponent(NodeOptionsTitleLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(LabelTextLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(LabelTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(TextColorLabel)
								.addComponent(TextColorBtn))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(NodeShapeLabel)
										.addComponent(NodeShapeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(BorderColorLabel)
												.addComponent(BorderColorBtn))
												.addGap(18, 18, 18)
												.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(FillColorLabel)
														.addComponent(FillColorBtn))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
														.addComponent(SaveButton)
														.addContainerGap())
				);

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(filler1, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(NodeOptionsPanel, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
								.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(NodeOptionsPanel, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0))
				);
	}

	/*
	 * The Label Color Button was clicked
	 */
	protected void TextColorBtnActionPerformed(MouseEvent evt) {
		Color newColor = JColorChooser.showDialog(EditNodePanel.this, "Choose Label Color", labelColor);

		if (newColor != null) {
			labelColor = newColor;
		}
	}

	/*
	 * The Fill Color Button was clicked
	 */
	protected void FillColorBtnActionPerformed(MouseEvent evt) {
		Color newColor = JColorChooser.showDialog(EditNodePanel.this, "Choose Fill Color", fillColor);

		if (newColor != null) {
			fillColor = newColor;
		}
	}

	/*
	 * The Border Color Button was clicked
	 */
	protected void BorderColorBtnActionPerformed(MouseEvent evt) {
		Color newColor = JColorChooser.showDialog(EditNodePanel.this, "Choose Border Color", borderColor);

		if (newColor != null) {
			borderColor = newColor;
		}
	}

	// Variables declaration
	private JButton BorderColorBtn;
	private JLabel BorderColorLabel;
	private JButton FillColorBtn;
	private JLabel FillColorLabel;
	private JTextField LabelTextField;
	private JLabel LabelTextLabel;
	private JPanel NodeOptionsPanel;
	private JLabel NodeOptionsTitleLabel;
	private JComboBox NodeShapeComboBox;
	private JLabel NodeShapeLabel;
	private JButton TextColorBtn;
	private JLabel TextColorLabel;
	private Box.Filler filler1;
	private JButton SaveButton;

	protected Color labelColor;
	protected Color fillColor;
	protected Color borderColor;
}
