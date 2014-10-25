package demo.soen6441.ui.viewFlow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.View;

/**
 * @author chenglong
 * @version $Revision: 1.0 $
 */
public class ViewFlowDemoScene1 extends View {
	
	private Button button;
	
	@Override
	protected void initSubviews() {
		super.initSubviews();
		
		Label label = new Label();
		label.setText("Scene1");
		label.setLocation(100, 100);
		label.setSize(200, 40);
		this.add(label);
		
		button = new Button();
		button.setTitle("Go to Scene2");
		button.setLocation(100, 200);
		button.setSize(200, 40);
		this.add(button);
		
	}
	
	@Override
	protected void initEvents() {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewFlowDemoScene2 scene2 = new ViewFlowDemoScene2();
				ViewFlowDemoScene1.this.viewFlow.push(scene2);
			}
		});
	}
	
}
