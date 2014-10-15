package demo.soen6441.core.archiving;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import com.soen6441.core.IArchive;

import demo.soen6441.core.archiving.Point.NameForArchiving;

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
	
	public Circle(Point origin, int radius) {
		super();
		this.origin = origin;
		this.radius = radius;
	}


	/*
	 * Archiving
	 */
	
	public class NameForArchiving{
		public static final String Class = "Circle";
		private static final String Origin = "origin";
		private static final String Radius = "radius";
	}
	
	@Override
	public void decode(Element element){
		Element originElement = element.element(NameForArchiving.Origin);
		Point origin = new Point();
		origin.decode(originElement.element(Point.NameForArchiving.Class));
		this.origin = origin;
		
		Element radiusElement = element.element(NameForArchiving.Radius);
		this.radius = Integer.parseInt(radiusElement.getText());
	}

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
	
	public Point getOrigin() {
		return origin;
	}
	public void setOrigin(Point origin) {
		this.origin = origin;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "{" + "origin: " + origin.toString() + ", " + "radius: " + radius + "}";
	}
	
}
