package com.soen6441.core;

import org.dom4j.Element;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public interface IArchive {
	/**
	 * Method decode.
	 * @param element Element
	 */
	public void decode(Element element);
	/**
	 * Method encode.
	
	 * @return Element */
	public Element encode();
}
