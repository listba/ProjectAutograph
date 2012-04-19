package autograph;

import java.awt.Font;
import java.util.Stack;

import autograph.Edge.Direction;
import autograph.Edge.EdgeStyle;
import autograph.Node.NodeShape;
import autograph.exception.CannotLoadGraph;

public class GMLParser {
   
   private static int vCurrentIndex = 0;
   private static StringBuilder vGmlText;
   
   //The following enums are the gml attributes we support for each associated
   //level of the gml text. 
   private static enum graphAttributesGML{
      LABEL,
      NODE,
      EDGE,
      HIERARCHIC
   }
   
   private static enum nodeAttributesGML{
      ID,
      LABEL,
      NAME,
      LABELGRAPHICS,
      GRAPHICS
   }
   
   private static enum nodeGraphicsAttributesGML{
      W,
      H,
      TYPE,
      FILL,
      OUTLINE,
      OUTLINESTYLE,
   }
   
   private static enum nodeLabelGraphicsAttributesGML{
      TEXT,
      FONTSIZE,
      FONTSTYLE,
      FONTNAME,
      COLOR
   }
   
   private static enum edgeAttributesGML{
      SOURCE,
      TARGET,
      LABEL,
      GRAPHICS,
      LABELGRAPHICS
   }
   
   private static enum edgeLineAttributesGML{
      POINT
   }
   
   private enum edgeGraphicsAttributesGML{
      STYLE,
      LINE,
      FILL,
      ARROW
   }
   
   private enum edgeLabelGraphicsAttributesGML{
      TEXT,
      COLOR,
      FONTSIZE,
      FONTSTYLE,
      FONTNAME
   }
   
   public GMLParser(StringBuilder text, int index){
      vGmlText = text;
      vCurrentIndex = index;
   }
   
   /**
    * Finds the next word (delimited by spaces or "s as appropriate) and 
    * increments the current index variable to one space after the next word.
    * @return String - the next word
    */
   public String mGetNextWord(){
      while(vGmlText.charAt(vCurrentIndex)== ' ' || vGmlText.charAt(vCurrentIndex) == '\t'){
         vCurrentIndex++;
      }
      Boolean bUseTab = false;
      int nextSpaceLoc = vGmlText.indexOf(" ", vCurrentIndex);
      int nextTabLoc = vGmlText.indexOf("\t", vCurrentIndex);
      if(nextTabLoc < nextSpaceLoc && (nextTabLoc != -1))
      {
         bUseTab = true;
      }
      String word = "";
      if(bUseTab){
         word = vGmlText.substring(vCurrentIndex, nextTabLoc);
      }
      else{
         word = vGmlText.substring(vCurrentIndex, nextSpaceLoc);
      }

      if(word.startsWith("\"")){
         //if the word starts with " then find the corresponding closing "
         //and take the text from in between those quotes
         nextSpaceLoc = vGmlText.indexOf("\"", vCurrentIndex+1) + 1;
         if(bUseTab){
            word = vGmlText.substring(vCurrentIndex + 1, nextSpaceLoc -1);
         }
         else{
            word = vGmlText.substring(vCurrentIndex + 1, nextSpaceLoc - 2);
         }
         
      }
      vCurrentIndex = nextSpaceLoc+1;
      return word;
   }
   
   private void mGetEdgePointAttributes(Edge edge, Stack<String> graphLoc){
      if(graphLoc.peek() == "edgePoint"){
         String nextAttribute = "";
         while(!nextAttribute.equals("]")){
            nextAttribute = mGetNextWord();
            
            //We are not going to read in the x and y coordinates here, out application
            //will draw them.
         }
         graphLoc.pop();
      }
   }
   
   private void mGetEdgeLineAttributes(Edge edge, Stack<String> graphLoc) throws CannotLoadGraph{
      if(graphLoc.peek() == "edgeLine"){
         String nextAttribute = "";
         while(!nextAttribute.equals("]")){
            nextAttribute = mGetNextWord();
            try{
               switch(edgeLineAttributesGML.valueOf(nextAttribute.toUpperCase())){
                  case POINT:
                     graphLoc.push("edgePoint");
                     if(!mGetNextWord().equals("[")){
                        throw new CannotLoadGraph("Invalid syntax");
                     }
                     else{
                        mGetEdgePointAttributes(edge, graphLoc);
                     }
                     break;
                  default:   
               }
            }
            catch(Exception e){
               
            }
         }
         graphLoc.pop();
      }
   }
   
