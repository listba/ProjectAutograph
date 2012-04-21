package autograph;


import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import autograph.ui.mainWindow;
import autograph.ui.ButtonTabComponent;

public class Autograph extends mainWindow {

	public Autograph(String title) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		for(int i = 0; i < MainWindowTabbedPane.getTabCount(); i++){
			MainWindowTabbedPane.setTabComponentAt(i, new ButtonTabComponent(MainWindowTabbedPane.getTitleAt(i), MainWindowTabbedPane));
			GraphPanel graphPanel = (GraphPanel)(((JScrollPane)MainWindowTabbedPane.getComponentAt(i)).getViewport().getView());
			int imageWidth = GraphHelper.mGetPreferredImageWidth(graphPanel.mGetGraph());

			graphPanel.setPreferredSize(new Dimension(imageWidth, imageWidth));

			// Draw Initial Graph
			if(graphPanel.mGetGraph().mGetNodeList().size() > 0){
				GraphHelper.mDrawForceDirectedGraph(graphPanel);
			}
		}
	}

	public static void main(String[] args) {


		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Autograph autograph = new Autograph(
						"Autograph");
				autograph.setVisible(true);
			}

		});
	}
}


