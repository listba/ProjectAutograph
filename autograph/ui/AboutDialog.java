package autograph.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AboutDialog extends JDialog {
	
	public AboutDialog(JFrame parent, String title, String message) {
		super(parent, title, true);
		if (parent != null) {
			Dimension parentSize = parent.getSize(); 
			Point p = parent.getLocation(); 
			setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
		}
		
		JPanel messagePane = new JPanel();
		messagePane.add(new JTextArea(message));
		String path = "resources/Autograph.jpg";
		JPanel picturePane = new JPanel(new BorderLayout());
		JLabel label = new JLabel(new ImageIcon(path));
		label.setHorizontalAlignment(JLabel.CENTER);
		picturePane.add(label);  // default center section
		getContentPane().add(picturePane);
		getContentPane().add(messagePane, BorderLayout.SOUTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack(); 
		setVisible(true);
	}
}