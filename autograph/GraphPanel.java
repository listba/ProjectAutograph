package autograph;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import autograph.ui.mainWindow;

public class GraphPanel extends JPanel implements MouseListener, MouseMotionListener {
   private Graph graph;
   private String filePath;
   private ArrayList<Node> nodesToDrag;
   private boolean inDrag = false;
   private boolean drawBox = false;
   private int startX;
   private int startY;
   private int endX;
   private int endY;
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
      int maxWidth = 0;
      int maxHeight= 0;
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
            Node n = nodes.get(i);
            GraphHelper.mDrawNode(g, n);
            int width = n.mGetCenterX()+n.mGetWidth();
            int height = n.mGetCenterY()+n.mGetHeight();
            if (width > maxWidth) {maxWidth = width;}
            if (height > maxHeight) {maxHeight = height;}
            this.setPreferredSize(new Dimension(maxWidth,maxHeight));
            this.revalidate();
         }
         for(int i = 0; i < sNodes.size(); i++) {
            GraphHelper.mDrawSelectedNode(g, sNodes.get(i));
         }
         for(int i = 0; i < sEdges.size(); i++) {
            GraphHelper.mDrawSelectedEdge(g, sEdges.get(i));
         }
         if (drawBox)
         {
            Graphics2D g2d = (Graphics2D)g.create();
            int x1 = startX;
            int x2 = endX;
            int y1 = startY;
            int y2 = endY;
            int temp;
            if(x1 > x2)
            {
               temp = x1;
               x1 = x2;
               x2 = temp;
            }
            if(y1 > y2)
            {
               temp = y1;
               y1 = y2;
               y2 = temp;
            }
            g2d.setPaint(new Color(0, 0, 1, 0.3f));
            g2d.fillRect(x1, y1, x2-x1, y2-y1);
            g2d.setPaint(Color.blue);
            g2d.drawRect(x1, y1, x2-x1, y2-y1);
            g2d.dispose();
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
               System.out.println("wut");
               //graph.vSelectedItems.mAppendNode(nodes.get(i));
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
      inDrag = true;
      startX = e.getX();
      startY = e.getY();
      for (int i = 0; i < nodes.size(); i++){
         if (nodes.get(i).mContains(e.getX(), e.getY()))
         {
            if (e.isControlDown()) {
               System.out.println("wat");
               graph.vSelectedItems.mAppendNode(nodes.get(i));
            } else if (e.isShiftDown()) {
               graph.vSelectedItems.mSelectAllNodes(nodes);
            } else {
               graph.vSelectedItems.mClearSelectedItems();
               graph.vSelectedItems.mSelectNode(nodes.get(i));
            }
            nodesToDrag = graph.vSelectedItems.mGetSelectedNodes();
            System.out.println(getPreferredSize());
            this.repaint();
            break;
         }
      }
   }

   public void mouseReleased(MouseEvent e) {
      inDrag = false;
      drawBox = false;
      nodesToDrag = null;
      graph.vSelectedItems.mClearSelectedItems();
      // TODO: Add Nodes and Edges to selection Box
      this.repaint();
      
   }

   public void mouseDragged(MouseEvent e) {
      if(inDrag && nodesToDrag != null)
      {
         int newX = e.getX()-startX;
         int newY = e.getY()-startY;
         int maxWidth = 0;
         int maxHeight = 0;
         boolean outsideXViewport = false;
         boolean outsideYViewport = false;
         for (int i = 0; i< nodesToDrag.size(); i++)
         {
            Node nodeToDrag = nodesToDrag.get(i);
            int x = newX + nodeToDrag.mGetCenterX();
            int y = newY + nodeToDrag.mGetCenterY();
            if (x <= 0) {outsideXViewport = true;} 
            if (y <= 0) {outsideYViewport = true;}

            if (outsideYViewport && outsideXViewport) {break;}
         }
         if (!outsideXViewport || !outsideYViewport)
         {
            for (int i = 0; i< nodesToDrag.size(); i++)
            {
               Node nodeToDrag = nodesToDrag.get(i);
               int x = newX + nodeToDrag.mGetCenterX();
               int y = newY + nodeToDrag.mGetCenterY();
               if(outsideXViewport) {nodeToDrag.mSetCenterLocation(nodeToDrag.mGetCenterX(), y);}
               else if (outsideYViewport) {nodeToDrag.mSetCenterLocation(x, nodeToDrag.mGetCenterY());}
               else {nodeToDrag.mSetCenterLocation(x, y);}
            }

            if(outsideXViewport){startY = e.getY();}
            if(outsideYViewport){startX = e.getX();}
            else 
            {
               startX = e.getX();
               startY = e.getY();
            }
            this.repaint();
         }
      } else if (inDrag)
      {
         endX = e.getX();
         endY = e.getY();
         drawBox = true;
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
