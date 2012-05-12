package autograph.undo;

import autograph.Graph;
import autograph.Node;
import autograph.Edge;
import java.util.ArrayList;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class AddNodeEdit extends AbstractUndoableEdit {
   
   private Node vAddedNode;
   private ArrayList<Node> vNodeList;

   //throws CannotUndoException
   public AddNodeEdit(Node addedNode, ArrayList<Node> nodeList) {
      vAddedNode = addedNode;
      vNodeList = nodeList;
   }

   public void redo() throws CannotRedoException {
      super.redo();
      vNodeList.add( vAddedNode );
   }

   public void undo() throws CannotUndoException {
      super.undo();
      vNodeList.remove( vNodeList.indexOf(vAddedNode) );
   }
}