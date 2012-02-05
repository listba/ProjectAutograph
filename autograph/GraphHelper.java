package autograph;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.io.*;
import autograph.exception.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Graph Helper class used to manipulate graph objects
 *
 * @author Ben List
 * @version 1.0
 */
public class GraphHelper {

   /**
    * Draws the graph object in the most efficient way possible
    *
    * Note: Probably going to have a Java drawing return type, not sure on details of implementation yet.
    *
    * @param   graph    The Graph to be drawn
    * @see     Graph
    */
	
	// TODO: To draw in the center you have to pass in the panel or coordinates
   public static void mDrawGraph(Graph graph, Graphics g) {
	   g.setColor(Color.WHITE);
	   
	   // TODO: This is a lot slower than it needs to be.. You should use a hash map with the node ID
	   // Draw nodes
	   ArrayList<Node> nodes = graph.mGetNodeList();
	   
	   //TODO: have user dynamically set width/height rather than hard coding it here. (Or dynamically calculate height/width based on label size)
	   //      currently, the node size will be updated if the label does not fit in the node within the mDrawNode function.
	   int diameter = 50;
	   
	   //determine the location of each node in the graph, and draw that node.
	   for (int n=0; n<nodes.size(); n++) {
	      Node node = nodes.get(n);
	      node.mSetCenterLocation(n*diameter*2 + diameter/2, 20 + diameter/2);
	      node.mSetWidth(diameter);
	      node.mSetHeight(diameter);
	      mDrawNode(g, node);
	   }
	   
	   // Draw edges
	   // KMW NOTE: the current implementation of mDrawEdge will draw a straight line from startNode to endNode.
	   //           Somewhere (either in the node placement algorithm or in the edge drawing algorithm) we will
	   //           need to make sure that edges will not cross if we don't want them to.
	   ArrayList<Edge> edges = graph.mGetEdgeList();
	   for (Edge edge : edges) {
		   mDrawEdge(g, edge);
	   }
   }
   
   /**
    * Saves the graph object as a serialized object to the given file location
    *
    * @param   graph       The graph to be saved
    * @param   filePath    The filepath to be saved (with .xml extension)
    * @see     Graph
    */
   public static void mSaveGraphObject(Graph graph, String filePath) {
      try {
         // Open filestream to write graph object to
         FileOutputStream fileOut = 
            new FileOutputStream(filePath);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         // Write graph object to file
         out.writeObject(graph);
         out.close();
         fileOut.close();
      } catch (IOException e) { // Saving the file failed
         // IO EXCEPTION TEMP ERROR CODE
      }
   }
   
   /**
    * Loads the serialized graph object from the file location given
    *
    * @param   filePath    The filepath to be saved (with .xml extension)
    * @return              The deserialized graph object
    * @see     Graph
    */
   public static Graph mLoadGraphObject(String filePath) {
      Graph graph = new Graph("");
      try {
         // Open file to read in graph object
         FileInputStream fileIn = 
            new FileInputStream(filePath);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         // Read in graph object from file
         graph = (Graph) in.readObject();
         in.close();
         fileIn.close();
      } catch(IOException e) { // Reading the file failed
         // IO EXCEPTION TEMP ERROR CODE
      } catch (ClassNotFoundException e) { 
         // Class Not Found Exception
      }
      // Loading the graph object was successful
      return graph;
   }
   
   /**
    * Exports a graph to an xml file at the given location
    *
    * @param   graph       The graph to be exported
    * @param   filePath    The filepath to be saved (with .xml extension)
    * @see     Graph
    */
   public static void mExportGraphToXML(Graph graph, String filePath) {
   }
   