   /**
    * Gets the style and color attributes for the edge from the GML file and puts them in the graph
    * @param edge - the edge to populate
    * @param graphLoc - the current stack location of the gml file (mostly for debugging)
    */
   private void mGetEdgeGraphicsAttributesGML(Edge edge, Stack<String> graphLoc) throws CannotLoadGraph{
      if(graphLoc.peek() == "edgeGraphics"){
         String nextAttribute = "";
         while(!nextAttribute.equals("]")){
            nextAttribute = mGetNextWord();
            try{
               switch(edgeGraphicsAttributesGML.valueOf(nextAttribute.toUpperCase())){
                  case STYLE:
                     //dotted, dashed, or solid
                     try{
                        edge.mSetEdgeStyle(EdgeStyle.valueOf(mGetNextWord().toUpperCase()));
                     }
                     catch(IllegalArgumentException e){
                        edge.mSetEdgeStyle(EdgeStyle.SOLID);
                     }
                     break;
                  case LINE:
                     graphLoc.push("edgeLine");
                     mGetEdgeLineAttributes(edge, graphLoc);
                     break;
                  case FILL:
                     //GML supports colors in the form of "#RRGGBB".
                     //TODO: write code to interpret color formats like that into 3 values from 1-255.
                     break;
                  case ARROW:
                     String direction = mGetNextWord();
                     if(direction == "last"){
                        edge.mSetDirection(Direction.ENDDIRECTION);
                     }
                     else if(direction == "first"){
                        edge.mSetDirection(Direction.STARTDIRECTION);
                     }
                     else if(direction == "both"){
                        edge.mSetDirection(Direction.DOUBLEDIRECTION);
                     }
                     else{
                        edge.mSetDirection(Direction.NODIRECTION);
                     }
                  default:
               }
            }
            catch (Exception e){
               
            }
         }
         graphLoc.pop();
      }
      else{
         throw new CannotLoadGraph("Parse Error - Cannot load edge graphic data");
      }
   }
   
   /**
    * Populates the edge with the appearance data for the edge label based on GML input string
    * @param edge - the edge to populate
    * @param graphLoc - the current stack location for the gml (mostly for debugging)
    * @param text - the text we are parsing to populate the graph
    * @param currentIndex - the current index in the text.
    */
   private void mGetEdgeLabelGraphicsAttributesGML(Edge edge, Stack<String> graphLoc) throws CannotLoadGraph{
      if(graphLoc.peek() == "edgeLabelGraphics"){
         String nextAttribute = "";
         while(!nextAttribute.equals("]")){
            nextAttribute = mGetNextWord();
            try{
               switch(edgeLabelGraphicsAttributesGML.valueOf(nextAttribute.toUpperCase())){
                  case TEXT:
                     edge.mSetLabel(mGetNextWord());
                     break;
                  case COLOR:
                     //TODO: convert #RRGGBB style into 3 ints ranging from 1-255.
                  case FONTSIZE:
                     //TODO: impelment font stuff for edges (not yet done in drawing either).
                  case FONTSTYLE:
                  case FONTNAME:
                  default:
               }
            }
            catch(Exception e){
               
            }
         }
         graphLoc.pop();
      }
      else{
         throw new CannotLoadGraph("Parse Error - Cannot load edge label graphic data");
      }
   }
   
   /**
    * Creates a new edge for a graph during GML import based on the GML file.
    * @param g - the graph object being populated
    * @param graphLoc - the current stack depth of the gml file (mostly for debugging)
    * @param text - the text of the file we are parsing
    * @param currentIndex - the current index in the text
    * @return - the edge we are populating for the graph
    * @throws CannotLoadGraph 
    */
   private Edge mGetEdgeAttributesGML(Graph g, Stack<String> graphLoc) throws CannotLoadGraph{
      //initialize a new edge to all null values.
      Edge edge = new Edge(" ", null, null, null, null, null, true);
      if(graphLoc.peek() == "edge"){
         String nextAttribute = "";
         while(!nextAttribute.equals("]")){
            nextAttribute = mGetNextWord();
            try{
               switch(edgeAttributesGML.valueOf(nextAttribute.toUpperCase())){
                  case SOURCE:
                     edge.mSetStartNode(g.mGetNodeById(mGetNextWord()));
                     break;
                  case TARGET:
                     edge.mSetEndNode(g.mGetNodeById(mGetNextWord()));
                     break;
                  case LABEL:
                     edge.mSetLabel(mGetNextWord());
                     break;
                  case GRAPHICS:
                     if(!mGetNextWord().equals("[")){
                        throw new CannotLoadGraph("Parse Error - Invalid GML syntax. Missing '['");
                     }
                     else{
                        graphLoc.push("edgeGraphics");
                        mGetEdgeGraphicsAttributesGML(edge, graphLoc);
                     }
                     break;
                  case LABELGRAPHICS:
                     if(!mGetNextWord().equals("[")){
                        throw new CannotLoadGraph("Parse Error - Invalid GML syntax. Missing '['");
                     }
                     else{
                        graphLoc.push("edgeLabelGraphics");
                        mGetEdgeLabelGraphicsAttributesGML(edge, graphLoc);
                     }
                     break;
                  default:
                     break;
               }
            }
            catch(Exception e){
               
            }
         }
         graphLoc.pop();
      }
      else{
         throw new CannotLoadGraph("Parse Error - Cannot load edge data");
      }
      return edge;
   }
   
