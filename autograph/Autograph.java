package autograph;
import java.io.*;

public class Autograph {

   public static void main(String [] args) {
      String filePath = "Graph.xml";
      Graph g = new Graph("");
      GraphHelper.mImportGraphFromXML(filePath);
   }
}