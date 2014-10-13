package com.soen6441.ui.scene;

import com.soen6441.ui.common.Command;
import com.soen6441.ui.common.InspectorView;
import com.soen6441.ui.map.MapView;
import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.View;


/**
 * @author chenglong zhang 
 * this is PlayingScene class is responsible of the playScene view and showing the 
 * corresponding information of the related models
 * not finish with the inner classes
 */
public class PlayingScene extends View{
	
	/**
	 * 
	 * these properties are defined in the ui.parallel package that inherited from javax.swing
	 */
	private Label moneyLabel;
	private Label infoLabel;
	private Label lifelabel;
	private Button controlButton;
	private Button backButton;
	private InspectorView inspectorView = new InspectorView();
	private MapView mapView = new MapView();


}

