/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autograph.ui;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileFilter;

import autograph.Graph;
import autograph.GraphHelper;
import autograph.GraphPanel;
import autograph.Utils;

public class FilePickerDialog extends javax.swing.JDialog {

	/**
	 * Creates new form FilePickerDialog
	 */
	public FilePickerDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		FilePicker = new javax.swing.JFileChooser();
		//set it so we can only save/open files of the following extensions. (add filters for image types in save dialog only)
		FilePicker.addChoosableFileFilter(new XmlFilter());
		FilePicker.addChoosableFileFilter(new TxtFilter());
		FilePicker.addChoosableFileFilter(new AgFilter());
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(FilePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(FilePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE))
				);

		pack();
	}// </editor-fold>

	/**
	 * Opens the file picker dialog, and then validates the file chosen and opens a new tab containing the valid graph.
	 * @param MainWindowTabbedPane - the tabbed pane to put the new tab into.
	 */
	public void mOpenFilePickerDialog(JTabbedPane MainWindowTabbedPane){
	   
		int returnVal = FilePicker.showOpenDialog(this.getParent());
		if(returnVal == JFileChooser.APPROVE_OPTION){
			//the user has selected a file.
			String fileName = FilePicker.getSelectedFile().getName();
			if(!(fileName.endsWith(".ag") || fileName.endsWith(".xml")|| fileName.endsWith(".txt"))){
				//TODO: Implement error dialog for this scenario.
				System.out.println(FilePicker.getSelectedFile().getName() + " is not a valid file type.");
			}
			else
			{
				//we know we have a valid file type, so we are going to create a new graph object, and load it
				//into a new tab
				Graph loadedGraph;
				File selectedFile = FilePicker.getSelectedFile();
				if(fileName.endsWith(".ag")){
					//load from save file
					loadedGraph = GraphHelper.mLoadGraphObject(selectedFile.getPath());
				}
				else if(fileName.endsWith(".xml")){
					//load from xml
					loadedGraph = GraphHelper.mImportGraphFromXML(selectedFile.getPath());
				}
				else
				{
					//load from gml
					loadedGraph = GraphHelper.mImportGraphFromGML(selectedFile.getPath(), null);
				}
				
				GraphPanel newGraphPanel = new GraphPanel(loadedGraph);
				newGraphPanel.mSetFilePath(selectedFile.getPath());
				JScrollPane newPane = new javax.swing.JScrollPane(newGraphPanel);
				newPane.setBorder(null);

				int imageWidth = GraphHelper.mGetPreferredImageWidth(newGraphPanel.mGetGraph());
				newGraphPanel.setPreferredSize(new Dimension(imageWidth, imageWidth));  
				GraphHelper.mDrawForceDirectedGraph(newGraphPanel);
				
				//giving the tab the file's name.
				int extensionStart = fileName.lastIndexOf('.');
				String newTitle =fileName.substring(0, extensionStart);
				MainWindowTabbedPane.addTab(newTitle, newPane);
				MainWindowTabbedPane.setSelectedIndex(MainWindowTabbedPane.indexOfComponent(newPane));
				MainWindowTabbedPane.setTabComponentAt(MainWindowTabbedPane.indexOfComponent(newPane), new ButtonTabComponent(newTitle, MainWindowTabbedPane));
				
			}
		}
	}

	public void mOpenSaveDialog(JTabbedPane MainWindowTabbedPane){
		//allow saving of these image types.
		FilePicker.addChoosableFileFilter(new BmpFilter());
		FilePicker.addChoosableFileFilter(new GifFilter());
		FilePicker.addChoosableFileFilter(new PngFilter());
		FilePicker.addChoosableFileFilter(new JpgFilter());
		//remove the all files option from the save dialog.
		FilePicker.removeChoosableFileFilter(FilePicker.getChoosableFileFilters()[0]);
		
		//display the FilePicker dialog with the save option rather than the open option.
		int returnValue = FilePicker.showSaveDialog(this.getParent());
		if(returnValue == JFileChooser.APPROVE_OPTION){
			//get the graph panel containing the graph we want to save.
			int selectedIndex = MainWindowTabbedPane.getSelectedIndex();
			GraphPanel currentPanel = (GraphPanel)(((JScrollPane)MainWindowTabbedPane.getComponentAt(selectedIndex)).getViewport().getView());
			
			//if the save button is clicked and a file name has been entered
			//retrieve the file path to the file (will include file name in path)
			String filePath = FilePicker.getSelectedFile().getPath();
			
			//we are also going to retrieve and update the fileName as necessary.
			//This way we can easily update the tab name to reflect the file name.
			String fileName = FilePicker.getSelectedFile().getName();
			if(filePath != null && !filePath.isEmpty()){
				//make sure it is a valid save type
				if(FilePicker.getFileFilter().getDescription() == "gif"){
					//now we want to save using gif format
					if(!filePath.endsWith(".gif")){
						//make sure the file has the correct extension
						filePath = filePath + ".gif";
						fileName = fileName + ".gif";
					}
					GraphHelper.mSaveGIF(currentPanel, filePath);
				}
				else if(FilePicker.getFileFilter().getDescription() == "jpg"){
					//we want to save using jpg format
					if(!filePath.endsWith(".jpg")){
						//make sure the file has the correct extension
						filePath = filePath +".jpg";
						fileName = fileName + ".jpg";
					}
					GraphHelper.mSaveJPG(currentPanel, filePath);
				}
				else if(FilePicker.getFileFilter().getDescription() == "bmp"){
					//we want to save using the bmp format
					if(!filePath.endsWith(".bmp")){
						//make sure the file has the correct extension
						filePath = filePath + ".bmp";
						fileName = fileName + ".bmp";
					}
					GraphHelper.mSaveBMP(currentPanel, filePath);
				}
				else if(FilePicker.getFileFilter().getDescription() == "png"){
					//we want to save using the png format
					if(!filePath.endsWith(".png")){
						//make sure the file has the correct extension
						filePath = filePath + ".png";
						fileName = fileName + ".png";
					}
					GraphHelper.mSavePNG(currentPanel, filePath);
				}
				else if(FilePicker.getFileFilter().getDescription() == "xml"){
					//we want to save the graph as xml
					if(!filePath.endsWith(".xml")){
						//make sure the file has the correct extension
						filePath = filePath + ".xml";
						fileName = fileName + ".xml";
					}
					GraphHelper.mExportGraphToXML(currentPanel.mGetGraph(), filePath);
				}
				else if(FilePicker.getFileFilter().getDescription() == "txt"){
					//we want to save the graph using GML syntax in a txt file
					if(!filePath.endsWith(".txt")){
						//make sure the file has the correct extension
						filePath = filePath + ".txt";
						fileName = fileName + ".txt";
					}
					try{
						GraphHelper.mExportGraphToGML(currentPanel.mGetGraph(), filePath);
					}
					catch (Exception e){
						//TODO: implement error dialog for this case.
					}
				}
				else if(FilePicker.getFileFilter().getDescription() == "ag"){
					//we want to save a serialized version of the graph.
					if(!filePath.endsWith(".ag")){
						//make sure the file has the correct extension
						filePath = filePath + ".ag";
						fileName = fileName + ".ag";
					}
					GraphHelper.mSaveGraphObject(currentPanel.mGetGraph(), filePath);
				}
				else{
					//TODO: implement error dialog for this scenario (should never happen, but can't hurt to be prepared).
				}
				
				//update the tab name to the new file name. and update the filePath for the panel
				if(fileName != null && !fileName.isEmpty()){
				   currentPanel.mSetFilePath(filePath);
				   int extensionStart = fileName.lastIndexOf('.');
				   MainWindowTabbedPane.setTitleAt(selectedIndex, fileName.substring(0, extensionStart));
				}
			}
			else{
				//TODO: implement error dialog for this scenario.
			}
		}
		else if(returnValue == JFileChooser.ERROR_OPTION){
			System.out.println("Error occurred.");
		}
		else{
			System.out.println("Close the dialog?");
		}
	}

	// Variables declaration - do not modify
	private javax.swing.JFileChooser FilePicker;
	// End of variables declaration
}

