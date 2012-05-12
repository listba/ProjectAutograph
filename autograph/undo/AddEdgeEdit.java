package autograph.undo;

import autograph.Graph;
import autograph.Edge;
import java.util.ArrayList;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class AddEdgeEdit extends AbstractUndoableEdit {
   
   private Edge vAddedEdge;
   private Edge vTwin;
   private Edge.PairPosition vTwinPP;
   private ArrayList<Edge> vEdgeList;

   //throws CannotUndoException
   public AddEdgeEdit(Edge addedEdge, ArrayList<Edge> edgeList) {
      vAddedEdge = addedEdge;
      vTwin = addedEdge.mGetTwin();
      vEdgeList = edgeList;
   }

   public void redo() throws CannotRedoException {
      super.redo();
      vEdgeList.add( vAddedEdge );
      if (vTwin != vAddedEdge) {
         vTwin.mSetTwin(vAddedEdge);
         vTwin.vPairPosition = vTwinPP;
      }
   }

   public void undo() throws CannotUndoException {
      super.undo();
      vEdgeList.remove( vEdgeList.indexOf(vAddedEdge) );
      if (vTwin != null) {
         vTwinPP = vTwin.vPairPosition;
         vTwin.mSetTwin(null);
         vTwin.vPairPosition = Edge.PairPosition.UNPAIRED;
      }
   }
}