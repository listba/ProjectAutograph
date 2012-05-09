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
// TODO: This currently implements Serializable, but we will not want this data saved
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
    * @return     array list of selected edges
    * @see Edge
    */
   public ArrayList<Edge> mGetSelectedEdges(){
      return vSelectedEdges;
   }

   /**
    * mSelectNode - sets vSelectedNodes List to a single node
    *              If node is already selected deselect it
    *
    * @param      n  node to select
    * @see Node
    */
   public void mSelectNode(Node n) {
      if (vSelectedNodes.contains(n)) {
         mClearSelectedNodes();
      } else {
         mClearSelectedNodes();
         vSelectedNodes.add(n);
      }
   }

   /**
    * mSelectEdge - sets vSelectedEdges List to a single edge
    *              If edge is already selected deselect it
    *
    * @param      e  edge to select
    * @see Edge
    */
   public void mSelectEdge(Edge e) {
      if (vSelectedEdges.contains(e)) {
         mClearSelectedEdges();
      } else {
         mClearSelectedEdges();
         vSelectedEdges.add(e);
      }
   }

   /**
    * mAppendNode - appends node to selected node list
    *              If node is already selected deselect it
    *
    * @param      n  node to select
    * @see Node
    */
   public void mAppendNode(Node n) {
      if (vSelectedNodes.contains(n)) {
         mRemoveNode(n);
      } else {
         vSelectedNodes.add(n);
      }
   }

   /**
    * mAppendEdge - appends edge to selected edge list
    *              If edge is already selected deselect it
    *
    * @param      e  edge to select
    * @see Edge
    */
   public void mAppendEdge(Edge e) {
      if (vSelectedEdges.contains(e)) {
         mRemoveEdge(e);
      } else {
         vSelectedEdges.add(e);
      }
   }

   /**
    * mSelectAllNodes - selects all nodes in array list
    *
    * @param      nodes  array list of nodes to select
    * @see Node
    */
   public void mSelectAllNodes(ArrayList<Node> nodes) {
      mClearSelectedNodes();
      for (int i = 0; i < nodes.size(); i++) {
         vSelectedNodes.add(nodes.get(i));
      }
   }

   /**
    * mSelectAllEdges - selects all edges in array list
    *
    * @param      edges  array list of edges to select
    * @see Edge
    */
   public void mSelectAllEdges(ArrayList<Edge> edges) {
      mClearSelectedEdges();
      for (int i = 0; i < edges.size(); i++) {
         vSelectedEdges.add(edges.get(i));
      }
   }

   /**
    * mRemoveNode - removes single node from selected nodes list
    *
    * @param      n  node to deselect
    * @see Node
    */
   public void mRemoveNode(Node n) {
      vSelectedNodes.remove(n);
   }

   /**
    * mRemoveEdge - removes single edge from selected edge list
    *
    * @param      e  edge to deselect
    * @see Edge
    */
   public void mRemoveEdge(Edge e) {
      vSelectedEdges.remove(e);
   }

   /**
    * mClearSelectedItems - clears the selected nodes and selected edges lists
    *
    */
   public void mClearSelectedItems() {
      mClearSelectedNodes();
      mClearSelectedEdges();
   }

   /**
    * mClearSelectedNodes - clears selected nodes list
    *
    */
   public void mClearSelectedNodes() {
      vSelectedNodes.clear();
   }

   /**
    * mCLearSelectedEdges - clears selected edges list
    *
    */
   public void mClearSelectedEdges() {
      vSelectedEdges.clear();
   }
}