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
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//String filePath = "Graph.xml";
		//final Graph graph = GraphHelper.mImportGraphFromXML(filePath);

		JPanel panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(Color.white);
				g.fillRect(0, 0, getWidth(), getHeight());

				//I am guessing (without looking at the code) the xml import stuff isn't newing node/edge objects because I am getting null pointer exceptions 
				//if I run the code using the xml import. So I'll just build a graph with the attributes I want to test against.
				Graph graph = new Graph("TestGraph");
				Node testNode1 = new Node("node1", "Test Label", Node.NodeShape.CIRCLE, Node.NodeStyle.SOLID);
				Node testNode2 = new Node("node2", "Test Label 2", Node.NodeShape.CIRCLE, Node.NodeStyle.SOLID);
				Edge edge1 = new Edge("edge1", "Edge Label", testNode1, testNode2, Edge.Direction.NODIRECTION, Edge.EdgeStyle.SOLID);

				try{
					graph.mAddNode(testNode1);
					graph.mAddNode(testNode2);
					graph.mAddEdge(edge1);
					GraphHelper.mDrawGraph(graph,g);
				}
				catch(Exception e) {
					e.getMessage();
					e.getCause();
				}
			}
		};
		//	mainWindow win =  new mainWindow();

		this.setPreferredSize(new Dimension(800, 600));
		this.add(panel);
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
