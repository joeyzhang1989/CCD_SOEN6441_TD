package demo.soen6441.ui.viewFlow;

import com.soen6441.ui.parallel.View;
import com.soen6441.ui.parallel.ViewFlow;
import com.soen6441.ui.parallel.Window;

/**
 * @author chenglong
 * @version $Revision: 1.0 $
 */
public class ViewFlowDemo extends View {
	
	
	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main(String[] args) {
		ViewFlow viewFlow = new ViewFlow();
		viewFlow.push(new ViewFlowDemoScene1());
		new Window(viewFlow);
	}
}
