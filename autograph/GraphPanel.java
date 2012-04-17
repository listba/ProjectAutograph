package autograph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GraphPanel extends JPanel implements MouseListener {
   private Graph graph;
   public GraphPanel (Graph g) {
      this.graph = g;
      this.addMouseListener(this);
      setLayout(null);
   }
   
   public void mSetGraph(Graph g){
      graph = g;
   }
   
   public Graph mGetGraph(){
	   return graph;
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
      ArrayList<Edge> sEdges = graph.vSelectedItems.mGetSelectedEdges();
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
      System.out.println("Mouse Clicked");
      System.out.println("x:"+e.getX()+",y:"+e.getY());
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
            System.out.println("NODE #"+i);
            break;
         }
      }
      for (int i = 0; i < edges.size(); i++){
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
            System.out.println("Edge #"+i);
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
