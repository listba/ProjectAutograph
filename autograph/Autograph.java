package autograph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.util.ArrayList;
import autograph.ui.mainWindow;

public class Autograph extends mainWindow {

	public Autograph(String title) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create New Graph Panel
		// And Load in graph from XML file
		//String filePath = "Graph.xml";
		//Graph graph = GraphHelper.mImportGraphFromXML(filePath);
		//GraphPanel panel = new GraphPanel(graph);
		// Write Graph to XML file
		/*
		String writeTo = "NewGraph.xml";
		GraphHelper.mExportGraphToXML(graph, writeTo);
		*/
		// Draw each tab we have.
		for(int i = 0; i < vTabs.size(); i++){
			GraphPanel graphPanel = (GraphPanel)vTabs.get(i).getViewport().getView();
			int imageWidth = GraphHelper.mGetPreferredImageWidth(graphPanel.mGetGraph());
	
			graphPanel.setPreferredSize(new Dimension(imageWidth, imageWidth));
	
			// Draw Initial Graph
			GraphHelper.mDrawForceDirectedGraph(graphPanel);
		}
		
		
		//GraphHelper.mSavePNG(panel);
		//try{
		   //GraphHelper.mExportGraphToGML(graph, "graphGML.txt", null);
		//}
		//catch(Exception e){
		   
		//}

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


