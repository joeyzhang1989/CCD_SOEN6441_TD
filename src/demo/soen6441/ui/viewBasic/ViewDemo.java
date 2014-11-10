package demo.soen6441.ui.viewBasic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.View;

/**
 * @author chenglong
 * @version $Revision: 1.0 $
 */
public class ViewDemo extends View {

	private Button button;
	
	public ViewDemo() {
		super();
	}
	
	@Override
	protected void init() {
		super.init();

		this.setSize(100, 100);
	}
	
	@Override
	protected void initSubviews() {
		super.initSubviews();
		
		button = new Button();
		

	}
	
	@Override
	protected void initEvents() {
		super.initEvents();
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("action");
			}
		});
		
	}
	
	
}