   /**
    * Imports a graph from xml file at the given location
    *
    * @param   filePath    The filepath to be saved (with .xml extension)
    * @return              The graph loaded from the xml file
    * @see     Graph
    */
   public static Graph mImportGraphFromXML(String filePath) {
      Graph graph = null;
      Document dom;
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      try {
         // Using factory get an instance of document builder
         DocumentBuilder db = dbf.newDocumentBuilder();
         // Parse using builder to get DOM representation of the XML file
         dom = db.parse(filePath);
         // Get Root Element
         Element docEle = dom.getDocumentElement();
         // Get Graph Title
         graph = new Graph(docEle.getAttribute("title"));
         // Get a NodeList of <Node> elements
         NodeList nl = docEle.getElementsByTagName("Node");
         // If there are Node elements
         if(nl != null && nl.getLength() > 0) {
            // Loop through each Node Element and load into Node object
            for(int i = 0; i < nl.getLength(); i++) {
               Element el = (Element)nl.item(i);
               try {
                  Node node = getNodeElement(el);
                  System.out.println(node.mGetId() + " added ("+ i +")");
                  graph.mAddNode(node);
               } catch (InvalidXMLException e) {
                  e.getMessage();
				      e.getCause();
               } catch (CannotAddNodeException e) {
                  e.getMessage();
				      e.getCause();
               }
            }
         } else {
            // TODO: THROW INVALID XML EXCEPTION
         }
         nl = docEle.getElementsByTagName("Edge");
         // If there are Edge elements
         if(nl != null && nl.getLength() > 0) {
            // Loop through each Edge Element and load into Edge object
            for(int i = 0; i < nl.getLength(); i++) {
               Element el = (Element)nl.item(i);
               try {
                  Edge edge = getEdgeElement(el, graph);
                  System.out.println(edge.mGetId() + " added ("+ i +")");
                  graph.mAddEdge(edge);
               } catch (InvalidXMLException e) {
                  e.getMessage();
				      e.getCause();
               } catch (CannotAddEdgeException e) {
                  e.getMessage();
				      e.getCause();
               }
            }
         } else { 
            // TODO: THROW INVALID XML EXCEPTION
         }
      } catch(ParserConfigurationException e) {
         e.printStackTrace();
      } catch(SAXException e) {
         e.printStackTrace();
      } catch(IOException e) {
         e.printStackTrace();
      }
      return graph;
   }
   /**
    * Exports a graph to a plaintext document in our Graphing Language
    *
    * @param   graph       The graph to be exported
    * @param   fileName    The file name to be saved (with .agl extension)
    * @param   fileLoc     The location to save the agl file to
    * @see     Graph
    */
   public static void mExportGraphToLanguage(Graph graph, String fileName, String fileLoc) {
   }
   
   /**
    * Imports a graph from a plaintext document in our Graphing Language
    *
    * @param   fileName    The file name to be loaded (with .agl extension)
    * @param   fileLoc     The location to load the agl file from
    * @return              The graph loaded from the agl file
    * @see     Graph
    */
   public static Graph mImportGraphFromLanguage(String fileName, String fileLoc) {
      Graph graph = new Graph("");
      return graph;
   }
   
   /**
    * Gets Node object from xml element
    * 
    * @param   el          The xml node element
    * @return              The Node object
    */
   private static Node getNodeElement(Element el) throws InvalidXMLException{
      // TODO: Get additional elements from XML
      Node node = null;
      String id = el.getAttribute("id");
      if (id != null) {
         node = new Node(id, null, null, null);
      }
      else {
         // Invalid XML for a node
         throw new InvalidXMLException("Node Element is missing the required ID attribute");
      }
      return node;
   }

   /**
    * Gets Edge object from xml element
    * 
    * @param   el          The xml edge element
    * @param   graph       The current graph with the nodes 
    * @return              The edge object
    */
   private static Edge getEdgeElement(Element el, Graph graph) throws InvalidXMLException {
      // TODO: Get additional elements from XML
      Edge edge = null;
      String id = el.getAttribute("id");
      Node startNode = graph.mGetNodeById(el.getAttribute("startNode"));
      Node endNode = graph.mGetNodeById(el.getAttribute("endNode"));
      if (id != null && startNode != null && endNode != null) {
         edge = new Edge(id, null, startNode, endNode, null, null);
      }
      else {
         // Invalid XML for an edge
         throw new InvalidXMLException("Edge Element is missing one or more of the following required attributes: " +
         "ID, startNode, endNode");
      }
      return edge;
   }
   
