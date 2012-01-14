package autograph;
import java.io.Serializable;
/**
 * Edge contains all data for edge objects of a Graph.
 *
 * @author Keith Wentzel
 * @version 1.0
 */
public class Edge implements Serializable {

   /**
    * Direction - the various directions supported by the edge object
    */
   public enum Direction{
      NODIRECTION,
      STARTDIRECTION,
      ENDDIRECTION,
      DOUBLEDIRECTION
   };

   /**
    * EdgeStyle - the various styles supported by the edge object
    */
   public enum EdgeStyle{
      SOLID,
      DOTTED,
      DASHED
   };

   private String vName;
   private String vLabel;
   private Node vStartNode;
   private Node vEndNode;
   private Direction vDirection;
   private EdgeStyle vEdgeStyle;

   /**
    * Edge Constructor - builds the edge object
    * @param name - variable name of the edge
    * @param label - label displayed for the edge
    * @param startNode - node at start of the edge
    * @param endNode - node at end of the edge
    * @param direction - direction of the edge
    * @param style - style of the edge
    * @see NodeStyle
    * @see Direction
    */
   public Edge(String name, String label, Node startNode, Node endNode,
           Direction direction, EdgeStyle style){
      vName = name;
      vLabel = label;
      vStartNode = startNode;
      vEndNode = endNode;
      vDirection = direction;
      vEdgeStyle = style;
   }

   /**
    * GetName - used to access the variable name of the edge object to differentiate
    * from other edges
    * @return the variable name of the edge
    */
   public String mGetName(){
      return vName;
   }

   /**
    * SetLabel - changes the label of the edge
    * @param label - new label for the edge
    */
   public void mSetLabel(String label){
      vLabel = label;
   }

   /**
    * GetLabel - gets the label of the edge
    * @return the label of the edge
    */
   public String mGetLabel(){
      return vLabel;
   }

   /**
    * SetStartNode - sets the start node of the edge
    * @param start - the nodes the start of the edge is connected to
    * @see Node
    */
   public void mSetStartNode(Node start){
      vStartNode = start;
   }

   /**
    * GetStartNode - gets the start node of the edge
    * @return - the nodes the start of the edge is connected to
    * @see Node
    */
   public Node mGetStartNode(){
      return vStartNode;
   }

   /**
    * SetEndNode - sets the end node of the edge
    * @param end - the node the end of the edge is connected to
    * @see Node
    */
   public void mSetEndNode(Node end){
      vEndNode = end;
   }

   /**
    * GetEndNode - gets the end node of the edge
    * @return the node the end of the edge is connected to
    * @see Node
    */
   public Node mGetEndNode(){
      return vEndNode;
   }

   /**
    * SetDirection - sets the direction of the edge
    * @param dir - an enumeration of the direction of the edge
    * @see Direction
    */
   public void mSetDirection(Direction dir){
      vDirection = dir;
   }

   /**
    * GetDirection - gets the direction of the edge
    * @return - an enumeration of the direction of the edge
    * @see Direction
    */
   public Direction mGetDirection(){
      return vDirection;
   }

   /**
    * SetEdgeStyle - sets the edge's style
    * @param style - an enumeration of the edge style
    * @see EdgeStyle
    */
   public void mSetEdgeStyle(EdgeStyle style){
      vEdgeStyle = style;
   }

   /**
    * GetEdgeStyle - gets the edge's style
    * @return - an enumeration of the edge style
    * @see EdgeStyle
    */
   public EdgeStyle mGetEdgeStyle(){
      return vEdgeStyle;
   }
}
