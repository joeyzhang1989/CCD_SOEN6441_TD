package com.soen6441.core.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import com.soen6441.core.IArchive;

/**
 * 
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class Log implements IArchive {
	
	/*
	 * Mark - Constructors
	 */
	
	public Log() {
		this(CATEGORY_GAME);
	}
	
	/**
	 * Constructor for Log.
	 * @param category String
	 */
	public Log(String category) {
		this.setCategory(category);
		this.date = new Date();
		this.identity = 0;
		this.message = "";
		this.value = "";
	}
	
	/*
	 * Mark - Build - Methods
	 */
	
	/**
	 * Method identity.
	 * @param identity long
	 * @return Log
	 */
	public Log identity(long identity) {
		this.setIdentity(identity);
		return this;
	}
	
	/**
	 * Method message.
	 * @param message String
	 * @return Log
	 */
	public Log message(String message) {
		this.setMessage(message);
		return this;
	}
	
	/**
	 * Method value.
	 * @param value String
	 * @return Log
	 */
	public Log value(String value) {
		this.setValue(value);
		return this;
	}
	
	/*
	 * Mark - Basic - Properties
	 */
	 
	private Date date;
	private String category;
	private long identity;
	private String message;
	private String value;
	
	public static final String CATEGORY_TOWER 	= "Tower";
	public static final String CATEGORY_WAVE 	= "Wave ";
	public static final String CATEGORY_GAME 	= "Game ";
	public static final String CATEGORY_MAP 	= "Map";
	
	/*
	 * Mark - Basic - Getters & Setters
	 */
	 
	/**
	 * Method getCategory.
	 * @return String
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Method setCategory.
	 * @param category String
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Method getIdentity.
	 * @return long
	 */
	public long getIdentity() {
		return identity;
	}

	/**
	 * Method setIdentity.
	 * @param identity long
	 */
	public void setIdentity(long identity) {
		this.identity = identity;
	}

	/**
	 * Method getMessage.
	 * @return String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Method setMessage.
	 * @param message String
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Method getValue.
	 * @return String
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Method setValue.
	 * @param value String
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Method getDate.
	 * @return Date
	 */
	public Date getDate() {
		return date;
	}
	
	/*
	 * Mark - Display - Methods
	 */
	 
	private static DateFormat _DATE_FORMATE = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * Method toString.
	 * @return String
	 */
	public String toString(){
		return _DATE_FORMATE.format(this.date) + this.category + " " + Long.toString(identity) + " " + message + " " + value; 
	}
	
	/*
	 * Mark - Archive - Methods
	 */

	/**
	 */
	public class NameForArchiving{
		public static final String Class 		= "Log";
		private static final String Date 		= "date";
		private static final String Category 	= "category";
		private static final String Identity 	= "identity";
		private static final String Message 	= "message";
		private static final String Value 		= "value";
	}
	
	/**
	 * Method decode.
	 * @param element Element
	 * @see com.soen6441.core.IArchive#decode(Element)
	 */
	@Override
	public void decode(Element element) {
		try {
			this.date = _DATE_FORMATE.parse(element.attributeValue(NameForArchiving.Date));
			this.category = element.attributeValue(NameForArchiving.Category);
			this.identity = Long.valueOf(element.attributeValue(NameForArchiving.Identity));
			this.message = element.attributeValue(NameForArchiving.Message);
			this.value = element.attributeValue(NameForArchiving.Value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method encode.
	 * @return Element
	 * @see com.soen6441.core.IArchive#encode()
	 */
	@Override
	public Element encode() {
		Element element = new DefaultElement(NameForArchiving.Class);
		element.addAttribute(NameForArchiving.Date, _DATE_FORMATE.format(this.date));
		element.addAttribute(NameForArchiving.Category, this.category);
		element.addAttribute(NameForArchiving.Identity, Long.toString(this.identity));
		element.addAttribute(NameForArchiving.Message, this.message);
		element.addAttribute(NameForArchiving.Value, this.value);
		return element;
	}
}