class GifFilter extends FileFilter{
	public boolean accept(File f){
		if(f.isDirectory()){
			return true;
		}
		String extension =  Utils.getExtension(f);
		if(extension != null){
			if(extension.equals(Utils.gif)){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
	public String getDescription() {
		return "gif";
	}
}

class JpgFilter extends FileFilter{
	public boolean accept(File f){
		if(f.isDirectory()){
			return true;
		}
		String extension =  Utils.getExtension(f);
		if(extension != null){
			if(extension.equals(Utils.jpg)){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
	public String getDescription() {
		return "jpg";
	}
}

class PngFilter extends FileFilter{
	public boolean accept(File f){
		if(f.isDirectory()){
			return true;
		}
		String extension =  Utils.getExtension(f);
		if(extension != null){
			if(extension.equals(Utils.png)){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
	public String getDescription() {
		return "png";
	}
}

class BmpFilter extends FileFilter{
	public boolean accept(File f){
		if(f.isDirectory()){
			return true;
		}
		String extension = Utils.getExtension(f);
		if(extension != null){
			if(extension.equals(Utils.bmp)){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
	public String getDescription(){
		return "bmp";
	}
}

class XmlFilter extends FileFilter{
	public boolean accept(File f){
		if(f.isDirectory()){
			return true;
		}
		String extension = Utils.getExtension(f);
		if(extension != null){
			if(extension.equals(Utils.xml)){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
	public String getDescription(){
		return "xml";
	}
}

class TxtFilter extends FileFilter{
	public boolean accept(File f){
		if(f.isDirectory()){
			return true;
		}
		String extension = Utils.getExtension(f);
		if(extension != null){
			if(extension.equals(Utils.txt)){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
	public String getDescription(){
		return "txt";
	}
}

class AgFilter extends FileFilter{
	public boolean accept(File f){
		if(f.isDirectory()){
			return true;
		}
		String extension = Utils.getExtension(f);
		if(extension != null){
			if(extension.equals(Utils.ag)){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
	public String getDescription(){
		return "ag";
	}
}


