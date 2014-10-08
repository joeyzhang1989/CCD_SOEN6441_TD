package com.soen6441.ui.sence;

import com.soen6441.ui.common.IInspectable;
import com.soen6441.ui.common.InspectorView;
import com.soen6441.ui.map.MapView;
import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;


/**
 * @author chenglong zhang chenglongzhang931@gmail.com
 * @since version 1.0
 * this is PlayingScene class is responsible of the playscene view
 * not finish with the inner classes
 * @see
 */
public class PlayingScene implements IInspectable {
	
	// these properties are defined in the ui.parallel package that inherited from javax.swing
	private Label moneyLabel;
	private Label infoLabel;
	private Label lifelabel;
	private Button controlButton;
	private Button backButton;
	private InspectorView inspectorView = new InspectorView();
	private MapView mapView = new MapView();

}