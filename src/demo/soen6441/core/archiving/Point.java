package demo.soen6441.core.archiving;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import com.soen6441.core.IArchive;


public class Point implements IArchive {
	
	/*
	 * Properties
	 */
	
	private int x;
	private int y;
	
	/*
	 * Constructors
	 */
	
	public Point() {
		super();
	}
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/*
	 * Archiving
	 */

	public class NameForArchiving{
		public static final String Class = "Point";
		private static final String X = "x";
		private static final String Y = "y";
	}

	@Override
	public void decode(Element element) {
		x = Integer.parseInt(element.attributeValue(NameForArchiving.X));
		y = Integer.parseInt(element.attributeValue(NameForArchiving.Y));
	}
	
	@Override
	public Element encode() {
		Element element = new DefaultElement(NameForArchiving.Class); 
		element.addAttribute(NameForArchiving.X, Integer.toString(x));
		element.addAttribute(NameForArchiving.Y, Integer.toString(y));
		return element;
	}
	
	/*
	 * Getters & Setters
	 */

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "{" + "x: " + x + ", " + "y: " + y + "}";
	}
	
	
}
