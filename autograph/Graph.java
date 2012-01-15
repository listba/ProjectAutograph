package autograph;
import java.util.ArrayList;
import java.io.Serializable;
import autograph.exception.*;
/**
 * Graph contains the data and functionality for building graph objects to be represented by Autograph
 *
 * @author Keith Wentzel
 * @version 1.0
 */
public class Graph implements Serializable {
   private ArrayList<Node> vNodeList;
   private ArrayList<Edge> vEdgeList;
   private String vTitle;

   /**
    * ValidateGraph - ensures valid data is passed to the graph constructor
    *
    * @param title - title of the graph
    */
   private void mValidateGraph(String title) throws CannotCreateGraphException{
      if(title == null){
         throw new CannotCreateGraphException("The graph must have a title.");
      }
   }
   
   public Graph(String title){
      try{
         mValidateGraph(title);
         vNodeList = new ArrayList<Node>();
         vEdgeList = new ArrayList<Edge>();
         if(title.isEmpty()){
            vTitle = "New Graph";
         }
         else{
            vTitle = title;
         }
      }
      catch(Exception e){
         //TODO: do something to notify the user of an error
      }
   }
   
   /**
    * GetNodeList - gets a list of nodes for the graph
    *
    * @return - node list for the graph
    * @see Node
    */
   public ArrayList<Node> mGetNodeList(){
      return vNodeList;
   }
   
   /**
    * GetEdgeList - gets an edge list for the graph
    *
    * @return a list of edges for the graph
    * @see Edge
    */
   public ArrayList<Edge> mGetEdgeList(){
      return vEdgeList;
   }
   
   /**
    * GetNodeById - returns the node with the selected Id
    *
    * @param id - id of the node 
    * @return a node with the selected id, or null if the node is not found
    * @see Node
    */
   public Node mGetNodeById(String id){
      //initialize to null. It will be up to the caller to make sure the value is not
      //null when it is returned.
      Node node = null;
      try{
         for(int i = 0; i < vNodeList.size(); i++){
            if(vNodeList.get(i).mGetId().equals(id)){
               node = vNodeList.get(i);
            }
         }
      }
      catch(Exception e){
         //This will theoretically only happen if vNodeList.get(i) is null.
         //TODO: report failure to user.
      }
      return node;
   }
   
   /**
    * DeleteNodeById - removes a node with the selected id from the node list for the graph
    *
    * @param id - id of the Node
    * @see Node
    */
   public void mDeleteNodeById(String id){
      try{
         for(int i = 0; i < vNodeList.size(); i++){
            if(vNodeList.get(i).mGetId().equals(id)){
               //remove will remove the node and shift left, so it will not create
               //a null entry in the list.
               vNodeList.remove(i);
            }
         }
      }
      catch(Exception e){
         //this will theoretically only happen if vNodeList.get(i) is null
         //TODO: report failure to user
      }
   }
   
   /**
    * GetEdgeById - returns an edge with the selected id in the graph
    *
    * @param id - id of the Edge
    * @return an edge with the selected id, or null if the node is not found
    * @see Edge
    */
   public Edge mGetEdgeById(String id){
      Edge edge = null;
      try{
         for(int i = 0; i < vEdgeList.size(); i++){
            if(vEdgeList.get(i).mGetId().equals(id)){
               edge = vEdgeList.get(i);
            }
         }
      }
      catch(Exception e){
         //This will theoretically only happen if vNodeList.get(i) is null.
         //TODO: report failure to user.
      }
      return edge;
   }
   
   /**
    * DeleteEdgeById - removes the edge with the selected id from the graph
    *
    * @param id of the edge
    * @see Edge
    */
   public void mDeleteEdgeById(String id){
      try{
         for(int i = 0; i < vEdgeList.size(); i++){
            if(vEdgeList.get(i).mGetId().equals(id)){
               //remove will remove the node and shift left, so it will not create
               //a null entry in the list.
               vEdgeList.remove(i);
            }
         }
      }
      catch(Exception e){
         //this will theoretically only happen if vNodeList.get(i) is null
         //TODO: report failure to user
      }
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
