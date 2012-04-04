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
		String filePath = "Graph.xml";
		Graph graph = GraphHelper.mImportGraphFromXML(filePath);
		GraphPanel panel = new GraphPanel(graph);
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
		
		//GraphHelper.mSavePNG(panel);
		try{
		   GraphHelper.mExportGraphToGML(graph, "graphGML.txt", null);
		}
		catch(Exception e){
		   
		}

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
   /**
    * paint - overides the default paint method for panels
    *         so we can define our own drawing method
    *         this function draws all nodes and edges, then all selected items
    */
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
		   	GraphHelper.mDrawSelectedNode(g, sNodes.get(i));
		   }
		}
		catch(Exception e) {
			e.getMessage();
			e.getCause();
		}
	}

	/** 
    * mouseClicked - Find what element was clicked, if any and register
    * 
    * @param e    Mouse event that stores information about mouse click
	 */
	public void mouseClicked(MouseEvent e) {
    	System.out.println("Mouse Clicked");
    	System.out.println("x:"+e.getX()+",y:"+e.getY());
    	ArrayList<Node> nodes = graph.mGetNodeList();
      boolean itemSelected = false;
    	for (int i = 0; i < nodes.size(); i++){
    		if (nodes.get(i).mContains(e.getX(), e.getY()))
    		{
            if (e.isControlDown()) {
               graph.vSelectedItems.mAppendNode(nodes.get(i));
            } else if (e.isShiftDown()) {
               graph.vSelectedItems.mSelectAllNodes(nodes);
            } else {
    			   graph.vSelectedItems.mSelectNode(nodes.get(i));
            }
            itemSelected = true;
    			System.out.println("NODE #"+i);
    			break;
    		}
    	}
      // TODO: Select Edges, I need to figure out a good way to figure out
      /// the bounding box arround edges
      if (!itemSelected) {
         graph.vSelectedItems.mClearSelectedItems();
      }
    	this.repaint();
   } 
   // Other Mouse Events, since we are implementing an interface we 
   // must include implementations for these even if we don't use it
   public void mousePressed(MouseEvent e) {
   }

   public void mouseReleased(MouseEvent e) {
   }

   public void mouseEntered(MouseEvent e) {
   }

   public void mouseExited(MouseEvent e) {
   }
}
