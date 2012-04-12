/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autograph.ui;

public class AddNodePanel extends javax.swing.JPanel {

	/**
	 * Creates new form AddNodePanel
	 */
	public AddNodePanel() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane5 = new javax.swing.JScrollPane();
		AddNodePanel = new javax.swing.JPanel();
		AddNodeTitleLabel = new javax.swing.JLabel();
		LabelSubtitleLabel = new javax.swing.JLabel();
		LabelTextLabel = new javax.swing.JLabel();
		LabelTextField = new javax.swing.JTextField();
		LabelFontLabel = new javax.swing.JLabel();
		LabelFontComboBox = new javax.swing.JComboBox();
		LabelColorLabel = new javax.swing.JLabel();
		LabelColorBtn = new javax.swing.JButton();
		NodeSubtitleLabel = new javax.swing.JLabel();
		NodeShapeLabel = new javax.swing.JLabel();
		BorderColorLabel = new javax.swing.JLabel();
		FillColorLabel = new javax.swing.JLabel();
		FillColorBtn = new javax.swing.JButton();
		NodeShapeComboBox = new javax.swing.JComboBox();
		BorderColorBtn = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JSeparator();
		jButton1 = new javax.swing.JButton();
		filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 32767));

		setBackground(new java.awt.Color(255, 255, 255));
		setMinimumSize(new java.awt.Dimension(200, 300));
		setPreferredSize(new java.awt.Dimension(200, 400));

		jScrollPane5.setBorder(null);
		jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		AddNodePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

		AddNodeTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		AddNodeTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		AddNodeTitleLabel.setText("Add Node");

		LabelSubtitleLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		LabelSubtitleLabel.setText("Label");

		LabelTextLabel.setText("Label Text");

		LabelFontLabel.setText("Label Font");

		LabelFontComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Times New Roman", "Ariel", "Some other font", "More fonts", "Wingdings" }));

		LabelColorLabel.setText("Label Color");

		LabelColorBtn.setText("[  ]");
		LabelColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));

		NodeSubtitleLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		NodeSubtitleLabel.setText("Node");

		NodeShapeLabel.setText("Node Shape");

		BorderColorLabel.setText("Border Color");

		FillColorLabel.setText("Fill Color");

		FillColorBtn.setText("[  ]");
		FillColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));

		NodeShapeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AA", "BB", "CC", "DD", "EE", "FF" }));

		BorderColorBtn.setText("[  ]");
		BorderColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));

		jButton1.setText("Add Node");

		javax.swing.GroupLayout AddNodePanelLayout = new javax.swing.GroupLayout(AddNodePanel);
		AddNodePanel.setLayout(AddNodePanelLayout);
		AddNodePanelLayout.setHorizontalGroup(
				AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(AddNodePanelLayout.createSequentialGroup()
						.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(LabelSubtitleLabel)
								.addComponent(NodeSubtitleLabel))
								.addGap(0, 0, Short.MAX_VALUE))
								.addGroup(AddNodePanelLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(AddNodeTitleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(LabelTextField)
												.addComponent(LabelFontComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(AddNodePanelLayout.createSequentialGroup()
														.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addGroup(AddNodePanelLayout.createSequentialGroup()
																		.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(LabelTextLabel)
																				.addComponent(LabelFontLabel)
																				.addComponent(BorderColorLabel)
																				.addComponent(FillColorLabel))
																				.addGap(24, 24, 24)
																				.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(FillColorBtn)
																						.addComponent(BorderColorBtn)))
																						.addGroup(AddNodePanelLayout.createSequentialGroup()
																								.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																										.addComponent(NodeShapeLabel)
																										.addComponent(LabelColorLabel))
																										.addGap(26, 26, 26)
																										.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(LabelColorBtn)
																												.addComponent(NodeShapeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
																												.addGap(0, 45, Short.MAX_VALUE))
																												.addComponent(jSeparator1)
																												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddNodePanelLayout.createSequentialGroup()
																														.addGap(0, 0, Short.MAX_VALUE)
																														.addComponent(jButton1)))
																														.addContainerGap())
				);
		AddNodePanelLayout.setVerticalGroup(
				AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(AddNodePanelLayout.createSequentialGroup()
						.addComponent(AddNodeTitleLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(LabelSubtitleLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(LabelTextLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(LabelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(LabelFontLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(LabelFontComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(LabelColorLabel)
								.addComponent(LabelColorBtn))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(3, 3, 3)
								.addComponent(NodeSubtitleLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(NodeShapeLabel)
										.addComponent(NodeShapeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(BorderColorLabel)
												.addComponent(BorderColorBtn))
												.addGap(18, 18, 18)
												.addGroup(AddNodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(FillColorLabel)
														.addComponent(FillColorBtn))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
														.addComponent(jButton1)
														.addContainerGap())
				);

		jScrollPane5.setViewportView(AddNodePanel);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0))
				);
	}// </editor-fold>//GEN-END:initComponents
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel AddNodePanel;
	private javax.swing.JLabel AddNodeTitleLabel;
	private javax.swing.JButton BorderColorBtn;
	private javax.swing.JLabel BorderColorLabel;
	private javax.swing.JButton FillColorBtn;
	private javax.swing.JLabel FillColorLabel;
	private javax.swing.JButton LabelColorBtn;
	private javax.swing.JLabel LabelColorLabel;
	private javax.swing.JComboBox LabelFontComboBox;
	private javax.swing.JLabel LabelFontLabel;
	private javax.swing.JLabel LabelSubtitleLabel;
	private javax.swing.JTextField LabelTextField;
	private javax.swing.JLabel LabelTextLabel;
	private javax.swing.JComboBox NodeShapeComboBox;
	private javax.swing.JLabel NodeShapeLabel;
	private javax.swing.JLabel NodeSubtitleLabel;
	private javax.swing.Box.Filler filler1;
	private javax.swing.JButton jButton1;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JSeparator jSeparator1;
	// End of variables declaration//GEN-END:variables
}
