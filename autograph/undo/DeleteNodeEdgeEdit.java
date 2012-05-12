package autograph.undo;

import autograph.Graph;
import autograph.Node;
import autograph.Edge;
import java.util.ArrayList;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class DeleteNodeEdgeEdit extends AbstractUndoableEdit {
   
   private ArrayList<Node> vRemovedNodeList;
   private ArrayList<Node> vNodeList;
   private ArrayList<Edge> vRemovedEdgeList;
   private ArrayList<Edge> vEdgeList;
   //throws CannotUndoException
   public DeleteNodeEdgeEdit(ArrayList<Node> removedNodes, ArrayList<Node> nodeList, 
                             ArrayList<Edge> removedEdges, ArrayList<Edge> edgeList) {
      vRemovedNodeList = removedNodes;
      vNodeList = nodeList;
      vRemovedEdgeList = removedEdges;
      vEdgeList = edgeList;
   }

   public void redo() throws CannotRedoException {
      super.redo();
      for (int i = 0; i < vRemovedNodeList.size(); i++) {
         vNodeList.remove( vNodeList.indexOf( vRemovedNodeList.get(i) ) );
      }
      for (int i = 0; i < vRemovedEdgeList.size(); i++) {
         vEdgeList.remove( vEdgeList.indexOf( vRemovedEdgeList.get(i) ) );
      }
   }

   public void undo() throws CannotUndoException {
      super.undo();
      for (int i = 0; i < vRemovedNodeList.size(); i++) {
         vNodeList.add( vRemovedNodeList.get(i) );
      }
      for (int i = 0; i < vRemovedEdgeList.size(); i++) {
         vEdgeList.add( vRemovedEdgeList.get(i) );
      }
   }
}