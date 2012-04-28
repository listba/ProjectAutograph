/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autograph.ui;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class EditEdgePanel extends JPanel {

	/**
	 * Creates new form AddNodePanel
	 */
	public EditEdgePanel() {
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
		edgeColor = Color.BLACK;

		// Edge Options Panel
		EdgeOptionsPanel = new JPanel();
		EdgeOptionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		EdgeOptionsPanel.setPreferredSize(new java.awt.Dimension(200, 512));

		// Edge Options Title Label
		EdgeOptionsTitleLabel = new JLabel();
		EdgeOptionsTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); 
		EdgeOptionsTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		EdgeOptionsTitleLabel.setText("Edge Options");

		// Label Text Label
		LabelTextLabel = new JLabel();
		LabelTextLabel.setText("Label Text");

		// Label Text Field
		LabelTextField = new JTextField();

		// Text Color Label
		TextColorLabel = new JLabel();
		TextColorLabel.setText("Text Color");

		// Edge Design Combo Box
		EdgeDesignComboBox = new JComboBox();
		EdgeDesignComboBox.setModel(new DefaultComboBoxModel(new String[] {"Solid", "Dotted", "Dashed"}));

		// End Shape Label
		EndShapeLabel = new JLabel();
		EndShapeLabel.setText("End Shape");

		// End Shape Combo Box
		EndShapeComboBox = new JComboBox();
		EndShapeComboBox.setModel(new DefaultComboBoxModel(new String[] {"None", "Line Arrow", "Shape Arrow", "Filled Arrow"}));

		//Edge Color Label
		EdgeColorLabel = new JLabel();
		EdgeColorLabel.setText("Edge Color");

		// Edge Color Button
		EdgeColorBtn = new JButton();
		EdgeColorBtn.setText("Select Color");
		EdgeColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
		EdgeColorBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				EdgeColorBtnActionPerformed(evt);
			}
		});

		// Edge Design Label
		EdgeDesignLabel = new JLabel();
		EdgeDesignLabel.setText("Edge Design");

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
		javax.swing.GroupLayout AddEdgePanelLayout = new GroupLayout(EdgeOptionsPanel);
		EdgeOptionsPanel.setLayout(AddEdgePanelLayout);
		AddEdgePanelLayout.setHorizontalGroup(
				AddEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(AddEdgePanelLayout.createSequentialGroup()
						.addGroup(AddEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))
								.addGap(0, 0, Short.MAX_VALUE))
								.addGroup(AddEdgePanelLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(AddEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(EdgeOptionsTitleLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(LabelTextField, 0, GroupLayout.DEFAULT_SIZE, 190)
												.addGroup(javax.swing.GroupLayout.Alignment.LEADING, AddEdgePanelLayout.createSequentialGroup()
														.addGap(0, 0, Short.MAX_VALUE)
														.addComponent(SaveButton))
														.addGroup(AddEdgePanelLayout.createSequentialGroup()
																.addGroup(AddEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																		.addGroup(AddEdgePanelLayout.createSequentialGroup()
																				.addGroup(AddEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(LabelTextLabel)
																						.addComponent(EndShapeLabel)
																						.addComponent(EdgeColorLabel))
																						.addGap(10, 10, 10)
																						.addGroup(AddEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addComponent(EdgeColorBtn, 0, GroupLayout.DEFAULT_SIZE, 120)
																								.addComponent(TextColorBtn, 0, GroupLayout.DEFAULT_SIZE, 120)
																								.addComponent(EdgeDesignComboBox, 0, GroupLayout.DEFAULT_SIZE, 120)
																								.addComponent(EndShapeComboBox, 0, GroupLayout.DEFAULT_SIZE, 120)))
																								.addGroup(AddEdgePanelLayout.createSequentialGroup()
																										.addGroup(AddEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(EdgeDesignLabel)
																												.addComponent(TextColorLabel))
																												.addGap(18, 18, 18)
																												.addGroup(AddEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))))
																												.addGap(0, 0, Short.MAX_VALUE)))
																												.addContainerGap())
				);
		AddEdgePanelLayout.setVerticalGroup(
				AddEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(AddEdgePanelLayout.createSequentialGroup()
						.addComponent(EdgeOptionsTitleLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(LabelTextLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(LabelTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(AddEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(TextColorLabel)
								.addComponent(TextColorBtn))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(AddEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(EdgeDesignLabel)
										.addComponent(EdgeDesignComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(AddEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(EndShapeLabel)
												.addComponent(EndShapeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(AddEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(EdgeColorLabel)
														.addComponent(EdgeColorBtn))
														.addGap(18, 18, 18)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
								.addComponent(EdgeOptionsPanel, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
								.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(EdgeOptionsPanel, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0))
				);
	}

	/*
	 * The Label Color Button was clicked
	 */
	protected void TextColorBtnActionPerformed(MouseEvent evt) {
		Color newColor = JColorChooser.showDialog(EditEdgePanel.this, "Choose Label Color", labelColor);

		if (newColor != null) {
			labelColor = newColor;
		}
	}

	/*
	 * The Edge Color Button was clicked
	 */
	protected void EdgeColorBtnActionPerformed(MouseEvent evt) {
		Color newColor = JColorChooser.showDialog(EditEdgePanel.this, "Choose Edge Color", edgeColor);

		if (newColor != null) {
			edgeColor = newColor;
		}
	}

	// Variables declaration
	private JButton EdgeColorBtn;
	private JLabel EdgeColorLabel;
	private JComboBox EdgeDesignComboBox;
	private JLabel EdgeDesignLabel;
	private JPanel EdgeOptionsPanel;
	private JLabel EdgeOptionsTitleLabel;
	private JComboBox EndShapeComboBox;
	private JLabel EndShapeLabel;
	private JTextField LabelTextField;
	private JLabel LabelTextLabel;
	private JButton TextColorBtn;
	private JLabel TextColorLabel;
	private Box.Filler filler1;
	private JButton SaveButton;

	protected Color labelColor;
	protected Color edgeColor;
}
