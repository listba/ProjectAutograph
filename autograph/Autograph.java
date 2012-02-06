package autograph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import autograph.ui.mainWindow;

public class Autograph extends JFrame {

	public Autograph(String title) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      String filePath = "Graph.xml";
      final Graph graph = GraphHelper.mImportGraphFromXML(filePath);
		this.setTitle(title + " - " + graph.mGetTitle());
		JPanel panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(Color.white);
				g.fillRect(0, 0, getWidth(), getHeight());
				
				//Graph graph = new Graph("TestGraph");
				//Node testNode1 = new Node("node1", "Test Label", Node.NodeShape.SQUARE, Node.NodeStyle.SOLID);
				//Node testNode2 = new Node("node2", "Test Label 2", Node.NodeShape.CIRCLE, Node.NodeStyle.SOLID);
				//Edge edge1 = new Edge("edge1", "Edge Label", testNode1, testNode2, Edge.Direction.NODIRECTION, Edge.EdgeStyle.SOLID);

				try{
					//graph.mAddNode(testNode1);
					//graph.mAddNode(testNode2);
					//graph.mAddEdge(edge1);
					GraphHelper.mDrawGraph(graph,g);
				}
				catch(Exception e) {
					e.getMessage();
					e.getCause();
				}
			}
		};
		//	mainWindow win =  new mainWindow();

		
		this.add(panel);
		this.setPreferredSize(new Dimension(800, 600));
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
