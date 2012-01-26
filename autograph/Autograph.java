package autograph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import autograph.ui.mainWindow;

public class Autograph extends mainWindow {

	public Autograph(String title) {
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*String filePath = "Graph.xml";
		final Graph graph = GraphHelper.mImportGraphFromXML(filePath);
		JPanel panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				 g.setColor(Color.GRAY);
				g.fillRect(0, 0, getWidth(), getHeight());
				GraphHelper.mDrawGraph(graph,g);
			}
		};*/

		mainWindow win =  new mainWindow();
		
		this.pack();
		this.setLocationRelativeTo(null);

	}

	public static void main(String[] args) {
		

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Autograph autograph = new Autograph(
						"Autograph");
				autograph.setVisible(true);
			}

		});
	}
}
