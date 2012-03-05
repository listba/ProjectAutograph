package autograph;
import java.util.ArrayList;
import java.io.Serializable;
import autograph.exception.*;
/**
 * Contained within a graph object - Stores Information about a graphs selected items.
 *
 * @author Ben List
 * @version 1.0
 */
// TODO: This currently impliments Serializable, but we will not want this data saved
public class SelectedItems implements Serializable {
   private ArrayList<Node> vSelectedNodes;
   private ArrayList<Edge> vSelectedEdges;

   public SelectedItems() {
      vSelectedNodes = new ArrayList<Node>();
      vSelectedEdges = new ArrayList<Edge>();
   }

   /**
    * GetSelectedNodes - gets a list of nodes that are currently selected
    *
    * @return - array list of selected nodes
    * @see Node
    */
   public ArrayList<Node> mGetSelectedNodes(){
      return vSelectedNodes;
   }
   
   /**
    * GetSelectedEdges - gets a list of edges that are currently selected
    *
    * @return - array list of selected edges
    * @see Edge
    */
   public ArrayList<Edge> mGetSelectedEdges(){
      return vSelectedEdges;
   }

   /**
    * selectNode - sets vSelectedNodes List to a single node
    *
    * 
    * @see Node
    */
   public void selectNode(Node n) {
      vSelectedNodes = new ArrayList<Node>();
      vSelectedNodes.add(n);
   }

   /**
    * selectEdge - sets vSelectedEdges List to a single edge
    *
    * 
    * @see Edge
    */
   public void selectEdge(Edge e) {
      vSelectedEdges = new ArrayList<Edge>();
      vSelectedEdges.add(e);
   }

}