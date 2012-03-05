package autograph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.util.ArrayList;
import autograph.ui.mainWindow;

public class Autograph extends JFrame {

	public Autograph(String title) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create New Graph Panel
		// And Load in graph from XML file
		String filePath = "NewGraph.xml";
		GraphPanel panel = new GraphPanel(GraphHelper.mImportGraphFromXML(filePath));
		// Write Graph to XML file
		/*
		String writeTo = "NewGraph.xml";
		GraphHelper.mExportGraphToXML(graph, writeTo);
		*/
		// Approximate size of Graph
		int imageWidth = GraphHelper.mGetPreferredImageWidth(panel.graph);
		this.setPreferredSize(new Dimension(imageWidth, imageWidth));
		panel.setPreferredSize(new Dimension(imageWidth, imageWidth));
		this.setTitle(title + " - " + panel.graph.mGetTitle());
		panel.repaint();
		
		
		
		this.add(panel);

		//this.setPreferredSize(new Dimension(800, 600));
		this.pack();
		this.setLocationRelativeTo(null);

		// Draw Initial Graph
		GraphHelper.mDrawForceDirectedGraph(panel);
		panel.repaint();

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

class GraphPanel extends JPanel implements MouseListener {
	final public Graph graph;
	GraphPanel (Graph g) {
		this.graph = g;
		this.addMouseListener(this);
		setLayout(null);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.white);
		System.out.println(getWidth());
		g.fillRect(0, 0, getWidth(), getHeight());
		ArrayList<Node> nodes = graph.mGetNodeList();
		ArrayList<Edge> edges = graph.mGetEdgeList();
		ArrayList<Node> sNodes = graph.vSelectedItems.mGetSelectedNodes();
		try{
			//GraphHelper.mDrawForceDirectedGraph(graph, g, this);
			// Draw the nodes
		   for(int i = 0; i < nodes.size(); i++) {
			   GraphHelper.mDrawNode(g, nodes.get(i));
		   }
		   // Draw the edges
		   for(int i = 0; i < edges.size(); i++) {
			   GraphHelper.mDrawEdge(g, edges.get(i));
		   }
		   for(int i = 0; i < sNodes.size(); i++) {
		   	GraphHelper.mDrawSelectedNode(g, nodes.get(i));
		   }
		}
		catch(Exception e) {
			e.getMessage();
			e.getCause();
		}
	}

	// Find what element was clicked, if any and register
	/* TODO: we can use the isShiftDown(), isControlDown(), isAltDown()
	 *       To check key states so we can register multiple objects
	 *       as being selected.
	*/
	public void mouseClicked(MouseEvent e) {
    	System.out.println("Mouse Clicked");
    	System.out.println("x:"+e.getX()+",y:"+e.getY());
    	ArrayList<Node> nodes = graph.mGetNodeList();
      ArrayList<Node> wat = graph.vSelectedItems.mGetSelectedNodes();
    	for (int i = 0; i < nodes.size(); i++){
    		if (nodes.get(i).mContains(e.getX(), e.getY()))
    		{
            Node n = nodes.get(i);
    			graph.vSelectedItems.mSelectANode(n);
    			System.out.println("NODE #"+i);
    			break;
    		}
    	}
    	this.repaint();
    }

   public void mousePressed(MouseEvent e) {
   }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
