/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autograph.ui;

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

		setBackground(new java.awt.Color(255, 255, 255));
		setMinimumSize(new java.awt.Dimension(200, 300));
		setPreferredSize(new java.awt.Dimension(200, 459));
		
		// Edge Options Panel
		EdgeOptionsPanel = new JPanel();
		EdgeOptionsPanel.setPreferredSize(new java.awt.Dimension(200, 457));
		
		// Edge Options Title Label
		EdgeOptionsTitleLabel = new JLabel();
		EdgeOptionsTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); 
		EdgeOptionsTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		EdgeOptionsTitleLabel.setText("Edge Options");
		EdgeOptionsTitleLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		EdgeOptionsTitleLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
		
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
		EdgeDesignComboBox.setModel(new DefaultComboBoxModel(new String[] { "____", "_ _ _", "- - - -", "......", "===", " " }));
		
		// End Shape Label
		EndShapeLabel = new JLabel();
		EndShapeLabel.setText("End Shape");
		
		// End Shape Combo Box
		EndShapeComboBox = new JComboBox();
		EndShapeComboBox.setModel(new DefaultComboBoxModel(new String[] { "A-A", "A-B", "A-C", "B-B", "B-C", "C-C" }));
		
		//Edge Color Label
		EdgeColorLabel = new JLabel();
		EdgeColorLabel.setText("Edge Color");
		
		// Edge Color Button
		EdgeColorBtn = new JButton();
		EdgeColorBtn.setText("[  ]");
		EdgeColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
		
		// Edge Design Label
		EdgeDesignLabel = new JLabel();
		EdgeDesignLabel.setText("Edge Design");
		
		// Text Color Button
		TextColorBtn = new JButton();
		TextColorBtn.setText("[  ]");
		TextColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
		
		// Panel Filler
		filler1 = new Box.Filler(new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 32767));

		// Layout
		GroupLayout EdgeOptionsPanelLayout = new GroupLayout(EdgeOptionsPanel);
		EdgeOptionsPanel.setLayout(EdgeOptionsPanelLayout);
		EdgeOptionsPanelLayout.setHorizontalGroup(
				EdgeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(EdgeOptionsTitleLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(EdgeOptionsPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(EdgeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(EdgeOptionsPanelLayout.createSequentialGroup()
										.addGroup(EdgeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(TextColorLabel)
												.addComponent(EndShapeLabel)
												.addComponent(EdgeColorLabel)
												.addComponent(EdgeDesignLabel))
												.addGap(18, 18, 18)
												.addGroup(EdgeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(EndShapeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(EdgeColorBtn)
														.addComponent(EdgeDesignComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(TextColorBtn))
														.addGap(0, 0, Short.MAX_VALUE))
														.addGroup(EdgeOptionsPanelLayout.createSequentialGroup()
																.addGroup(EdgeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(LabelTextField)
																		.addGroup(EdgeOptionsPanelLayout.createSequentialGroup()
																				.addComponent(LabelTextLabel)
																				.addGap(0, 0, Short.MAX_VALUE)))
																				.addContainerGap())))
				);
		EdgeOptionsPanelLayout.setVerticalGroup(
				EdgeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(EdgeOptionsPanelLayout.createSequentialGroup()
						.addComponent(EdgeOptionsTitleLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(LabelTextLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(LabelTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(14, 14, 14)
						.addGroup(EdgeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(EdgeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(TextColorLabel)
										.addComponent(TextColorBtn))
										.addGroup(EdgeOptionsPanelLayout.createSequentialGroup()
												.addGap(41, 41, 41)
												.addGroup(EdgeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(EndShapeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(EndShapeLabel))
														.addGap(18, 18, 18)
														.addGroup(EdgeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(EdgeColorBtn)
																.addComponent(EdgeColorLabel))
																.addGap(18, 18, 18)
																.addGroup(EdgeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(EdgeDesignComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addComponent(EdgeDesignLabel))))
																		.addContainerGap(203, Short.MAX_VALUE))
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
}
