package demo.soen6441.core.archiving;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import com.soen6441.core.IArchive;


/**
 * @author Zhe Zhao
 */
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
	
	/**
	 * Constructor for Point.
	 * @param x int
	 * @param y int
	 */
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/*
	 * Archiving
	 */

	/**
	 */
	public class NameForArchiving{
		public static final String Class = "Point";
		private static final String X = "x";
		private static final String Y = "y";
	}

	/**
	 * Method decode.
	 * @param element Element
	 * @see com.soen6441.core.IArchive#decode(Element)
	 */
	@Override
	public void decode(Element element) {
		x = Integer.parseInt(element.attributeValue(NameForArchiving.X));
		y = Integer.parseInt(element.attributeValue(NameForArchiving.Y));
	}
	
	/**
	 * Method encode.
	 * @return Element
	 * @see com.soen6441.core.IArchive#encode()
	 */
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

	/**
	 * Method getX.
	 * @return int
	 */
	public int getX() {
		return x;
	}
	/**
	 * Method setX.
	 * @param x int
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Method getY.
	 * @return int
	 */
	public int getY() {
		return y;
	}
	/**
	 * Method setY.
	 * @param y int
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Method toString.
	 * @return String
	 */
	@Override
	public String toString() {
		return "{" + "x: " + x + ", " + "y: " + y + "}";
	}
	
	
}
