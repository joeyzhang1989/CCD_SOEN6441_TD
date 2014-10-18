package demo.soen6441.core.archiving;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author Zhe Zhao
 */
public class ArchivingDemo {
	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main(String[] args) {
		
		// archiving - encoding
		
		Circle circle = new Circle(new Point(2, 3), 6);
		
		Element element = circle.encode();

        Document document = DocumentHelper.createDocument();
        document.add(element);
        System.out.println("\n Objects -> XML");
        System.out.println(document.asXML());
        
		// unarchiving - decoding

		File file = new File("src/demo/soen6441/core/archiving/point.xml");
        SAXReader reader = new SAXReader();
        try {
			Document documentIn = reader.read(file);
			Circle circleIn = new Circle();
			circleIn.decode(documentIn.getRootElement());
	        System.out.println("\n XML -> Objects");
			System.out.println(circleIn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
}
