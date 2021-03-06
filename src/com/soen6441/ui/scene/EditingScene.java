package com.soen6441.ui.scene;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.soen6441.core.log.Log;
import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.PathManager;
import com.soen6441.core.map.Road;
import com.soen6441.core.map.Road.RoadType;
import com.soen6441.core.play.Play;
import com.soen6441.core.play.PlayManager;
import com.soen6441.ui.common.Command;
import com.soen6441.ui.common.GridViewSelectionListener;
import com.soen6441.ui.common.IInspectable;
import com.soen6441.ui.common.InspectorView;
import com.soen6441.ui.map.MapView;
import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.TextField;
import com.soen6441.ui.parallel.View;

/**
 * This class is the Editing scene class where the user edits a created or new
 * map.
 * 
 * @author JeanRaymondDaher
 * @author Mengyao Wang
 * @author Chenglong Zhang
 *
 * @version $Revision: 1.0 $
 */

public class EditingScene extends View implements GridViewSelectionListener {
	
	/*
	 * Mark - Context - Properties
	 */

	private Play play;
	
	/*
	 * Mark - View - Properties
	 */
	
	private Button controlButton; //validate button to make sure map is valid
	private Label infoLabel; 
	private TextField money;

	private MapView mapView; //the map of the game where the grid resides
	private InspectorView inspectorView;

	private Button backButton;
	private Button saveButton;

	/*
	 * Mark - View - Life Cycle
	 */
	
	protected void init() {
		super.init();
		this.play = Play.currentPlay();
	};

	/**
	 * This method adds the buttons and components to the editing scene.
	 */
	protected void initSubviews() {
		super.initSubviews();

		this.setBackground(new Color(0x242424));
		
		// Upper view containing the money label,infolabel label,controlbutton button
		View upperView = new View();
		upperView.setLocation(0, 0);
		upperView.setSize(800, 60);
		upperView.setBackground(new Color(0x000000));

		// Validate button
		this.controlButton = new Button();
		this.controlButton.setLocation(10, 10);
		this.controlButton.setTitle("Validate");
		this.controlButton.setSize(120, 40);
		// InfoLabel : valid or not
		this.infoLabel = new Label();
		this.infoLabel.setText("Validation : Please Validate Map");
		this.infoLabel.setSize(500, 40);
		this.infoLabel.setLocation(135, 10);
		// initial money Label
		Label initialMoney = new Label();
		initialMoney.setText("Initial Money");
		initialMoney.setSize(300, 40);
		initialMoney.setLocation(600, 10);
		// actual money
		this.money = new TextField();
		this.money.setText(play.getCoins()+"");
		this.money.setSize(80, 40);
		this.money.setLocation(700, 10);

		upperView.add(this.controlButton);
		upperView.add(this.infoLabel);
		upperView.add(initialMoney);
		upperView.add(this.money);
		this.add(upperView);

		// Lower view containing the back and save buttons
		View lowerView = new View();
		lowerView.setLocation(0, 540);
		lowerView.setSize(800, 40);
		lowerView.setBackground(new Color(0x000000));

		// back button
		this.saveButton = new Button();
		this.saveButton.setTitle("SAVE");
		this.saveButton.setSize(60, 20);
		this.saveButton.setLocation(730, 10);
		// save button
		this.backButton = new Button();
		this.backButton.setTitle("BACK");
		this.backButton.setSize(60, 20);
		this.backButton.setLocation(10, 10);

		lowerView.add(this.saveButton);
		lowerView.add(this.backButton);
		this.add(lowerView);

		// MapView
		this.mapView = new MapView();
		this.mapView.setMap(play.getMap());
		this.mapView.setSize(this.mapView.suggestedSize());
		int centralizedYLocation = (600 - this.mapView.suggestedSize().height) / 2;
		int centralizedXLocation = (800 - 180 - this.mapView.suggestedSize().width) / 2;
		// 180 is the size of the inspector View
		this.mapView.setLocation(centralizedXLocation, centralizedYLocation);
		this.add(mapView);

		// Inspectorview
		this.inspectorView = new InspectorView();
		this.inspectorView.setLocation(620, 60);
		this.inspectorView.setSize(180, 480);
		this.inspectorView.setBackground(new Color(0x111111));
		this.add(inspectorView);
	}

