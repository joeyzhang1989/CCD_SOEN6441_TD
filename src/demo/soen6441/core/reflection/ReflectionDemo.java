package demo.soen6441.core.reflection;

import com.soen6441.core.tower.Tower;

public class ReflectionDemo {

	public static void main(String[] args) {
		String className = "Tower";
		String classPath = "com.soen6441.core.tower.";
		
		try {
			// Tower tower = new Tower();
			Class towerClass = Class.forName(classPath + "." + className);
			Tower tower = (Tower) towerClass.newInstance();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
