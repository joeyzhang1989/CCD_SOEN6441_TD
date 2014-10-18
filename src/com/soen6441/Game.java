package com.soen6441;

import com.soen6441.ui.parallel.ViewFlow;
import com.soen6441.ui.parallel.Window;
import com.soen6441.ui.scene.MainScene;

/**
 */
public class Game {
	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main(String[] args) {
		ViewFlow viewFlow = new ViewFlow();
		new Window(viewFlow);
		viewFlow.push(new MainScene());
	}
}