   /**
    * Updates the node's label attributes based on text found in the gml file
    * @param node - the node to update
    * @param graphLoc - the current stack location of the GML file
    * @param text - the text to parse
    * @param currentIndex - the current index in the text to parse
    * @throws CannotLoadGraph
    */
   private void mGetNodeLabelGraphicsAttributesGML(Node node, Stack<String> graphLoc) throws CannotLoadGraph{
      if(graphLoc.peek() == "nodeLabelGraphics"){
         String nextAttribute = "";
         //because of the way fonts seem to work in java we will keep track of the font data separately
         //so that we can create a font at the end of the function and assign that to the node.
         int fontSize = 10;
         int fontStyle = Font.PLAIN;
         String fontName = "Monospaced";
         while(!nextAttribute.equals("]")){
            nextAttribute = mGetNextWord();
            try{
               switch(nodeLabelGraphicsAttributesGML.valueOf(nextAttribute.toUpperCase())){
                  case TEXT:
                     node.mSetLabel(mGetNextWord());
                     break;
                  case FONTSIZE:
                     try{
                        fontSize = Integer.parseInt(mGetNextWord());
                     }
                     catch(NumberFormatException e){
                        throw new CannotLoadGraph("Parse Error - Failed to parse node label font size");
                     }
                     break;
                  case FONTSTYLE:
                     String style = mGetNextWord();
                     if(style.equals("plain")){
                        fontStyle = Font.PLAIN;
                     }
                     else if(style.equals("italic")){
                        fontStyle = Font.ITALIC;
                     }
                     else if(style.equals("bold")){
                        fontStyle = Font.BOLD;
                     }
                     break;
                  case FONTNAME:
                     fontName = mGetNextWord();
                     break;
                  case COLOR:
                     //TODO: write code to parse the color from #RRGGBB format
                  default:
               }
            }
            catch(Exception e){
               
            }
         }
         Font font = new Font(fontName, fontStyle, fontSize);
         node.mSetFont(font);
         graphLoc.pop();
      }
      else{
         throw new CannotLoadGraph("Parse Error- Cannot load node label graphics");
      }
   }
   
   /**
    * Updates a node with the graphics attributes found in the GML syntax for that node
    * @param node - the node to update
    * @param graphLoc - the current stack position of that node in the gml
    * @param text - the text containing the gml
    * @param currentIndex - the current index of the text
    * @throws CannotLoadGraph
    */
   private void mGetNodeGraphicsAttributesGML(Node node, Stack<String> graphLoc) throws CannotLoadGraph{
      if(graphLoc.peek() == "nodeGraphics"){
         String nextAttribute = "";
         while(!nextAttribute.equals("]")){
            nextAttribute = mGetNextWord();
            try{
               switch(nodeGraphicsAttributesGML.valueOf(nextAttribute.toUpperCase())){
                  case W:
                     node.mSetWidth(Integer.parseInt(mGetNextWord()));
                     break;
                  case H:
                     node.mSetHeight(Integer.parseInt(mGetNextWord()));
                     break;
                  case TYPE:
                     //KMW Note: square is not a valid shape for GML, but it will be supported by
                     //          rectangle anyway, so it's no big deal.
                     String type = mGetNextWord();
                     if(type == "oval"){
                        node.mSetShape("OVAL");
                     }
                     else if(type.equals("circle")){
                        node.mSetShape("CIRCLE");
                     }
                     else if(type.equals("rectangle")){
                        node.mSetShape("RECTANGLE");
                     }
                     else if(type.equals("triangle")){
                        node.mSetShape("TRIANGLE");
                     }
                     else if(type.equals("square")){
                        node.mSetShape("SQUARE");
                     }
                     else{
                        //default to circle for node shapes we don't support.
                        node.mSetShape("CIRCLE");
                     }
                     break;
                  case FILL:
                     //TODO: write code to parse color data out of #RRGGBB format
                     break;
                  case OUTLINE:
                     //TOOD: write code to parse color data out of #RRGGBB format
                  case OUTLINESTYLE:
                     //TODO: write code to supporw drawing different outline styles
                     //      (dotted, dashed, solid) and then write code to parse this 
                     //      from GML.
                  default:
               }
            }
            catch (Exception e){
               
            }
         }
         graphLoc.pop();
      }
      else{
         throw new CannotLoadGraph("Parse Error- failed to load node graphics data");
      }
   }
   
