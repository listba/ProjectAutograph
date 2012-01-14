package autograph;

import java.io.*;

/**
 * Graph Helper class used to manipulate graph objects
 *
 * @author Ben List
 * @version 1.0
 */
public class GraphHelper {

   /**
    * Draws the graph object in the most efficent way possible
    *
    * Note: Probably going to have a Java drawing return type, not sure on details of implementation yet.
    *
    * @param   graph    The Graph to be drawn
    * @return           Returns an integer based error code or 0 for success 
    * @see     Graph
    */
   static int mDrawGraph(Graph graph) {
      int returnVal = ErrorHandler.NOERROR;
      return returnVal;
   }
   
   /**
    * Saves the graph object as a serialized object to the given file location
    *
    * @param   graph       The graph to be saved
    * @param   fileName    The file name of the graph to be saved
    * @param   fileLoc     The location to save the graph to
    * @return              Returns an integer based error code or 0 for success 
    * @see     Graph
    */
   static int mSaveGraphObject(Graph graph, String fileName, String fileLoc) {
      int returnVal = ErrorHandler.NOERROR;
      try {
         // Open filestream to write graph object to
         FileOutputStream fileOut = 
            new FileOutputStream(fileLoc + "/" + fileName + ".ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         // Write graph object to file
         out.writeObject(graph);
         out.close();
         fileOut.close();
      } catch (IOException e) { // Saving the file failed
         returnVal = ErrorHandler.IOEXCEPTION; // IO EXCEPTION TEMP ERROR CODE
      }
      return returnVal;
   }
   
   /**
    * Loads the serialized graph object from the file location given
    *
    * @param   fileName    The file name of the graph to be loaded (with .ser extension)
    * @param   fileLoc     The location to load the graph from
    * @return              The deserialized graph object
    * @see     Graph
    */
   static Graph mLoadGraphObject(String fileName, String fileLoc) {
      Graph graph = new Graph();
      try {
         // Open file to read in graph object
         FileInputStream fileIn = 
            new FileInputStream(fileLoc + "/" + fileName);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         // Read in graph object from file
         graph = (Graph) in.readObject();
         in.close();
         fileIn.close();
      } catch(IOException e) { // Reading the file failed
         // IO EXCEPTION TEMP ERROR CODE
      }
      // Loading the graph object was successful
      return graph;
   }
   
   /**
    * Exports a graph to an xml file at the given location
    *
    * @param   graph       The graph to be exported
    * @param   fileName    The file name to be saved (with .xml extension)
    * @param   fileLoc     The location to save the xml file to
    * @return              Returns an integer based error code or 0 for success 
    * @see     Graph
    */
   static int mExportGraphToXML(Graph graph, String fileName, String fileLoc) {
      int returnVal = ErrorHandler.NOERROR;
      return returnVal;
   }
   
   /**
    * Imports a graph from xml file at the given location
    *
    * @param   fileName    The file name to be loaded (with .xml extension)
    * @param   fileLoc     The location to load the xml file from
    * @return              The graph loaded from the xml file
    * @see     Graph
    */
   static Graph mImportGraphFromXML(String fileName, String fileLoc) {
      Graph graph = new Graph();
      return graph;
   }
   
   /**
    * Exports a graph to a plaintext document in our Graphing Language
    *
    * @param   graph       The graph to be exported
    * @param   fileName    The file name to be saved (with .agl extension)
    * @param   fileLoc     The location to save the agl file to
    * @return              Returns an integer based error code or 0 for success 
    * @see     Graph
    */
   static int mExportGraphToLanguage(Graph graph, String fileName, String fileLoc) {
      int returnVal = ErrorHandler.NOERROR;
      return 0;
   }
   
   /**
    * Imports a graph from a plaintext document in our Graphing Language
    *
    * @param   fileName    The file name to be loaded (with .agl extension)
    * @param   fileLoc     The location to load the agl file from
    * @return              The graph loaded from the agl file
    * @see     Graph
    */
   static Graph mImportGraphFromLanguage(String fileName, String fileLoc) {
      Graph graph = new Graph();
      return graph;
   }
}
