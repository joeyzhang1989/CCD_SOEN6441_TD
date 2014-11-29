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
 */
public class Log implements IArchive {
	
	/*
	 * Mark - Constructors
	 */
	
	public Log() {
		this(CATEGORY_GAME);
	}
	
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
	
	public Log identity(long identity) {
		this.setIdentity(identity);
		return this;
	}
	
	public Log message(String message) {
		this.setMessage(message);
		return this;
	}
	
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
	 
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getIdentity() {
		return identity;
	}

	public void setIdentity(long identity) {
		this.identity = identity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}
	
	/*
	 * Mark - Display - Methods
	 */
	 
	private static DateFormat _DATE_FORMATE = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public String toString(){
		return _DATE_FORMATE.format(this.date) + this.category + " " + Long.toString(identity) + " " + message + " " + value; 
	}
	
	/*
	 * Mark - Archive - Methods
	 */

	public class NameForArchiving{
		public static final String Class 		= "Log";
		private static final String Date 		= "date";
		private static final String Category 	= "category";
		private static final String Identity 	= "identity";
		private static final String Message 	= "message";
		private static final String Value 		= "value";
	}
	
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

	
}
