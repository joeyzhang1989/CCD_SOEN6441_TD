package demo.soen6441.ui.viewBasic;

import java.awt.Color;

import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.View;
import com.soen6441.ui.parallel.Window;

public class SceneDemo extends View{
	
	private Button button;
	
	private ViewDemo view;

	private Label label;

	private Label infolabel;

	
	
	@Override
	protected void initSubviews() {
		super.initSubviews();
		
		button = new Button();
	button.setTitle("Validate");
		
		button.setSize(120, 40);
		button.setLocation(10, 10);
		//button.setTitle("validate");
		this.add(button);
		
		String valid="Not valid";
		this.infolabel=new Label();
		this.infolabel.setText("Validation : "+valid);
		this.infolabel.setSize(500, 40);
		this.infolabel.setLocation(135, 10);
		this.add(infolabel);
		
		Label initialMoney=new Label();
		    initialMoney.setText("Initial Money");
		    initialMoney.setSize(300, 40);
		    initialMoney.setLocation(600, 10);
		this.add(initialMoney);    
			
		 this.label=new Label();
		    this.label.setText("1000");
		    //THIS SHOULD BE SET TO PLAY.GETCOINS()
		    this.label.setSize(150,40);
		    this.label.setLocation(700,10);
		    this.add(label);
		
		    
		    
		    
			//back button
		  Button  saveButton=new Button();
		    saveButton.setTitle("SAVE");
		    saveButton.setSize(60, 20);
		    saveButton.setLocation(730, 550);
		    //save button
		 Button   backButton=new Button();
		    backButton.setTitle("BACK");
		    backButton.setSize(60, 20);
			backButton.setLocation(10, 550);
			
			this.add(saveButton);
			this.add(backButton);
		    
	//	view = new ViewDemo();
		//view.setLocation(0, 100);
	//	view.setBackground(new Color(255, 0, 0));
		//this.add(view);
	}
	
	// Using a window to test a view.
	public static void main(String[] args) {
		SceneDemo sceneDemo = new SceneDemo();
		new Window(sceneDemo);
	}
	
}

