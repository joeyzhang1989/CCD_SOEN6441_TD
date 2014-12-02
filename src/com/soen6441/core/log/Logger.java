package com.soen6441.core.log;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import com.soen6441.core.IArchive;

/**
 * 
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class Logger implements IArchive{
	
	/*
	 * Mark - Constructors
	 */
	
	public Logger() {
		logs = new ArrayList<Log>();
	}
	
	/*
	 * Mark - Basic - Properties
	 */
	 
	private List<Log> logs;
	
	/*
	 * Mark - Basic - Methods
	 */
	 
	/**
	 * Method addLog.
	 * @param log Log
	 */
	public synchronized void addLog(Log log) {
		logs.add(log);
	}
	
	/**
	 * Method getLogs.
	 * @param category String
	 * @return List<Log>
	 */
	public synchronized List<Log> getLogs(String category) {
		List<Log> filteredLogs = new ArrayList<Log>();
		for (Log log : logs) {
			if (log.getCategory().equals(category)) {
				filteredLogs.add(log);
			}
		}
		return filteredLogs;
	}
	
	/**
	 * Method getLogs.
	 * @param category String
	 * @param message String
	 * @return List<Log>
	 */
	public synchronized List<Log> getLogs(String category, String message) {
		List<Log> filteredLogs = new ArrayList<Log>();
		for (Log log : logs) {
			if (log.getCategory().equals(category) && log.getMessage().equals(message)) {
				filteredLogs.add(log);
			}
		}
		return filteredLogs;
	}
	
	/**
	 * Method getLogs.
	 * @param category String
	 * @param identity long
	 * @return List<Log>
	 */
	public synchronized List<Log> getLogs(String category, long identity) {
		List<Log> filteredLogs = new ArrayList<Log>();
		for (Log log : logs) {
			if (log.getCategory().equals(category) && log.getIdentity() == identity) {
				filteredLogs.add(log);
			}
		}
		return filteredLogs;
	}
	
	public synchronized void clearLogs() {
		logs = new ArrayList<Log>();
	}
	
	/*
	 * Mark - Archive - Methods
	 */
	/**
	 * @ author Zhe Zhao
	 */
	public class NameForArchiving{
		public static final String Class = "Logger";
		private static final String Logs = "logs";
	}
	 
	/**
	 * Method decode.
	 * @param element Element
	 * @see com.soen6441.core.IArchive#decode(Element)
	 */
	@Override
	public void decode(Element element) {
		Element logsElement = element.element(NameForArchiving.Logs);
		@SuppressWarnings("unchecked")
		List<Element> logElements = logsElement.elements(Log.NameForArchiving.Class);
		for (Element logElement : logElements) {
			Log log = new Log();
			log.decode(logElement);
			this.logs.add(log);
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
		Element logsElement = element.addElement(NameForArchiving.Logs);
		for (Log log : logs) {
			logsElement.add(log.encode());
		}
		return element;
	}
}