   /**
    * Updates a graph's node based on the attributes it finds in the GML text.
    * @param g - The graph we are building
    * @param graphLoc - the current stack depth of the gml file (mostly for debugging purposes)
    * @param text - the text of the file we are parsing
    * @param currentIndex - the current index in the text
    */
   private Node mGetNodeAttributesGML(Graph g, Stack<String> graphLoc) throws CannotLoadGraph{
      //Default to null values (constructor should fill in default values where necessary)
      Node node = new Node(" ", null, null, null);
      if(graphLoc.peek() == "node"){
         String nextAttribute = "";
         while(!nextAttribute.equals("]")){
            nextAttribute = mGetNextWord();
            //KMW Note: this additional try/catch is designed to allow non-attribute values
            //          to pass un-noticed through the switch statement. We don't care if the
            //          catch is tripped.
            try{
               switch(nodeAttributesGML.valueOf(nextAttribute.toUpperCase())){
                  case ID:
                  {
                     node.mSetID(mGetNextWord());
                     break;
                  }
                  case LABEL:
                  {
                     node.mSetLabel(mGetNextWord());
                     break;
                  }
                  case NAME:
                  {
                     node.mSetLabel(mGetNextWord());
                     break;
                  }
                  case LABELGRAPHICS:
                  {
                     if(!mGetNextWord().equals("[")){
                        throw new CannotLoadGraph("Parse Error - Invalid GML syntax. Missing '['");
                     }
                     else{
                        graphLoc.push("nodeLabelGraphics");
                        mGetNodeLabelGraphicsAttributesGML(node, graphLoc);
                     }
                     break;
                  }
                  case GRAPHICS:
                  {
                     if(!mGetNextWord().equals("[")){
                        throw new CannotLoadGraph("Parse Error - Invalid GML syntax. Missing '['");
                     }
                     else{
                        graphLoc.push("nodeGraphics");
                        mGetNodeGraphicsAttributesGML(node, graphLoc);
                     }
                     break;
                  }
                  default:
                     
               }
            }
            catch(Exception e){
               
            }
         }
         graphLoc.pop();
      }
      else{
         throw new CannotLoadGraph("Parse Error - Cannot load node values");
      }
      return node;
   }
   
   /**
    * Updates the selected graph based on the attributes it finds in the GML file.
    * @param g - The graph we are building
    * @param graphLoc - the current stack depth of the gml file (mostly for debugging purposes)
    * @param text - the text of the file we are parsing
    * @param currentIndex - the current index in the text
    */
   public void mGetGraphAttributesGML(Graph g, Stack<String> graphLoc) throws CannotLoadGraph{
      String nextAttribute = "";
      try{
         while(!nextAttribute.equals("]")){
            nextAttribute = mGetNextWord();
            //KMW Note: this additional try/catch is designed to allow non-attribute values
            //          to pass un-noticed through the switch statement. We don't care if the
            //          catch is tripped.
            try{
               switch(graphAttributesGML.valueOf(nextAttribute.toUpperCase())){
                  case LABEL:
                     g.mSetTitle(mGetNextWord());
                     break;
                  case EDGE:
                     if(!mGetNextWord().equals("[")){
                        throw new CannotLoadGraph("Parse Error - Invalid GML Syntax. Missing '['");
                     }
                     else{
                        graphLoc.push("edge");
                        g.mAddEdge(mGetEdgeAttributesGML(g, graphLoc));
                     }
                     break;
                  case NODE:
                     if(!mGetNextWord().equals("[")){
                        throw new CannotLoadGraph("Parse Error - Invalid GML Syntax. Missing '['");
                     }
                     else{
                        graphLoc.push("node");
                        g.mAddNode(mGetNodeAttributesGML(g, graphLoc));
                     }
                     break;
                  case HIERARCHIC:
                     int hierarchic = Integer.parseInt(mGetNextWord());
                     if(hierarchic > 0){
                        throw new CannotLoadGraph("Parse Error - Hierarchic graphs are not supported by Autograph.");
                     }
                     break;
                  default:
               }
            }
            catch(Exception e){
               
            }
         }
      }
      catch(Exception e){
         //TODO: report the failure to the user with the most recent exception's text..
         e.printStackTrace();
      }
   }
}
