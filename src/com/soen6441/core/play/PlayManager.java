package com.soen6441.core.play;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> master
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

<<<<<<< HEAD
import com.soen6441.core.critter.CritterWave;
=======
import com.soen6441.core.critter.Critter;
import com.soen6441.core.critter.CritterWave;
import com.soen6441.core.critter.CritterWave.NameForArchiving;
>>>>>>> master


/**
 * The class PalyManager will handle the task of saving the gamePlay in XML file and reading the XML file to generate gamePlay;
 * 
 * @author Mohammad Ali
 * @author Chenglong Zhang
 * @version $Revision: 1.0 $
 */
public class PlayManager {

	/**
	 * The method save will save the gamePlay in the specified file
	 * 
	 * @param file The name of the File where to save .
	 * @param play A play object
	 */

	public void save(File file, Play play) {
		play.getMap().getPathManager().generatePathsFromRoadItems();

		// Creating the xml file for map storage
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("xml");

		//adding Play element in the map xml file by calling play.encode();
		rootElement.add(play.encode());

		// lets write to a file
		XMLWriter writer = null;
		try {
			writer = new XMLWriter(new FileWriter(file));
			writer.write(document);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	
	/**
	 * The method read will read the specified file and generate a Play object.
	 * This method will use Xpath to navigate through MapXml file.
	 * Requires jaxen 1.1-beta-6.jar located in Dom4j lib folder.
	 * 
	 * @param file The name of the File form which to read 
     * @return Play  
     */
	public Play read(File file) {

		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Element rootElement = document.getRootElement();
		Element playElement = (Element) rootElement.selectSingleNode(Play.NameForArchiving.Class);

		Play play = Play.currentPlay();
		play.setLife(10);
		play.decode(playElement);

		play.getMap().getPathManager().generateRoadItemsFromPaths();
		play.setCritterWaves(readDefaultCritterWaves());
		
		return play;

	}
	
	private List<CritterWave> readDefaultCritterWaves() {
<<<<<<< HEAD
		
=======
		List<CritterWave> critterWaves = new ArrayList<CritterWave>();
		
		String filePath = System.getProperty("user.dir")+ "/data/" + "critter_waves.xml";
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new File(filePath));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element rootElement = document.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> critterWaveElements = rootElement.elements(CritterWave.NameForArchiving.Class);

		for (Element critterWaveElement : critterWaveElements) {
			CritterWave critterWave = new CritterWave();
			critterWave.decode(critterWaveElement);

			critterWaves.add(critterWave);
		}	
		
		return critterWaves;
>>>>>>> master
	}
	
}
