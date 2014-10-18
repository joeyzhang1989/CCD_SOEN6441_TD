package demo.soen6441.ui.viewFlow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.View;

/**
 */
public class ViewFlowDemoScene2 extends View {
	
	private Button button;
	
	@Override
	protected void initSubviews() {
		super.initSubviews();
		
		Label label = new Label();
		label.setText("Scene 2");
		label.setLocation(200, 100);
		label.setSize(200, 40);
		this.add(label);
		
		button = new Button();
		button.setTitle("Go back to Scene1");
		button.setLocation(200, 200);
		button.setSize(200, 40);
		this.add(button);
		
	}
	
	@Override
	protected void initEvents() {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewFlowDemoScene2.this.viewFlow.pop();
			}
		});
	}
}
