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
   //KMW Note: The Graphics2D library begins drawing shapes (or at least ovals) at the upper left coordinate of shape.
   //          However, we are storing the center locations. For drawing purposes, make sure you use the functions
   //          mGetUpperLeft and mGetUpperRight as the parameters for the Graphics2D object to begin drawing.
   private String vId;
   private String  vLabel;
   private NodeShape vShape;
   private NodeStyle vStyle;
   private ArrayList<Edge> vEdges;
   private int vCenterX;
   private int vCenterY;
   private double vDispX; // displacement x
   private double vDispY; // displacement y
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
    * @param shape string value of shape of the node
    * @param style string value of style of the node
    * @see NodeShape
    * @see NodeStyle
    */
   public Node(String id, String label, String shape, String style){
      try{
         mValidateNode(id);
         vId = id;
         if(label != null)
            vLabel = label;
         else
            vLabel = "";
         
         if(shape != null){
            try {
               vShape = NodeShape.valueOf(shape.toUpperCase());
            } catch (IllegalArgumentException e) {
                vShape = NodeShape.CIRCLE;
            }
         }
         else{
            vShape = NodeShape.CIRCLE;
         }
         
         if(style != null)
         {
            try {
               vStyle = NodeStyle.valueOf(style.toUpperCase());
            } catch (IllegalArgumentException e) {
                 vStyle = NodeStyle.SOLID;
            }
         }
         else{
            vStyle = NodeStyle.SOLID;
         }
         //For now when we create a node we will not have any edge data. This may
         //change at some point.
         vEdges = new ArrayList<Edge>();
         
         //for now all of the coordinate data will be set to actual values in the drawer using the accessor functions of this class,
         //but we may want to define it here eventually (especially if the user ever gets control of location or width data).
         vCenterX = 0;
         vCenterY = 0;
         vWidth = 0;
         vHeight = 0;
         vDispX = 0.0;
         vDispY = 0.0;
         
         vFillColor = Color.white;
         vBorderColor = Color.black;
         vLabelColor = Color.black;
         //default to 10pt Monospaced font with bold styling (user will have ability to change this)
         // BAL NOTE: Arial will only work if its installed on your pc, best to default to a logical font
         //           like Monospaced to ensure that it works on all computers
         //           For more info see: http://docs.oracle.com/javase/tutorial/2d/text/fonts.html
         vFont = new Font("Monospaced", 0, 10);
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
   
   public void mSetID(String id){
      vId = id;
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
   public ArrayList<Edge> mGetEdgeList(){
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
    *  Get the displacement x
    * @return the displacement x
    */
   public double mGetDispX() {
	   return vDispX;
   }
   
   /**
    *  Get the displacement y
    * @return the displacement y
    */
   public double mGetDispY() {
	   return vDispY;
   }
   
   /**
    *  Set the displacement x
    * @param x - the new displacement x
    */
   public void mSetDispX(double x) {
	   vDispX = x;
   }
   
   /**
    *  Set the displacement y
    * @param y - the new displacement y
    */
   public void mSetDispY(double y) {
	   vDispY = y;
   }
   
   /**
	 *  Method to return the distance of the displacement vector
	 * @return the distance
	 */
	public double mGetDispDistance() {
		return Math.sqrt(Math.pow(vDispX, 2.0) + Math.pow(vDispY, 2.0));
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

   public int mGetLowerRightX(){
      return (int)(vCenterX + vWidth/2);
   }
   
   /**
    * GetCenterY - returns the y coordinate (in pixels) in the image of the center of the node.
    * @return - vCenterX
    */
   public int mGetLowerRightY(){
      return (int)(vCenterY + vHeight/2);
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

   public boolean mContains(int x, int y) {
      int nX = this.mGetUpperLeftX();
      int nY = this.mGetUpperLeftY();
      int lX = this.mGetLowerRightX();
      int lY = this.mGetLowerRightY();
      //System.out.println(nX + " <= " + x + " <= " + lX);
      //System.out.println(nY + " <= " + y + " <= " + lY);
      if ( (x >= nX && x <= lX) &&
           (y >= nY && y <= lY) ) {
         return true;
      } else {
         return false;
      }
   }
   
}
