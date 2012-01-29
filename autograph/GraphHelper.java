package autograph;
import java.awt.Color;
import java.awt.Graphics;
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
	      node.mDrawNode(g);
	   }
	   
	   // Draw edges
	   // KMW NOTE: the current implementation of mDrawEdge will draw a straight line from startNode to endNode.
	   //           Somewhere (either in the node placement algorithm or in the edge drawing algorithm) we will
	   //           need to make sure that edges will not cross if we don't want them to.
	   ArrayList<Edge> edges = graph.mGetEdgeList();
	   for (Edge edge : edges) {
		   edge.mDrawEdge(g);
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
   public static Graph mImportGraphFromXML(String filePath) throws CannotAddNodeException, CannotAddEdgeException {
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
               Node node = getNodeElement(el);
               if (node != null) {
                  System.out.println(node.mGetId() + " added");
                  graph.mAddNode(node);
               }
            }
         } else {
            // TODO: THROW INVALID XML EXCEPTION
         }
         nl = docEle.getElementsByTagName("Edge");
         // If there are Edge elements
         if(nl != null && nl.getLength() > 0) {
            // Loop through each Node Element and load into Node object
            for(int i = 0; i < nl.getLength(); i++) {
               Element el = (Element)nl.item(i);
               Edge edge = getEdgeElement(el, graph);
               // If XML was valid and edge was created
               if (edge != null) {
                  System.out.println(edge.mGetId() + " added");
                  graph.mAddEdge(edge);
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
   private static Node getNodeElement(Element el) {
      // TODO: Get additional elements from XML
      Node node = null;
      String id = el.getAttribute("id");
      if (id != null) {
         node = new Node(id, null, null, null);
      }
      else {
         // TODO: ADD INVALID XML EXCEPTION
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
   private static Edge getEdgeElement(Element el, Graph graph) {
      // TODO: Get additional elements from XML
      Edge edge = null;
      String id = el.getAttribute("id");
      Node startNode = graph.mGetNodeById(el.getAttribute("startNode"));
      Node endNode = graph.mGetNodeById(el.getAttribute("endNode"));
      if (id != null && startNode != null && endNode != null) {
         edge = new Edge(id, null, startNode, endNode, null, null);
      }
      else {
         // TODO:THROW INVALID XML EXCEPTION
      }
      return edge;
   }
}
