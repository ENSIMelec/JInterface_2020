import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;
import java.util.Collections;

public class CenterActionPanel extends JPanel{
	JList<String> actionList;
	DefaultListModel<String> actionModel;
	JScrollPane listScrollPane;
	File racine = new File("/home/pi/robot_asserv_lidar/fileaction/");
	//File racine = new File("C:/Users/abdou/OneDrive/Bureau/fileaction/");
	
	public CenterActionPanel() {
		Main.cap = this;
		this.setBackground(Main.background_color);
		this.setForeground(Main.background_color);
		
		actionModel = new DefaultListModel<String>();
		actionList = new JList<String>(actionModel);
		actionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actionList.setBackground(new Color(60, 60, 60));
		actionList.setFont(new Font("Courier", Font.BOLD, 35));
		actionList.setForeground(Main.text_color);
		listScrollPane = new JScrollPane(actionList);
		listScrollPane.setPreferredSize(new Dimension(600, 350));

		listScrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,40));
		listScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(60,0));
		
		//File folder = new File("C:/Users/abdou/OneDrive/Bureau/fileaction");
		File folder = new File("/home/pi/robot_asserv_lidar/fileaction");
		//TODO Changer le repertoire
		ArrayList<File> listFile = new ArrayList<File>();
		getAllFiles(folder, listFile);
		
		Collections.sort(listFile);
		
		for(File file : listFile) {
			actionModel.addElement(racine.toURI().relativize(file.toURI()).getPath().replaceFirst("[.][^.]+$", ""));
		}
		this.add(listScrollPane);
	}
	
	public String getAction() {
		return actionList.getSelectedValue();
	}
	
	private void getAllFiles(File path, ArrayList<File> liste) {
		for(File file : path.listFiles()) {
			if(file.isDirectory()) {
				//repertoire
				getAllFiles(file, liste);
			}else {
				//fichier
				liste.add(file);
			}
		}
	}
	
}
