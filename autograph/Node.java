package autograph;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.Serializable;
import autograph.exception.*;
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
   private String vId;
   private String  vLabel;
   private NodeShape vShape;
   private NodeStyle vStyle;
   private ArrayList<Edge> vEdges;
   
   //KMW Note: The Graphics2D library begins drawing shapes (or at least ovals) at the upper left coordinate of shape.
   //          However, we are storing the center locations. For drawing purposes, make sure you use the functions
   //          mGetUpperLeft and mGetUpperRight as the parameters for the Graphics2D object to begin drawing.
   private int vCenterX;
   private int vCenterY;
   private int vWidth; //this is the width of the node in pixels (or diameter in case of circle)
   private int vHeight;
   private Color vFillColor;
   private Color vBorderColor;
   private Color vLabelColor;
   private Font vFont;

   /**
    * ValidateNode - makes sure the node has valid parameters for construction
    * @param id - id of the node to validate
    * @throws CannotAddNodeException
    */
   private void mValidateNode(String id) throws CannotAddNodeException{
      //We may want to add logic for the node id being unique, but I'm not sure
      //if we would add it here or in the Graph class (probably Graph)
      if(id == null || id.isEmpty()){
         throw new CannotAddNodeException("Node must have valid id.");
      }
   }
   
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
   public Node(String id, String label, NodeShape shape, NodeStyle style){
      try{
         mValidateNode(id);
         vId = id;
         if(label != null)
            vLabel = label;
         else
            vLabel = "";
         
         if(shape != null)
            vShape = shape;
         else
            vShape = NodeShape.CIRCLE;
         
         if(style != null)
            vStyle = style;
         else
            vStyle = NodeStyle.SOLID;
         
         //For now when we create a node we will not have any edge data. This may
         //change at some point.
         vEdges = new ArrayList<Edge>();
         
         //for now all of the coordinate data will be set to actual values in the drawer using the accessor functions of this class,
         //but we may want to define it here eventually (especially if the user ever gets control of location or width data).
         vCenterX = 0;
         vCenterY = 0;
         vWidth = 0;
         vHeight = 0;
         
         vFillColor = Color.white;
         vBorderColor = Color.black;
         vLabelColor = Color.black;
         //default to 8pt Arial font with no styling (user will have ability to change this)
         //KMW Note: the size is working, I'm not sure it is Arial though...we need to look into this more at some point
         vFont = new Font("Arial", 0, 8);
      }
      catch(CannotAddNodeException e){
    	//TODO: This may need changed to an error file, etc
    	  System.out.println("Error while trying to add node: " + e.getError());
      }
   }


   /**
    * GetName - used to access the variable name of the node to differentiate
    * from other nodes
    * @return the variable name of the node
    */
   public String mGetId(){
      return vId;
   }

   /**
    * SetLabel - change the label on the node
    * @param label string to change the label to
    */
   public void mSetLabel(String label){
      vLabel = label;
   }


   /**
    * GetLabel - gets the label of the current node
    * @return vLabel
    */
   public String mGetLabel(){
      return vLabel;
   }


   /**
    * SetShape - sets the shape of the node
    * @param shape is an enumeration of the shape of the node
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
   
   /**
    * GetCenterX - returns the x coordinate (in pixels) in the image of the center of the node.
    * @return - vCenterX
    */
   public int mGetCenterX(){
      return vCenterX;
   }
   
   /**
    * GetCenterY - returns the y coordinate (in pixels) in the image of the center of the node.
    * @return - vCenterX
    */
   public int mGetCenterY(){
      return vCenterY;
   }
   
   /**
    * SetCenterLocation - sets the location of the node in the image
    * @param x - x coordinate in pixels of the node
    * @param y - y coordinate in pixels of the node
    */
   public void mSetCenterLocation(int x, int y){
      vCenterX = x;
      vCenterY = y;
   }
   
   /**
    * GetCenterX - returns the x coordinate (in pixels) in the image of the center of the node.
    * @return - vCenterX
    */
   public int mGetUpperLeftX(){
      int upperLeftX;
      upperLeftX = vCenterX - vWidth/2;
      return upperLeftX;
   }
   
   /**
    * GetCenterY - returns the y coordinate (in pixels) in the image of the center of the node.
    * @return - vCenterX
    */
   public int mGetUpperLeftY(){
      int upperLeftY;
      upperLeftY = vCenterY - vHeight/2;
      return upperLeftY;
   }
   
   
   /**
    * GetWidth - returns the width of the node (or in the case of circles the diameter) in pixels
    * @return - vWidth 
    */
   public int mGetWidth(){
      return vWidth;
   }
   
   /**
    * GetWidth - resets the size of the node.
    * @param width - the width to set for the node
    */
   public void mSetWidth(int width){
      vWidth = width;
   }
   
   /**
    * GetHeight - returns the height of the node in pixels
    * @return - vHeight
    */
   public int mGetHeight(){
      return vHeight;
   }
   
   /**
    * SetHeight - resets the height of the node
    * @param height - the height to set for the node
    */
   public void mSetHeight(int height ){
      vHeight = height;
   }
   
   /**
    * GetFillColor - returns the fill color for the node
    * @return - the fill color for the node
    */
   public Color mGetFillColor(){
      return vFillColor;
   }
   
   /**
    * SetFillColor - sets the fill color for the node
    * @param fillColor - the fill color to set for the node
    */
   public void mSetFillColor(Color fillColor){
      vFillColor = fillColor;
   }
   
   /**
    * GetBorderColor - returns the border color for the node
    * @return - the border color for the node
    */
   public Color mGetBorderColor(){
      return vBorderColor;
   }
   
   /**
    * SetFillColor - sets the border color for the node
    * @param fillColor - the border color to set for the node
    */
   public void mSetBorderColor(Color borderColor){
      vBorderColor = borderColor;
   }
   
   /**
    * GetLabelrColor - returns the label color for the node
    * @return - the Label color for the node
    */
   public Color mGetLabelColor(){
      return vLabelColor;
   }
   
   /**
    * SetFillColor - sets the label color for the node
    * @param fillColor - the label color to set for the node
    */
   public void mSetLabelColor(Color labelColor){
      vLabelColor = labelColor;
   }
   
   /**
    * GetFont - Gets the font for the label's text
    * @return - the font for the label's text
    */
   public Font mGetFont(){
      return vFont;
   }
   
   /**
    * SetFont - Sets the font for the label's text
    * @param font - the font to set for the label's text
    */
   public void mSetFont(Font font){
      vFont = font;
   }
   
   /**
    * DrawNode - renders the node given the node's position and the graphics object g.
    * @param g - the graphics object in which to render the image.
    */
   public void mDrawNode(Graphics g){
      //make the assumption that no object's width should be 0
      if(this.mGetWidth() != 0){
         int upperLeftX = this.mGetUpperLeftX();
         int upperLeftY = this.mGetUpperLeftY();
         
         //KMW Note: The Graphics2D library draws strings using the lower left part of the string as the beginning coordinates of the graph.
         //          We now need to calculate the optimal position of the lower left portion of the string so that the string is centered within
         //          the bounds of the circle.
         g.setFont(this.mGetFont());
         FontMetrics fm = g.getFontMetrics();
         int labelWidth = fm.stringWidth(this.mGetLabel());
         int labelHeight = fm.getHeight();
         int widthDifference = this.mGetWidth() - labelWidth;
         if(widthDifference <= 0){
            //dynamically resize the node so the label fits. (because it is a circle resize both height and width)
            this.mSetWidth(labelWidth + 20);
            this.mSetHeight(labelWidth + 20);
            
            widthDifference = this.mGetWidth() - labelWidth;
         }
         
         int heightDifference = this.mGetHeight() - labelHeight;
         if(heightDifference <= 0){
            //dynamically resize the node so the label fits. (because it is a circle resize both height and width)
            this.mSetWidth(labelHeight + 20);
            this.mSetHeight(labelHeight + 20);
            widthDifference = this.mGetWidth() - labelWidth;
         }
         
         int labelLeftX = upperLeftX + widthDifference/2; //x coordinate of the lower left position of the string (for centering the string in the node)
         int labelLeftY = upperLeftY + heightDifference/2 + labelHeight/2; //y coordinate of the lower left position of the string (for centering the string in the node)
         
         switch (this.vShape){
         case CIRCLE:
         case OVAL:
            //draw shape first so label is not overwritten
            g.setColor(this.mGetFillColor());
            g.fillOval(upperLeftX, upperLeftY, this.mGetWidth(), this.mGetHeight());
            g.setColor(this.mGetBorderColor());
            g.drawOval(upperLeftX, upperLeftY, this.mGetWidth(), this.mGetHeight());
           
            //draw the label
            g.setColor(this.mGetLabelColor());
            g.drawString(this.mGetLabel(), labelLeftX, labelLeftY);
            
            break;
         case SQUARE:
         case RECTANGLE:
            //draw shape first so label is not overwritten
            g.setColor(this.mGetFillColor());
            g.fillRect(upperLeftX, upperLeftY, this.mGetWidth(), this.mGetHeight());
            g.setColor(this.mGetBorderColor());
            g.drawRect(upperLeftX, upperLeftY, this.mGetWidth(), this.mGetHeight());
           
            //draw the label
            g.setColor(this.mGetLabelColor());
            g.drawString(this.mGetLabel(), labelLeftX, labelLeftY);
            break;
         case TRIANGLE:
            //TODO: implement drawing for Triangle nodes
            break;
         }
      }
   }
}
