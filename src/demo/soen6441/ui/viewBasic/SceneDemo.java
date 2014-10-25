package demo.soen6441.ui.viewBasic;

import java.awt.Color;

import com.soen6441.ui.map.MapView;
import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.TextField;
import com.soen6441.ui.parallel.View;
import com.soen6441.ui.parallel.Window;

/**
 * @author chenglong
 * @version $Revision: 1.0 $
 */
public class SceneDemo extends View {

	private Button button;

	private ViewDemo view;

	private Label label;

	private Label infolabel;

	private TextField money;

	private MapView map;

	@Override
	protected void initSubviews() {
		super.initSubviews();

		button = new Button();
		button.setTitle("Validate");

		button.setSize(120, 40);
		button.setLocation(10, 10);
		// button.setTitle("validate");
		this.add(button);

		String valid = "Not valid";
		this.infolabel = new Label();
		this.infolabel.setText("Validation : " + valid);
		this.infolabel.setSize(500, 40);
		this.infolabel.setLocation(135, 10);
		this.add(infolabel);

		Label initialMoney = new Label();
		initialMoney.setText("Initial Money");
		initialMoney.setSize(300, 40);
		initialMoney.setLocation(600, 10);
		this.add(initialMoney);

		this.money = new TextField();
		this.money.setText("1000");
		// THIS SHOULD BE SET TO PLAY.GETCOINS()
		this.money.setSize(80, 40);
		this.money.setLocation(700, 10);
		this.add(money);

		// back button
		Button saveButton = new Button();
		saveButton.setTitle("SAVE");
		saveButton.setSize(60, 20);
		saveButton.setLocation(730, 550);
		// save button
		Button backButton = new Button();
		backButton.setTitle("BACK");
		backButton.setSize(60, 20);
		backButton.setLocation(10, 550);

		this.add(saveButton);
		this.add(backButton);
		// double height=this.map.getMap().getHeight();
		// double width=this.map.getMap().getWidth();
		/*
		 * height=15; width=15; View view = new View(); view.setLocation(10,
		 * 50); view.setSize(485,485); for(int i=0;i<height;i++) for(int
		 * j=0;j<width;j++){
		 * 
		 * }
		 */
		// view.setBackground(Color.BLACK);
		// this.add(view);

	}

	// Using a window to test a view.
	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main(String[] args) {
		SceneDemo sceneDemo = new SceneDemo();
		new Window(sceneDemo);
	}

}