	/**
	 * This method will respond to buttons pressed on the screen :
	 * Save,Validate,Back
	 */
	@Override
	protected void initEvents() {
		this.mapView.setSelectionListener(this);
		this.saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateMap()) {
					save();
				}
			}
		});
		this.controlButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validateMap();
			}
		});
		this.backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Play.destroy();
				EditingScene.this.viewFlow.pop();
			}

		});
	}
	
	/*
	 * Mark - Inspection - Methods
	 */

	/**
	 * This method responds to selections on the screen, when selected a cell
	 * should display its valid information
	 * @see com.soen6441.ui.common.GridViewSelectionListener#gridViewDidSelect()
	 */
	@Override
	public void gridViewDidSelect() {
		MapItem item = play.getMap().getSelectedItem();
		if (item == null) {
			this.inspectorView.setOn(new InspectableScenery());
			this.inspectorView.update();
		} else if (item instanceof Road) {
			Road road = (Road) item;
			this.inspectorView.setOn(new InspectableRoad(road));
			this.inspectorView.update();
		}
	}


	/*
	 * Mark - Inspection - Classes - Scenery
	 */
	
	/**
	 * This class is used to display the information of a scenery cell, it will
	 * also let the user add a start, end and road point.
	 * 
	 * @author JeanRaymondDaher
	 * @version $Revision: 1.0 $
	 */
	private class InspectableScenery implements IInspectable {

		private Command buildStartPoint;
		private Command buildEndPoint;
		private Command buildRoad;

		public InspectableScenery() {
			this.buildStartPoint = new Command("Build Start Point", "");
			this.buildEndPoint = new Command("Build End Point", "");
			this.buildRoad = new Command("Build Road", "");
		}

		/**
		 * Method title.
		 * @return String
         * @see com.soen6441.ui.common.IInspectable#title()
         */
		@Override
		public String title() {
			String title = "Scenery";
			return title;
		}

		/**
		 * Method subtitle.
		 * @return String
         * @see com.soen6441.ui.common.IInspectable#subtitle()
         */
		@Override
		public String subtitle() {
			return "This is a empty space.";
		}

		/**
		 * Method description
         * @return String  
		 * @see com.soen6441.ui.common.IInspectable#description()  
		 */
		@Override
		public String description() {
			return "This is an empty space where u can add a start, end or normal-road point";
		}
		
		/**
		 * Method commands.
		 * @return List<Command>
         * @see com.soen6441.ui.common.IInspectable#commands()
         */
		@Override
		public List<Command> commands() {
			List<Command> commands = new ArrayList<Command>();
			commands.add(this.buildStartPoint);
			commands.add(this.buildEndPoint);
			commands.add(this.buildRoad);
			return commands;
		}

		/**
		 * Method gridCommands.
		 * @return List<Command>
		 * @see com.soen6441.ui.common.IInspectable#gridCommands()
		 */
		@Override
		public List<Command> gridCommands() {
			return null;
		}

		/**
		 * Method execute.
		 * @param command Command
		 * @see com.soen6441.ui.common.IInspectable#execute(Command) */
		@Override
		public void execute(Command command) {
			Road road = null; 
			if (command == buildRoad) {
				road = new Road();
			} else if (command == buildStartPoint) {
				road = new Road(RoadType.START);
			} else if (command == buildEndPoint) {
				road = new Road(RoadType.END);
			}
			
			GridMap gridMap = play.getMap();
			gridMap.setItem(road, gridMap.getSelectedPoint());
		}

	}
	

	/*
	 * Mark - Inspection - Classes - Road
	 */

	/**
	 * This class is used to display the information of a road cell, it will
	 * also let the destroy this cell.
	 * 
	 * @author JeanRaymondDaher
	 * @version $Revision: 1.0 $
	 */
	private class InspectableRoad implements IInspectable {

		private Command destroyCell;
		private Road road;

		/**
		 * Constructor for InspectableRoad.
		 * @param road MapItem
		 */
		public InspectableRoad(Road road) {
			this.destroyCell = new Command("Delete", "");
			this.road = road;
		}

		/**
		 * Method title.
		 * @return String 
		 * @see com.soen6441.ui.common.IInspectable#title()
		 */
		@Override
		public String title() {
			return road.getName();
		}

		/**
		 * Method subtitle.
		 * @return String
		 * @see com.soen6441.ui.common.IInspectable#subtitle() 
		 */
		@Override
		public String subtitle() {
			return "Road editing";
		}

		/**
		 * Method description.
		 * @return String 
		 * @see com.soen6441.ui.common.IInspectable#description() 
		 */
		@Override
		public String description() {
			return "This is a road cell that you can destroy";
		}
		
		/**
		 * Method gridCommands.
	 	 * @return List<Command>
		 * @see com.soen6441.ui.common.IInspectable#gridCommands()
		 */
		@Override
		public List<Command> gridCommands() {
			return null;
		}
		
		/**
		 * Method commands.
		 * @return List<Command>
         * @see com.soen6441.ui.common.IInspectable#commands()
         */
		@Override
		public List<Command> commands() {
			List<Command> commands = new ArrayList<Command>();
			commands.add(this.destroyCell);
			return commands;
		}

		/**
		 * Method execute.
		 * @param command Command
		
		 * @see com.soen6441.ui.common.IInspectable#execute(Command) */
		@Override
		public void execute(Command command) {
			if (command == destroyCell) {
				play.getMap().removeItem(road);
			}
		}
	}

	/*
	 * Mark - Storage - Methods
	 */

	/**
	 * This method will check that the path is valid from Start to End.
	 * 
	 * @return boolean
	 * True or False depending on validation
     * @see PathManager
     */
	private boolean validateMap() {
		GridMap gridMap = this.play.getMap();
		if (gridMap.getPathManager().validate()) {
			infoLabel.setText("Validation : Path Is Valid");
			return true;
		} else {
			infoLabel.setText("Validation : " + gridMap.getPathManager().getErrorMessage());
			return false;
		}

	}
	/**
	 * This method will save the game to a file.
	 * 
	 */
	private void save() {
		Log log = new Log(Log.CATEGORY_MAP).message("Edited");
		play.getMap().getLogger().addLog(log);
		
		play.setCoins(Integer.parseInt(this.money.getText())); 
		PlayManager playManager = new PlayManager();
		playManager.save(play.getSourceFile(), play);
		infoLabel.setText("The map file has been saved.");
		JOptionPane.showMessageDialog(this, "Save Successfull");

	}

	/*
	 * Mark - Storage - Getters & Setters
	 */

}