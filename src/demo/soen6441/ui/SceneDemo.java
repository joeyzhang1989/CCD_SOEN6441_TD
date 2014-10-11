package demo.soen6441.ui;

import java.awt.Color;

import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.View;
import com.soen6441.ui.parallel.Window;

public class SceneDemo extends View{
	
	private Button button;
	
	private ViewDemo view;
	
	@Override
	protected void init() {
		super.init();
		this.setSize(800, 600);
	}
	
	@Override
	protected void initSubviews() {
		super.initSubviews();
		
		button = new Button();
		button.setTitle("Next Wave");
		button.setSize(100, 40);
		button.setLocation((int)(this.getSize().getWidth() - button.getSize().getWidth() - 10), 10);
		this.add(button);
		
		view = new ViewDemo();
		view.setLocation(0, 100);
		view.setBackground(new Color(255, 0, 0));
		this.add(view);
	}
	
	// Using a window to test a view.
	public static void main(String[] args) {
		SceneDemo sceneDemo = new SceneDemo();
		new Window(sceneDemo).show();
	}
	
}

