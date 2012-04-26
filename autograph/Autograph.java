package autograph;


import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import autograph.ui.mainWindow;
import autograph.ui.ButtonTabComponent;
import javax.swing.UIManager.*;
import javax.swing.UIManager;

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
      try {
         for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
              /*if ("Nimbus".equals(info.getName())) {
                  UIManager.setLookAndFeel(info.getClassName());
                  break;
              }*/
          }
         // Sets Look and Feel to defualt for OS
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) {
         System.out.println("OH NO! switching to default look and feel. The following are available on your computer:");
          for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            System.out.println(info.getName());
          }
      }
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Autograph  window = new Autograph(
						"Autograph");
            window.MainWindowComponent.setVisible(true);
			}
		});
	}
}


