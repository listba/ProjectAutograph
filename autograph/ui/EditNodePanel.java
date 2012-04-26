/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autograph.ui;

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

		setBackground(new java.awt.Color(255, 255, 255));
		setMinimumSize(new java.awt.Dimension(200, 300));
		setPreferredSize(new java.awt.Dimension(200, 459));

		// Node Options Panel
		NodeOptionsPanel = new JPanel();
		NodeOptionsPanel.setPreferredSize(new java.awt.Dimension(200, 457));

		// Node Options Title Label
		NodeOptionsTitleLabel = new JLabel();
		NodeOptionsTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		NodeOptionsTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		NodeOptionsTitleLabel.setText("Node Options");
		NodeOptionsTitleLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		NodeOptionsTitleLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

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
		NodeShapeComboBox.setModel(new DefaultComboBoxModel(new String[] { "AA", "BB", "CC", "DD", "EE", "FF" }));

		// Border Color Label
		BorderColorLabel = new JLabel();
		BorderColorLabel.setText("Border Color");

		// Border Color Button
		BorderColorBtn = new JButton();
		BorderColorBtn.setText("[  ]");
		BorderColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));

		// Fill Color Label
		FillColorLabel = new JLabel();
		FillColorLabel.setText("Fill Color");

		// Fill Color Button
		FillColorBtn = new JButton();
		FillColorBtn.setText("[  ]");
		FillColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));

		// Text Color Label
		TextColorLabel = new JLabel();
		TextColorLabel.setText("Text Color");

		// Text Color Button
		TextColorBtn = new JButton();
		TextColorBtn.setText("[  ]");
		TextColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));

		// Panel Filler
		filler1 = new Box.Filler(new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 32767));

		// Layout
		GroupLayout NodeOptionsPanelLayout = new GroupLayout(NodeOptionsPanel);
		NodeOptionsPanel.setLayout(NodeOptionsPanelLayout);
		NodeOptionsPanelLayout.setHorizontalGroup(
				NodeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(NodeOptionsTitleLabel, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
				.addGroup(NodeOptionsPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(NodeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(LabelTextField)
								.addGroup(NodeOptionsPanelLayout.createSequentialGroup()
										.addGroup(NodeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(LabelTextLabel)
												.addGroup(NodeOptionsPanelLayout.createSequentialGroup()
														.addGroup(NodeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(TextColorLabel)
																.addComponent(NodeShapeLabel)
																.addComponent(BorderColorLabel)
																.addComponent(FillColorLabel))
																.addGap(18, 18, 18)
																.addGroup(NodeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NodeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(NodeShapeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addComponent(TextColorBtn))
																				.addGroup(NodeOptionsPanelLayout.createSequentialGroup()
																						.addGroup(NodeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addComponent(FillColorBtn)
																								.addComponent(BorderColorBtn))
																								.addGap(10, 10, 10)))))
																								.addGap(0, 0, Short.MAX_VALUE)))
																								.addContainerGap())
				);
		NodeOptionsPanelLayout.setVerticalGroup(
				NodeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(NodeOptionsPanelLayout.createSequentialGroup()
						.addComponent(NodeOptionsTitleLabel)
						.addGap(18, 18, 18)
						.addComponent(LabelTextLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(LabelTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(14, 14, 14)
						.addGroup(NodeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(NodeOptionsPanelLayout.createSequentialGroup()
										.addGroup(NodeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(TextColorLabel)
												.addComponent(TextColorBtn))
												.addGap(18, 18, 18)
												.addGroup(NodeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(NodeShapeLabel)
														.addComponent(NodeShapeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addGap(18, 18, 18)
														.addGroup(NodeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(BorderColorLabel)
																.addComponent(BorderColorBtn)))
																.addGroup(NodeOptionsPanelLayout.createSequentialGroup()
																		.addGap(120, 120, 120)
																		.addGroup(NodeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(FillColorBtn)
																				.addComponent(FillColorLabel))))
																				.addContainerGap(212, Short.MAX_VALUE))
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
}
