package demo.soen6441.core.archiving;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import com.soen6441.core.IArchive;

import demo.soen6441.core.archiving.Point.NameForArchiving;

/**
 *  @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class Circle implements IArchive{
	
	/*
	 * Properties
	 */
	
	private Point origin;
	private int radius;
	
	/*
	 * Constructors
	 */
	
	public Circle() {
		super();
	}
	
	/**
	 * Constructor for Circle.
	 * @param origin Point
	 * @param radius int
	 */
	public Circle(Point origin, int radius) {
		super();
		this.origin = origin;
		this.radius = radius;
	}


	/*
	 * Archiving
	 */
	
	/**
	 * @author chenglong
	 * @version $Revision: 1.0 $
	 */
	public class NameForArchiving{
		public static final String Class = "Circle";
		private static final String Origin = "origin";
		private static final String Radius = "radius";
	}
	
	/**
	 * Method decode.
	 * @param element Element
	
	 * @see com.soen6441.core.IArchive#decode(Element) */
	@Override
	public void decode(Element element){
		Element originElement = element.element(NameForArchiving.Origin);
		Point origin = new Point();
		origin.decode(originElement.element(Point.NameForArchiving.Class));
		this.origin = origin;
		
		Element radiusElement = element.element(NameForArchiving.Radius);
		this.radius = Integer.parseInt(radiusElement.getText());
	}

	/**
	 * Method encode.
	 * @return Element 
     * @see com.soen6441.core.IArchive#encode() 
     * @see com.soen6441.core.IArchive#encode()
	 */
	@Override
	public Element encode() {
		Element element = new DefaultElement(NameForArchiving.Class); 
		
		Element originElement = element.addElement(NameForArchiving.Origin);
		originElement.add(this.origin.encode());

		Element radiusElement = element.addElement(NameForArchiving.Radius);
		radiusElement.addText(Integer.toString(this.radius));
		
		return element;
	}

	
	/*
	 * Getters & Setters
	 */
	
	/**
	 * Method getOrigin.
	
	 * @return Point */
	public Point getOrigin() {
		return origin;
	}
	/**
	 * Method setOrigin.
	 * @param origin Point
	 */
	public void setOrigin(Point origin) {
		this.origin = origin;
	}
	/**
	 * Method getRadius.
	
	 * @return int */
	public int getRadius() {
		return radius;
	}
	/**
	 * Method setRadius.
	 * @param radius int
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Method toString.
	
	 * @return String */
	@Override
	public String toString() {
		return "{" + "origin: " + origin.toString() + ", " + "radius: " + radius + "}";
	}
	
}
