/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autograph.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.*;

import autograph.Edge;
import autograph.Graph;
import autograph.GraphPanel;
import autograph.Node;

public class EditNodeEdgePanel extends JPanel {

	/**
	 * Creates new form EditNodeEdgePanel
	 */
	public EditNodeEdgePanel() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	@SuppressWarnings("unchecked")
	private void initComponents() {

		// Setup
		GraphicsEnvironment ge;
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		String[] fontNames = ge.getAvailableFontFamilyNames();

		setBackground(new java.awt.Color(255, 255, 255));
		setMinimumSize(new java.awt.Dimension(200, 512));
		setPreferredSize(new java.awt.Dimension(200, 512));

		// Colors
		edgeColor = Color.BLACK;
		labelColor = Color.BLACK;
		fillColor = Color.WHITE;
		borderColor = Color.BLACK;

		// Edit Pane
		EditPane = new JPanel();
		EditPane.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		EditPane.setPreferredSize(new java.awt.Dimension(200, 512));

		// Edit Title Label
		EditTitleLabel = new JLabel();
		EditTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); 
		EditTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		EditTitleLabel.setText("Edit Nodes/Edges");

		// Label Subtitle Label
		LabelSubtitleLabel = new JLabel();
		LabelSubtitleLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); 
		LabelSubtitleLabel.setText("Label");

		// Label Text Label
		LabelTextLabel = new JLabel();
		LabelTextLabel.setText("Label Text");

		// Label Text Field
		LabelTextField = new JTextField();

		// Label Font Label
		LabelFontLabel = new JLabel();
		LabelFontLabel.setText("Label Font");

		// Label Font Combo Box
		LabelFontComboBox = new JComboBox();
		LabelFontComboBox.setModel(new DefaultComboBoxModel(fontNames));
		LabelFontComboBox.setSelectedItem("Courier");

		// Label Color Label
		LabelColorLabel = new JLabel();
		LabelColorLabel.setText("Label Color");

		// Label Color Button
		LabelColorBtn = new ColorPickerButton();
		LabelColorBtn.setText("   ");
		LabelColorBtn.setColor(labelColor);
		LabelColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
		LabelColorBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				LabelColorBtnActionPerformed(evt);
			}
		});

		// Separator 1
		jSeparator1 = new JSeparator();

		// Node Subtitle Label
		NodeSubtitleLabel = new JLabel();
		NodeSubtitleLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); 
		NodeSubtitleLabel.setText("Node");

		// Border Color Label
		BorderColorLabel = new JLabel();
		BorderColorLabel.setText("Border Color");

		// Border Color Button
		BorderColorBtn = new ColorPickerButton();
		BorderColorBtn.setText("   ");
		BorderColorBtn.setColor(borderColor);
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
		FillColorBtn = new ColorPickerButton();
		FillColorBtn.setText("   ");
		FillColorBtn.setColor(fillColor);
		FillColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
		FillColorBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				FillColorBtnActionPerformed(evt);
			}
		});

		// Separator 2
		jSeparator2 = new JSeparator();

		// Edge Subtitle Label
		EdgeSubtitleLabel = new JLabel();
		EdgeSubtitleLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); 
		EdgeSubtitleLabel.setText("Edge");

		// Edge Color Label
		EdgeColorLabel = new JLabel();
		EdgeColorLabel.setText("Edge Color");

		// Edge Color Button
		EdgeColorBtn = new ColorPickerButton();
		EdgeColorBtn.setText("   ");
		EdgeColorBtn.setColor(edgeColor);
		EdgeColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
		EdgeColorBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				EdgeColorBtnActionPerformed(evt);
			}
		});

		// Save Button
		SaveButton = new JButton();
		SaveButton.setText("Save Changes");
		SaveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				SaveButtonActionPerformed(evt);
			}
		});


		// Panel Filler
		filler1 = new Box.Filler(new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 32767));	

		// Layout
		javax.swing.GroupLayout EditPaneLayout = new GroupLayout(EditPane);
		EditPane.setLayout(EditPaneLayout);
		EditPaneLayout.setHorizontalGroup(
				EditPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(EditPaneLayout.createSequentialGroup()
						.addGroup(EditPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(LabelSubtitleLabel)
								.addComponent(NodeSubtitleLabel)
								.addComponent(EdgeSubtitleLabel))
								.addGap(0, 0, Short.MAX_VALUE))
								.addGroup(EditPaneLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(EditPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(EditTitleLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(LabelTextField, 0, GroupLayout.DEFAULT_SIZE, 190)
												.addComponent(LabelFontComboBox, 0, GroupLayout.DEFAULT_SIZE, 190)
												.addComponent(jSeparator1, 0, GroupLayout.DEFAULT_SIZE, 186)
												.addComponent(jSeparator2, 0, GroupLayout.DEFAULT_SIZE, 186)
												.addGroup(javax.swing.GroupLayout.Alignment.LEADING, EditPaneLayout.createSequentialGroup()
														.addGap(0, 0, Short.MAX_VALUE)
														.addComponent(SaveButton))
														.addGroup(EditPaneLayout.createSequentialGroup()
																.addGroup(EditPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																		.addGroup(EditPaneLayout.createSequentialGroup()
																				.addGroup(EditPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(LabelTextLabel)
																						.addComponent(LabelFontLabel)
																						.addComponent(BorderColorLabel)
																						.addComponent(FillColorLabel)
																						.addComponent(EdgeColorLabel))
																						.addGap(18, 18, 18)
																						.addGroup(EditPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addComponent(FillColorBtn)
																								.addComponent(BorderColorBtn)
																								.addComponent(LabelColorBtn)
																								.addComponent(EdgeColorBtn)))
																								.addGroup(EditPaneLayout.createSequentialGroup()
																										.addGroup(EditPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(LabelColorLabel))
																												.addGap(18, 18, 18)
																												.addGroup(EditPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))))
																												.addGap(0, 0, Short.MAX_VALUE)))
																												.addContainerGap())

				);

		EditPaneLayout.setVerticalGroup(
				EditPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(EditPaneLayout.createSequentialGroup()
						.addComponent(EditTitleLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(LabelSubtitleLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(LabelTextLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(LabelTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(LabelFontLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(LabelFontComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(EditPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(LabelColorLabel)
								.addComponent(LabelColorBtn))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
								.addGap(3, 3, 3)
								.addComponent(NodeSubtitleLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(EditPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(BorderColorLabel)
										.addComponent(BorderColorBtn))
										.addGap(18, 18, 18)
										.addGroup(EditPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(FillColorLabel)
												.addComponent(FillColorBtn))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
												.addGap(3, 3, 3)
												.addComponent(EdgeSubtitleLabel)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(EditPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(EdgeColorLabel)
														.addComponent(EdgeColorBtn))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(SaveButton))
				);

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(filler1, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(EditPane, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
								.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(EditPane, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0))
				);
	}

	protected void SaveButtonActionPerformed(ActionEvent evt) {
		// Graph/Window pointers
		mainWindowTabbedPane = mainWindow.getMainWindowPane();
		JScrollPane currentPane = (JScrollPane)mainWindowTabbedPane.getSelectedComponent();
		GraphPanel currentPanel = (GraphPanel)currentPane.getViewport().getView();
		Graph currentGraph = currentPanel.mGetGraph();
		int numNodes = currentGraph.mGetNodeList().size();

		Node currentNode;
		Edge currentEdge;

		for(int i = 0; i < currentGraph.vSelectedItems.mGetSelectedEdges().size(); i++) {

			// Get the current edge
			currentEdge = currentGraph.vSelectedItems.mGetSelectedEdges().get(i);

			// If auto Label Edges is on
			if(mainWindow.isAutoLabelEdges()) {
				// Make a Node Label
				if(LabelTextField.getText().isEmpty()) {
					edgeLabel = "Edge" + currentEdge.mGetLabel();
				}
				// Retrieve the node label
				else {
					edgeLabel = LabelTextField.getText();
				}
			}
			// Otherwise...
			else {
				// They need to enter a Node Label
				if(LabelTextField.getText().isEmpty()) {
					edgeLabel = "";
					//JOptionPane.showMessageDialog(AddEdgePanel.this, "Please specify an Edge Label!", "Attention!", JOptionPane.WARNING_MESSAGE);
					//return;
				}
				// Retrieve the node label
				else {
					edgeLabel = LabelTextField.getText();
				}
			}

			// Set the edge's fields
			currentEdge.mSetLabel(edgeLabel);
			currentEdge.mSetLabelColor(labelColor);
			currentEdge.mSetEdgeColor(edgeColor);
			currentEdge.mSetFont(Font.decode((String)LabelFontComboBox.getSelectedItem()));
		}

		for(int i = 0; i < currentGraph.vSelectedItems.mGetSelectedNodes().size(); i++) {

			// Get the current node
			currentNode = currentGraph.vSelectedItems.mGetSelectedNodes().get(i);

			// If auto Label Nodes is on
			if(mainWindow.isAutoLabelNodes()) {
				// Make a Node Label
				if(LabelTextField.getText().isEmpty()) {
					nodeLabel = "Node" + currentNode.mGetId();
				}
				// Retrieve the node label
				else {
					nodeLabel = LabelTextField.getText();
				}
			}
			// Otherwise...
			else {
				// They need to enter a Node Label
				if(LabelTextField.getText().isEmpty()) {
					nodeLabel = null;
					//JOptionPane.showMessageDialog(AddNodePanel.this, "Please specify a Node Label!", "Attention!", JOptionPane.WARNING_MESSAGE);
					//return;
				}
				// Retrieve the node label
				else {
					nodeLabel = LabelTextField.getText();
				}
			}

			// Set the node's new attributes
			currentNode.mSetBorderColor(borderColor);
			currentNode.mSetFillColor(fillColor);
			currentNode.mSetLabelColor(labelColor);
			currentNode.mSetFont(Font.decode((String)LabelFontComboBox.getSelectedItem()));
			currentNode.mSetLabel(nodeLabel);
		}

		// Clear the old list
		AddEdgePanel.SelectEndNodeComboBox.removeAllItems();
		AddEdgePanel.SelectEndNodeComboBox.addItem("");
		AddEdgePanel.SelectStartNodeComboBox.removeAllItems();
		AddEdgePanel.SelectStartNodeComboBox.addItem("");
		// Add to the AddEdge lists
		for(int i = 0; i < numNodes; i++) {
			AddEdgePanel.SelectEndNodeComboBox.addItem(currentGraph.mGetNodeList().get(i).mGetId() + " - " + currentGraph.mGetNodeList().get(i).mGetLabel());
			AddEdgePanel.SelectStartNodeComboBox.addItem(currentGraph.mGetNodeList().get(i).mGetId() + " - " + currentGraph.mGetNodeList().get(i).mGetLabel());
		}

		currentGraph.vSelectedItems.mClearSelectedItems();
		mainWindow.resetSidePane();
		currentPanel.repaint();
	}

	protected void EdgeColorBtnActionPerformed(MouseEvent evt) {
		Color newColor = JColorChooser.showDialog(EditNodeEdgePanel.this, "Choose Edge Color", edgeColor);

		if (newColor != null) {
			edgeColor = newColor;
			EdgeColorBtn.setColor(edgeColor);
		}
	}

	protected void FillColorBtnActionPerformed(MouseEvent evt) {
		Color newColor = JColorChooser.showDialog(EditNodeEdgePanel.this, "Choose Fill Color", fillColor);

		if (newColor != null) {
			fillColor = newColor;
			FillColorBtn.setColor(fillColor);
		}
	}

	protected void BorderColorBtnActionPerformed(MouseEvent evt) {
		Color newColor = JColorChooser.showDialog(EditNodeEdgePanel.this, "Choose Border Color", borderColor);

		if (newColor != null) {
			borderColor = newColor;
			BorderColorBtn.setColor(borderColor);
		}
	}

	protected void LabelColorBtnActionPerformed(MouseEvent evt) {
		Color newColor = JColorChooser.showDialog(EditNodeEdgePanel.this, "Choose Label Color", labelColor);

		if (newColor != null) {
			labelColor = newColor;
			LabelColorBtn.setColor(labelColor);
		}
	}

	protected void updateFields() {
		// Set the values to defaults
		LabelTextField.setText("");
		labelColor = Color.BLACK;
		edgeColor = Color.BLACK;
		fillColor = Color.WHITE;
		borderColor = Color.BLACK;
		LabelFontComboBox.setSelectedItem("Courier");
	}

	// Variables declaration
	private ColorPickerButton BorderColorBtn;
	private JLabel BorderColorLabel;
	private ColorPickerButton EdgeColorBtn;
	private JLabel EdgeColorLabel;
	private JLabel EdgeSubtitleLabel;
	private JLabel EditTitleLabel;
	private ColorPickerButton FillColorBtn;
	private JLabel FillColorLabel;
	private ColorPickerButton LabelColorBtn;
	private JLabel LabelColorLabel;
	private JComboBox LabelFontComboBox;
	private JLabel LabelFontLabel;
	private JLabel LabelSubtitleLabel;
	private JTextField LabelTextField;
	private JLabel LabelTextLabel;
	private JLabel NodeSubtitleLabel;
	private Box.Filler filler1;
	private JButton SaveButton;
	private JPanel EditPane;
	private JSeparator jSeparator1;
	private JSeparator jSeparator2;

	protected JTabbedPane mainWindowTabbedPane;
	protected JScrollPane currentPane;
	protected GraphPanel currentPanel;
	protected Graph currentGraph;
	protected String edgeLabel;
	protected String nodeLabel;

	protected Color labelColor;
	protected Color borderColor;
	protected Color edgeColor;
	protected Color fillColor;
}
