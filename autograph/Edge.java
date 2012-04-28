package autograph;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.io.Serializable;

import autograph.Node.NodeShape;
import autograph.exception.*;
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
	// This stores the allowed ditance frome dge to be considered a valid selection click
	private final static double CLICK_BUFFER_DISTANCE = 5.0;
	private String vId;
	private String vLabel;
	private Node vStartNode;
	private Node vEndNode;
	private Direction vDirection;
	private EdgeStyle vEdgeStyle;
	private int vStartX;
	private int vStartY;
	private int vEndX;
	private int vEndY;
	private Color vEdgeColor;
	private Color vLabelColor;
	private Font vFont;

	private void mValidateEdge(String id, Node startNode, Node endNode) throws CannotAddEdgeException{
		if(id == null || id.isEmpty()){
			throw new CannotAddEdgeException("Edge must contain valid id.");
		}
		else if(startNode == null || endNode == null){
			throw new CannotAddEdgeException("Edge must have start and end node values.");
		}
	}

	/**
	 * Edge Constructor - builds the edge object
	 * @param id - variable name of the edge
	 * @param label - label displayed for the edge
	 * @param startNode - node at start of the edge
	 * @param endNode - node at end of the edge
	 * @param direction - direction of the edge
	 * @param style - style of the edge
	 * @see NodeStyle
	 * @see Direction
	 */
	public Edge(String id, String label, Node startNode, Node endNode,
			String direction, String style, boolean bIgnoreValidation){
		try{
			if(!bIgnoreValidation){
				mValidateEdge(id, startNode, endNode);
			}
			vId = id;
			vLabel = label;
			vStartNode = startNode;
			vEndNode = endNode;

			if(direction != null){
				try {
					vDirection = Direction.valueOf(direction.toUpperCase());
				} catch (IllegalArgumentException e) {
					vDirection = Direction.NODIRECTION;
				}
			}
			else{
				vDirection = Direction.NODIRECTION;
			}

			if(style != null){
				try {
					vEdgeStyle = EdgeStyle.valueOf(style.toUpperCase());
				} catch (IllegalArgumentException e) {
					vEdgeStyle = EdgeStyle.SOLID;
				}
			}
			else{
				vEdgeStyle = EdgeStyle.SOLID;
			}

			vEdgeColor = Color.black;
			vLabelColor = Color.black;
			vFont = new Font("Monospaced", 0, 10);
		}
		catch (CannotAddEdgeException e){
			//TODO: This may need changed to an error file, etc
			System.out.println("Error while trying to add edge: " + e.getError());
		}
	}

	/**
	 * GetName - used to access the variable name of the edge object to differentiate
	 * from other edges
	 * @return the variable name of the edge
	 */
	public String mGetId(){
		return vId;
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
	 * SetDirection - sets the direction of the edge
	 * @param dir - a string of the direction of the edge
	 * @see Direction
	 */
	public void mSetDirection(String dir) {
		if(dir != null){
			try {
				vDirection = Direction.valueOf(dir.toUpperCase());
			} catch (IllegalArgumentException e) {
				vDirection = Direction.NODIRECTION;
			}
		}
		else{
			vDirection = Direction.NODIRECTION;
		}
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
	 * SetEdgeStyle - sets the edge's style
	 * @param style - string of the style
	 * @see EdgeStyle
	 */
	public void mSetEdgeStyle(String style) {
		if(style != null){
			try {
				vEdgeStyle = EdgeStyle.valueOf(style.toUpperCase());
			} catch (IllegalArgumentException e) {
				vEdgeStyle = EdgeStyle.SOLID;
			}
		}
		else{
			vEdgeStyle = EdgeStyle.SOLID;
		}
	}

	/**
	 * GetEdgeStyle - gets the edge's style
	 * @return - an enumeration of the edge style
	 * @see EdgeStyle
	 */
	public EdgeStyle mGetEdgeStyle(){
		return vEdgeStyle;
	}

	/**
	 * GetStartX - returns the x coordinate of the start position of the edge
	 * @return vStartX
	 */
	public int mGetStartX(){
		return vStartX;
	}

	/**
	 * GetStartY - returns the y coordinate of the start position of the edge
	 * @return vStartY
	 */
	public int mGetStartY(){
		return vStartY;
	}

	/**
	 * GetEndX - returns the x coordinate of the end position of the edge
	 * @return vEndX
	 */
	public int mGetEndX(){
		return vEndX;
	}

	/**
	 * GetEndY - returns the y coordinate of the end position of the edge
	 * @return - vEndY
	 */
	public int mGetEndY(){
		return vEndY;
	}

	/**
	 * SetStartCoordinates - sets the new start point of the edge
	 * @param x - the x coordinate (in pixels) of the new start location.
	 * @param y - the y coordinate (in pixels) of the new start location.
	 */
	public void mSetStartCoordinates(int x, int y){
		vStartX = x;
		vStartY = y;
	}

	/**
	 * SetEndCoordinates - sets the new end point of the edge
	 * @param x - the x coordinate (in pixels) of the new end location.
	 * @param y - the y coordinate (in pixels) of the new end location.
	 */
	public void mSetEndCoordinates(int x, int y){
		vEndX = x;
		vEndY = y;
	}

	/**
	 * GetEdgeColor - returns the color of the edge
	 * @return - vColor
	 */
	public Color mGetEdgeColor(){
		return vEdgeColor;
	}

	/**
	 * SetEdgeColor - sets the color of the edge
	 * @param color - the color to set the edge
	 */
	public void mSetEdgeColor(Color color){
		vEdgeColor = color;
	}

	/**
	 * GetLabelColor - returns the color of the edge
	 * @return - vColor
	 */
	public Color mGetLabelColor(){
		return vLabelColor;
	}

	/**
	 * SetLabelColor - sets the color of the edge
	 * @param color - the color to set the edge
	 */
	public void mSetLabelColor(Color color){
		vLabelColor = color;
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
	 * mContains - calculates the shortest distance between the input coordinates and
	 *             any segment along the line, if this distance is within the "Click Buffer Range"
	 *             return true.
	 * @param x - the x coordinate to check
	 * @param y - the y coordinate to check
	 * 
	 */
	public boolean mContains(int x, int y)
	{
		boolean contains = false;
		int x1 = this.vStartX;
		int y1 = this.vStartY;
		int x2 = this.vEndX;
		int y2 = this.vEndY;

		double px = x2-x1;
		double py = y2-y1;

		double dot = (px*px) + (py*py);

		double u =  ((x - x1) * px + (y - y1) * py) / dot;

		if (u > 1){
			u = 1;
		} else if(u < 0) {
			u = 0;
		}

		double newX = x1 + u * px;
		double newY = y1 + u * py;

		double dx = newX - x;
		double dy = newY - y;

		double dist = Math.sqrt(dx*dx + dy*dy);
		System.out.println("DISTANCE:"+dist);
		if (dist <= CLICK_BUFFER_DISTANCE ) {
			contains = true;
		}
		return contains;
	}
}
