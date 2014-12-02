package com.soen6441.core.log;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import com.soen6441.core.IArchive;

/**
 * 
 * @author Zhe Zhao
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
	 
	public synchronized void addLog(Log log) {
		logs.add(log);
	}
	
	public synchronized List<Log> getLogs(String category) {
		List<Log> filteredLogs = new ArrayList<Log>();
		for (Log log : logs) {
			if (log.getCategory().equals(category)) {
				filteredLogs.add(log);
			}
		}
		return filteredLogs;
	}
	
	public synchronized List<Log> getLogs(String category, String message) {
		List<Log> filteredLogs = new ArrayList<Log>();
		for (Log log : logs) {
			if (log.getCategory().equals(category) && log.getMessage().equals(message)) {
				filteredLogs.add(log);
			}
		}
		return filteredLogs;
	}
	
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
	public class NameForArchiving{
		public static final String Class = "Logger";
		private static final String Logs = "logs";
	}
	 
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
