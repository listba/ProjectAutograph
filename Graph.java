
package autograph;
import java.util.ArrayList;
import java.io.Serializable;
/**
 * Graph contains the data and functionality for building graph objects to be represented by Autograph
 *
 * @author Keith Wentzel
 * @version 1.0
 */
public class Graph implements Serializable{
   private ArrayList<Node> vNodeList;
   private ArrayList<Edge> vEdgeList;

   public Graph(){
      vNodeList = new ArrayList<Node>();
      vEdgeList = new ArrayList<Edge>();
   }
   /**
    * AddEdge - adds an edge to the edge list for the graph
    *
    * @param edge - edge to be added to the edge list
    * @return - ErrorHandler.NOERROR if successful, other error code if not successful
    * @see Edge
    */
   public int mAddEdge(Edge edge){
      int retValue = ErrorHandler.CANNOTADDEDGE;
      if(vEdgeList.add(edge)){
         retValue = ErrorHandler.NOERROR;
      }
      return retValue;
   }

   /**
    * RemoveEdge - removes an edge from the edge list for the graph
    *
    * @param edge - edge to be removed from the edge list
    * @return - ErrorHandler.NOERROR if successful, other error code if not successful
    * @see Edge
    */
   public int mRemoveEdge(Edge edge){
      int retValue = ErrorHandler.CANNOTREMOVEEDGE;
      if(vEdgeList.remove(edge)){
         retValue = ErrorHandler.NOERROR;
      }
      return retValue;
   }

   /**
    * AddNode - adds a node to the edge list
    *
    * @param node - node to add to the node list for the graph
    * @return - ErrorHandler.NOERROR if successful, other error code if not successful
    * @see Node
    */
   public int mAddNode(Node node){
      int retValue = ErrorHandler.CANNOTADDNODE;
      if(vNodeList.add(node)){
         retValue = ErrorHandler.NOERROR;
      }
      return retValue;
   }

   /**
    * RemoveNode - removes a node from the node list
    *
    * @param node - node to remove from the node list for the graph
    * @return - ErrorHandler.NOERROR if successful, other error code if not successful
    * @see Node
    */
   public int mRemoveNode(Node node){
      int retValue = ErrorHandler.CANNOTREMOVENODE;
      if(vEdgeList.remove(node)){
         retValue = ErrorHandler.NOERROR;
      }
      return retValue;
   }
}