      /**
    * DrawNode - renders the node given the node's position and the graphics object g.
    * @param g - the graphics object in which to render the image.
    */
   private static void mDrawNode(Graphics g, Node n){
      //make the assumption that no object's width should be 0
      if(n.mGetWidth() != 0){
         int upperLeftX = n.mGetUpperLeftX();
         int upperLeftY = n.mGetUpperLeftY();
         
         //KMW Note: The Graphics2D library draws strings using the lower left part of the string as the beginning coordinates of the graph.
         //          We now need to calculate the optimal position of the lower left portion of the string so that the string is centered within
         //          the bounds of the circle.
         g.setFont(n.mGetFont());
         FontMetrics fm = g.getFontMetrics();
         int labelWidth = fm.stringWidth(n.mGetLabel());
         int labelHeight = fm.getHeight();
         int widthDifference = n.mGetWidth() - labelWidth;
         if(widthDifference <= 0){
            //dynamically resize the node so the label fits. (because it is a circle resize both height and width)
            n.mSetWidth(labelWidth + 20);
            n.mSetHeight(labelWidth + 20);
            
            widthDifference = n.mGetWidth() - labelWidth;
         }
         
         int heightDifference = n.mGetHeight() - labelHeight;
         if(heightDifference <= 0){
            //dynamically resize the node so the label fits. (because it is a circle resize both height and width)
            n.mSetWidth(labelHeight + 20);
            n.mSetHeight(labelHeight + 20);
            widthDifference = n.mGetWidth() - labelWidth;
         }
         
         int labelLeftX = upperLeftX + widthDifference/2; //x coordinate of the lower left position of the string (for centering the string in the node)
         int labelLeftY = upperLeftY + heightDifference/2 + labelHeight/2; //y coordinate of the lower left position of the string (for centering the string in the node)
         
         switch (n.mGetShape()){
         case CIRCLE:
         case OVAL:
            //draw shape first so label is not overwritten
            g.setColor(n.mGetFillColor());
            g.fillOval(upperLeftX, upperLeftY, n.mGetWidth(), n.mGetHeight());
            g.setColor(n.mGetBorderColor());
            g.drawOval(upperLeftX, upperLeftY, n.mGetWidth(), n.mGetHeight());
           
            //draw the label
            g.setColor(n.mGetLabelColor());
            g.drawString(n.mGetLabel(), labelLeftX, labelLeftY);
            
            break;
         case SQUARE:
         case RECTANGLE:
            //draw shape first so label is not overwritten
            g.setColor(n.mGetFillColor());
            g.fillRect(upperLeftX, upperLeftY, n.mGetWidth(), n.mGetHeight());
            g.setColor(n.mGetBorderColor());
            g.drawRect(upperLeftX, upperLeftY, n.mGetWidth(), n.mGetHeight());
           
            //draw the label
            g.setColor(n.mGetLabelColor());
            g.drawString(n.mGetLabel(), labelLeftX, labelLeftY);
            break;
         case TRIANGLE:
            //TODO: implement drawing for Triangle nodes
            break;
         }
      }
   }
   /**
    * DrawEdge - Draws an edge between two nodes. The edge will dynamically choose one of 4 points on each
    *            node as the start/end point
    * @param g - The graphics object to draw the edge for.
    */
   private static void mDrawEdge(Graphics g, Edge e) {
      //TODO: implement for non-straight edges
      //TODO: implement for different endge styles/directions.
      //TODO: implement label placement.
      //TODO: figure out how we want to handle the case where the edge label is longer than the edge (if we need to recalculate node position etc.)
      
      g.setColor(e.mGetColor());
      
      int startX;
      int startY;
      int endX;
      int endY;
      int differenceX;
      int differenceY;
      
      Node startNode = e.mGetStartNode();
      Node endNode = e.mGetEndNode();
      
      int startNodeCenterX = startNode.mGetCenterX();
      int startNodeCenterY = startNode.mGetCenterY();
      int endNodeCenterX = endNode.mGetCenterX();
      int endNodeCenterY = endNode.mGetCenterY();
      
      if(startNodeCenterX - endNodeCenterX > 0){
         //we will either use the left, top, or bottom point
         differenceX = startNodeCenterX - endNodeCenterX;
         if(startNodeCenterY - endNodeCenterY > 0){
            //we will either user the left point or the top point
            differenceY = startNodeCenterY - endNodeCenterY;
            if (differenceX > differenceY){ //startNodeX > endNodeX && startNodeY > endNodeY && x difference is bigger.
               //use the left point on the start node and right point on the end node
               startX = startNodeCenterX - startNode.mGetWidth()/2;
               startY = startNodeCenterY;
               endX = endNodeCenterX + endNode.mGetWidth()/2;
               endY = endNodeCenterY;
            }
            else { // startNodex > endNodeX && startNodeY > endNodeY && y difference is bigger
               //use the top point on the start node and bottom point on the end node
               startX = startNodeCenterX;
               startY = startNodeCenterY - startNode.mGetHeight()/2;
               endX = endNodeCenterX;
               endY = endNodeCenterY + endNode.mGetHeight()/2;
            }
         }
         else{ // startNodeX > endNodeX && endNodeY >= startNodeY
            //we will either use the left point or the bottom point
            differenceY = endNodeCenterY - startNodeCenterY;
            if (differenceX > differenceY){ //startNodeX > endNodeX && endNodeY >= startNodeY && differenceX > differenceY
               //use the left point on the start node and the right point on the end node
               startX = startNodeCenterX - startNode.mGetWidth()/2;
               startY = startNodeCenterY;
               endX =  endNodeCenterX + endNode.mGetWidth()/2;
               endY = endNodeCenterY;
            }
            else{
               //use the bottom point on the start node and the top point on the end node
               startX = startNodeCenterX;
               startY = startNodeCenterY + startNode.mGetHeight()/2;
               endX = endNodeCenterX;
               endY = endNodeCenterY - endNode.mGetHeight()/2;
            }
         }
      }
      else{
         //we will either use the right, top, or bottom point on the start node
         differenceX = endNodeCenterX - startNodeCenterX;
         if(startNodeCenterY - endNodeCenterY > 0){
            //use either right point or top point
            differenceY = startNodeCenterY - endNodeCenterY;
            if(differenceX > differenceY){
               //use the right point on the start node and the left point on the end node
               startX = startNodeCenterX + startNode.mGetWidth()/2;
               startY = startNodeCenterY;
               endX = endNodeCenterX - endNode.mGetWidth()/2;
               endY = endNodeCenterY;
            }
            else{
               //use the top point on the start node and the bottom point on the end node
               startX = startNodeCenterX;
               startY = startNodeCenterY - startNode.mGetHeight()/2;
               endX = endNodeCenterX;
               endY = endNodeCenterY + endNode.mGetWidth()/2;
            }
         }
         else{
            //use either right point or bottom point
            differenceY = endNodeCenterY - startNodeCenterY;
            if(differenceX > differenceY){
               //use right point on the start node and the left point on the end node
               startX = startNodeCenterX + startNode.mGetWidth()/2;
               startY = startNodeCenterY;
               endX = endNodeCenterX - endNode.mGetWidth()/2;
               endY = endNodeCenterY;
            }
            else{
               //use bottom point on the start node and the top point on the end node
               startX = startNodeCenterX;
               startY = startNodeCenterY + startNode.mGetHeight()/2;
               endX = endNodeCenterX;
               endY = endNodeCenterY - endNode.mGetHeight()/2;
            }
         }
      }
      
      
      g.drawLine(startX, startY, endX, endY);
   }
}
