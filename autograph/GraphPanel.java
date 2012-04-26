package autograph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import autograph.ui.mainWindow;

public class GraphPanel extends JPanel implements MouseListener, MouseMotionListener {
   private Graph graph;
   private String filePath;
   private Node nodeToDrag;
   private boolean inDrag = false;
   public GraphPanel (Graph g) {
      this.graph = g;
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
      this.filePath = "";
      setLayout(null);
   }
   
   public void mSetGraph(Graph g){
      graph = g;
   }
   
   public Graph mGetGraph(){
	   return graph;
   }
   
   public void mSetFilePath(String path){
      filePath = path;
   }
   
   public String mGetFilePath(){
      return filePath;
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
      g.fillRect(0, 0, getWidth(), getHeight());
      ArrayList<Node> nodes = graph.mGetNodeList();
      ArrayList<Edge> edges = graph.mGetEdgeList();
      ArrayList<Node> sNodes = graph.vSelectedItems.mGetSelectedNodes();
      ArrayList<Edge> sEdges = graph.vSelectedItems.mGetSelectedEdges();
      try{
         // Draw the edges
         for(int i = 0; i < edges.size(); i++) {
            GraphHelper.mDrawEdge(g, edges.get(i));
         }
         // Draw the nodes
         for(int i = 0; i < nodes.size(); i++) {
            GraphHelper.mDrawNode(g, nodes.get(i));
         }
         for(int i = 0; i < sNodes.size(); i++) {
            GraphHelper.mDrawSelectedNode(g, sNodes.get(i));
         }
         for(int i = 0; i < sEdges.size(); i++) {
            GraphHelper.mDrawSelectedEdge(g, sEdges.get(i));
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
      ArrayList<Node> nodes = graph.mGetNodeList();
      ArrayList<Edge> edges = graph.mGetEdgeList();
      boolean itemSelected = false;
      for (int i = 0; i < nodes.size(); i++){
         if (nodes.get(i).mContains(e.getX(), e.getY()))
         {
            if (e.isControlDown()) {
               graph.vSelectedItems.mAppendNode(nodes.get(i));
            } else if (e.isShiftDown()) {
               graph.vSelectedItems.mSelectAllNodes(nodes);
            } else {
               graph.vSelectedItems.mClearSelectedItems();
               graph.vSelectedItems.mSelectNode(nodes.get(i));
            }
            itemSelected = true;
            break;
         }
      }
      for (int i = 0; i < edges.size(); i++) {
         if (edges.get(i).mContains(e.getX(), e.getY())) {
            if (e.isControlDown()) {
               graph.vSelectedItems.mAppendEdge(edges.get(i));
            } else if (e.isShiftDown()) {
               graph.vSelectedItems.mSelectAllEdges(edges);
            } else {
               graph.vSelectedItems.mClearSelectedItems();
               graph.vSelectedItems.mSelectEdge(edges.get(i));
            }
            itemSelected = true;
            break;
         }
      }
      if (!itemSelected) {
         graph.vSelectedItems.mClearSelectedItems();
         mainWindow.setSidePanel("Deselect");
      }
      // Change the edit panel accordingly
      else {
    	  // If both edges and nodes are selected...
    	  if(!(graph.vSelectedItems.mGetSelectedNodes().isEmpty()) && !(graph.vSelectedItems.mGetSelectedEdges().isEmpty())) {
    		  mainWindow.setSidePanel("Both");
    	  }
    	  // If only nodes are selected
    	  else if(!(graph.vSelectedItems.mGetSelectedNodes().isEmpty())) {
    		  mainWindow.setSidePanel("Node");
    	  }
    	  // If only edges are selected
    	  else if(!(graph.vSelectedItems.mGetSelectedEdges().isEmpty())) {
    		  mainWindow.setSidePanel("Edge");
    	  }
      }
      this.repaint();
   } 
   // Other Mouse Events, since we are implementing an interface we 
   // must include implementations for these even if we don't use it
   public void mousePressed(MouseEvent e) {
      ArrayList<Node> nodes = graph.mGetNodeList();
       for (int i = 0; i < nodes.size(); i++){
         if (nodes.get(i).mContains(e.getX(), e.getY()))
         {
            nodeToDrag = nodes.get(i);
            inDrag = true;
            break;
         }
      }
   }

   public void mouseReleased(MouseEvent e) {
      inDrag = false;
      nodeToDrag = null;
   }

   public void mouseDragged(MouseEvent e) {
      if(inDrag && nodeToDrag != null)
      {
         nodeToDrag.mSetCenterLocation(e.getX(), e.getY());
         this.repaint();
      }
   }

   public void mouseEntered(MouseEvent e) {
   }

   public void mouseExited(MouseEvent e) {
   }

   public void mouseMoved(MouseEvent e) {

   }
}
