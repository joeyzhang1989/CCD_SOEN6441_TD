package com.soen6441.core;

import org.dom4j.Element;

public interface IArchive {
	public void decode(Element element);
	public Element encode();
}
