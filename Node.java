package autograph;

import java.util.ArrayList;
import java.io.Serializable;
/**
 * Node contains all data for node objects of a Graph.
 *
 * @author Keith Wentzel
 * @version 1.0
 */
public class Node implements Serializable {

   //enumerations
   /**
    * NodeShape - the various shapes for the border of the node
    */
   public enum NodeShape{
      SQUARE,
      CIRCLE,
      TRIANGLE,
      RECTANGLE,
      OVAL
   };
   /**
    * NodeStyle - the various styles for the border of the node
    */
   public enum NodeStyle{
     SOLID,
     DOTTED,
     DASHED,
     DOUBLE
   }

   //Member Variables
   private String vName;
   private String  vLabel;
   private NodeShape vShape;
   private NodeStyle vStyle;
   private ArrayList vEdges;

   /**
    * Node Constructor
    *
    * Set up the node object based on  user input
    *
    * @param name variable name to use for accessing the node
    * @param label label to be displayed in the node
    * @param shape shape of the node
    * @param style style of the node
    * @see NodeShape
    * @see NodeStyle
    */
   public Node(String name, String label, NodeShape shape, NodeStyle style){
      vName = name;
      vLabel = label;
      vShape = shape;
      vStyle = style;
      //For now when we create a node we will not have any edge data. This may
      //change at some point.
      vEdges = new ArrayList();
   }


   /**
    * GetName - used to access the variable name of the node to differentiate
    * from other nodes
    * @return the variable name of the node
    */
   public String mGetName(){
      return vName;
   }

   /**
    * SetLabel - change the label on the node
    * @param label string to change the label to
    */
   public void mSetLabel(String label){
      vLabel = label;
   }


   /**
    * GetLabel - gets the labbel of the current node
    * @return vLabel
    */
   public String mGetLabel(){
      return vLabel;
   }


   /**
    * SetShape - sets the shape of the node
    * @param shape is an enumaration of the shape of the node
    * @see NodeShape
    */
   public void mSetShape(NodeShape shape){
      vShape = shape;
   }


   /**
    * GetShape - gets the shape of the node
    * @return an enumeration of the shape of the node
    * @see NodeShape
    */
   public NodeShape mGetShape(){
      return vShape;
   }


   /**
    * SetStyle - sets the style of the node
    * @param style an enumeration of the style of the node
    * @see NodeStyle
    */
   public void mSetStyle(NodeStyle style){
      vStyle = style;
   }


   /**
    * GetStyle - gets the style of the node
    * @return an enumeration of the style of the node
    * @see NodeStyle
    */
   public NodeStyle mGetStyle(){
      return vStyle;
   }


   /**
    * AddEdge - adds an edge to the edge list for the node
    * @param edge - the edge to add to the list
    * @return ErrorHandler.CANNOTADDEDGE if successful, other error code if not successful
    * @see Edge
    */
   public int mAddEdge(Edge edge){
      int retValue = ErrorHandler.CANNOTADDEDGE;
      if(vEdges.add(edge)){
         retValue = ErrorHandler.NOERROR;
      }
      return retValue;
   }


   /**
    * RemoveEdge - removes an edge from the edge list for the node
    * @param edge - the edge to remove from the list
    * @return ErrorHandler.NOERROR if successful, other error code if not successful
    * @see Edge
    */
   public int mRemoveEdge(Edge edge){
      int retValue = ErrorHandler.CANNOTREMOVEEDGE;
      if(vEdges.remove(edge)){
         retValue = ErrorHandler.NOERROR;
      }
      return retValue;
   }


   /**
    * GetEdgeList - returns a list of edges for the node
    * @return - vEdges
    */
   public ArrayList mGetEdgeList(){
      return vEdges;
   }
}
