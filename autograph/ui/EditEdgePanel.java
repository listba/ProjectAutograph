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
		GraphicsEnvironment ge;
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		String[] fontNames = ge.getAvailableFontFamilyNames();

		setBackground(new java.awt.Color(255, 255, 255));
		setMinimumSize(new java.awt.Dimension(200, 512));
		setPreferredSize(new java.awt.Dimension(200, 512));

		labelColor = Color.BLACK;
		edgeColor = Color.BLACK;

		// Edit Edge Panel
		EditEdgePanel = new JPanel();
		EditEdgePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		EditEdgePanel.setPreferredSize(new java.awt.Dimension(200, 512));

		// Edit Edge Title Label
		EditEdgeTitleLabel = new JLabel();
		EditEdgeTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); 
		EditEdgeTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		EditEdgeTitleLabel.setText("Edit Edge");

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
		LabelColorBtn = new JButton();
		LabelColorBtn.setText("Select Color");
		LabelColorBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
		LabelColorBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				LabelColorBtnActionPerformed(evt);
			}
		});

		// Edge Subtitle Label
		EdgeSubtitleLabel = new JLabel();
		EdgeSubtitleLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); 
		EdgeSubtitleLabel.setText("Edge");

		// Edge Design Label
		EdgeDesignLabel = new JLabel();
		EdgeDesignLabel.setText("Edge Design");

		// Direction Label
		DirectionLabel = new JLabel();
		DirectionLabel.setText("Direction");

		// Edge Color Label
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

		// Edge Design Combo Box
		EdgeDesignComboBox = new JComboBox();
		EdgeDesignComboBox.setModel(new DefaultComboBoxModel(new String[] {"Solid", "Dotted", "Dashed"}));

		// Direction Combo Box
		DirectionComboBox = new JComboBox();
		DirectionComboBox.setModel(new DefaultComboBoxModel(new String[] {"NoDirection", "StartDirection", "EndDirection", "DoubleDirection"}));

		// Panel Separator
		panelSeperator = new JSeparator();

		// Save Button
		SaveButton = new JButton();
		SaveButton.setText("Save Changes");
		SaveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				SaveButtonActionPerformed(evt);
			}
		});

		// Panel Filler
		panelFiller = new Box.Filler(new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 0), new java.awt.Dimension(200, 32767));

		// Layout
		javax.swing.GroupLayout EditEdgePanelLayout = new GroupLayout(EditEdgePanel);
		EditEdgePanel.setLayout(EditEdgePanelLayout);
		EditEdgePanelLayout.setHorizontalGroup(
				EditEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(EditEdgePanelLayout.createSequentialGroup()
						.addGroup(EditEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(LabelSubtitleLabel)
								.addComponent(EdgeSubtitleLabel))
								.addGap(0, 0, Short.MAX_VALUE))
								.addGroup(EditEdgePanelLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(EditEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(EditEdgeTitleLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(LabelTextField, 0, GroupLayout.DEFAULT_SIZE, 190)
												.addComponent(LabelFontComboBox, 0, GroupLayout.DEFAULT_SIZE, 190)
												.addComponent(panelSeperator, 0, GroupLayout.DEFAULT_SIZE, 186)
												.addGroup(javax.swing.GroupLayout.Alignment.LEADING, EditEdgePanelLayout.createSequentialGroup()
														.addGap(0, 0, Short.MAX_VALUE)
														.addComponent(SaveButton))
														.addGroup(EditEdgePanelLayout.createSequentialGroup()
																.addGroup(EditEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																		.addGroup(EditEdgePanelLayout.createSequentialGroup()
																				.addGroup(EditEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(LabelTextLabel)
																						.addComponent(LabelFontLabel)
																						.addComponent(DirectionLabel)
																						.addComponent(EdgeColorLabel))
																						.addGap(10, 10, 10)
																						.addGroup(EditEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addComponent(EdgeColorBtn, 0, GroupLayout.DEFAULT_SIZE, 120)
																								.addComponent(LabelColorBtn, 0, GroupLayout.DEFAULT_SIZE, 120)
																								.addComponent(EdgeDesignComboBox, 0, GroupLayout.DEFAULT_SIZE, 120)
																								.addComponent(DirectionComboBox, 0, GroupLayout.DEFAULT_SIZE, 120)))
																								.addGroup(EditEdgePanelLayout.createSequentialGroup()
																										.addGroup(EditEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(EdgeDesignLabel)
																												.addComponent(LabelColorLabel))
																												.addGap(18, 18, 18)
																												.addGroup(EditEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))))
																												.addGap(0, 0, Short.MAX_VALUE)))
																												.addContainerGap())
				);
		EditEdgePanelLayout.setVerticalGroup(
				EditEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(EditEdgePanelLayout.createSequentialGroup()
						.addComponent(EditEdgeTitleLabel)
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
						.addGroup(EditEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(LabelColorLabel)
								.addComponent(LabelColorBtn))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(panelSeperator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
								.addGap(3, 3, 3)
								.addComponent(EdgeSubtitleLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(EditEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(EdgeDesignLabel)
										.addComponent(EdgeDesignComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(EditEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(DirectionLabel)
												.addComponent(DirectionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(EditEdgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(EdgeColorLabel)
														.addComponent(EdgeColorBtn))
														.addGap(18, 18, 18)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(SaveButton)
														.addContainerGap())
				);

		javax.swing.GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(panelFiller, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(EditEdgePanel, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
								.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(EditEdgePanel, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(panelFiller, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0))
				);
	}

	// The Save Button is clicked
	protected void SaveButtonActionPerformed(ActionEvent evt) {

		// Graph/Window pointers
		mainWindowTabbedPane = mainWindow.getMainWindowPane();
		JScrollPane currentPane = (JScrollPane)mainWindowTabbedPane.getSelectedComponent();
		GraphPanel currentPanel = (GraphPanel)currentPane.getViewport().getView();
		Graph currentGraph = currentPanel.mGetGraph();

		// If more than one edge is selected
		if(currentGraph.vSelectedItems.mGetSelectedEdges().size() > 1) {
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
				currentEdge.mSetDirection((String)DirectionComboBox.getSelectedItem());
				currentEdge.mSetEdgeStyle((String)EdgeDesignComboBox.getSelectedItem());
			}
		}
		// Otherwise 1 edge is selected...
		else {
			
			// Get the current edge
			Edge currentEdge = currentGraph.vSelectedItems.mGetSelectedEdges().get(0);

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
			currentEdge.mSetDirection((String)DirectionComboBox.getSelectedItem());
			currentEdge.mSetEdgeStyle((String)EdgeDesignComboBox.getSelectedItem());
		}

		currentGraph.vSelectedItems.mClearSelectedItems();
		mainWindow.resetSidePane();
		currentPanel.repaint();	
	}

	// If the Edge Color Button is clicked
	protected void EdgeColorBtnActionPerformed(MouseEvent evt) {
		Color newColor = JColorChooser.showDialog(EditEdgePanel.this, "Choose Edge Color", edgeColor);

		if (newColor != null) {
			edgeColor = newColor;
		}
	}

	// If the Label Color Button is clicked
	protected void LabelColorBtnActionPerformed(MouseEvent evt) {
		Color newColor = JColorChooser.showDialog(EditEdgePanel.this, "Choose Label Color", labelColor);

		if (newColor != null) {
			labelColor = newColor;
		}
	}

	protected void updateFields() {
		// Graph/Window pointers
		mainWindowTabbedPane = mainWindow.getMainWindowPane();
		JScrollPane currentPane = (JScrollPane)mainWindowTabbedPane.getSelectedComponent();
		GraphPanel currentPanel = (GraphPanel)currentPane.getViewport().getView();
		Graph currentGraph = currentPanel.mGetGraph();

		// If there is more than 1 edge selected
		if(currentGraph.vSelectedItems.mGetSelectedEdges().size() > 1) {

			// Set the values to defaults, as multiple edges are selected
			LabelTextField.setText("");
			labelColor = Color.BLACK;
			edgeColor = Color.BLACK;
			LabelFontComboBox.setSelectedItem("Courier");
			DirectionComboBox.setSelectedItem("NoDirection");
			EdgeDesignComboBox.setSelectedItem("Solid");
		}
		// Otherwise... (only one edge is selected)
		else {
			Edge selectedEdge = currentGraph.vSelectedItems.mGetSelectedEdges().get(0); 
			LabelTextField.setText(selectedEdge.mGetLabel());
			labelColor = selectedEdge.mGetLabelColor();
			edgeColor = selectedEdge.mGetEdgeColor();
			LabelFontComboBox.setSelectedItem(selectedEdge.mGetFont().getFontName());
			DirectionComboBox.setSelectedItem(badCodeStuff(selectedEdge.mGetDirection().toString()));
			EdgeDesignComboBox.setSelectedItem(makeRealWord(selectedEdge.mGetEdgeStyle().toString()));
		}
	}

	private String makeRealWord(String fakeWord) {
		String realWord = fakeWord.substring(0,1).toUpperCase();
		realWord += fakeWord.substring(1).toLowerCase();
		return realWord;
	}
	private String badCodeStuff(String derpString) {
		if (derpString.equals("NODIRECTION")) {
			return "NoDirection";
		} else if (derpString.equals("STARTDIRECTION")) {
			return "StartDirection";
		} else if (derpString.equals("ENDDIRECTION")) {
			return "EndDirection";
		} else {
			return "DoubleDirection";
		}
	}
	// Variables declaration
	private JPanel EditEdgePanel;
	private JLabel EditEdgeTitleLabel;
	private JButton EdgeColorBtn;
	private JLabel EdgeColorLabel;
	private JComboBox EdgeDesignComboBox;
	private JLabel EdgeDesignLabel;
	private JLabel EdgeSubtitleLabel;
	private JComboBox DirectionComboBox;
	private JLabel DirectionLabel;
	private JButton LabelColorBtn;
	private JLabel LabelColorLabel;
	private JComboBox LabelFontComboBox;
	private JLabel LabelFontLabel;
	private JLabel LabelSubtitleLabel;
	private JTextField LabelTextField;
	private JLabel LabelTextLabel;
	private JButton SaveButton;
	private Box.Filler panelFiller;
	private JSeparator panelSeperator;

	protected JTabbedPane mainWindowTabbedPane;
	protected JScrollPane currentPane;
	protected GraphPanel currentPanel;
	protected Graph currentGraph;
	protected String edgeLabel;

	protected Color labelColor;
	protected Color edgeColor;
}
