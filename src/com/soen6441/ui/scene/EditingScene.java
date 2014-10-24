package com.soen6441.ui.scene;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.Road;
import com.soen6441.core.map.Road.Type;
import com.soen6441.core.play.Play;
import com.soen6441.core.tower.Tower;
import com.soen6441.ui.common.Command;
import com.soen6441.ui.common.GridViewSelectionListener;
import com.soen6441.ui.common.IInspectable;
import com.soen6441.ui.common.InspectorView;
import com.soen6441.ui.map.MapItemCell;
import com.soen6441.ui.map.MapItemCellFactory;
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
 * @since 0.1
 *
 */

public class EditingScene extends View implements GridViewSelectionListener {

	private Button controlButton;// validate button to make sure map is valid
	private Label infoLabel;
	private TextField money;

	private MapView mapView;// the map of the game where the grid resides
	private InspectorView inspectorView;

	private Button backButton;
	private Button saveButton;

	private Play play;

	protected void init() {
		play = Play.currentPlay();
		super.init();
	};

	/**
	 * This method adds the buttons and components to the editing scene.
	 */
	protected void initSubviews() {
		super.initSubviews();

		// upper view containing the money label,infolabel label,controlbutton
		// button.
		View upperView = new View();
		upperView.setLocation(0, 0);
		upperView.setSize(800, 60);
		// upperView.setBackground(Color.gray);
		
		this.setBackground(new Color(0xDDDDDD));// set the overall background
												// color

		// Validate button
		this.controlButton = new Button();
		this.controlButton.setLocation(10, 10);
		this.controlButton.setTitle("Validate");
		this.controlButton.setSize(120, 40);
		// InfoLabel : valid or not
		String valid = "Not valid";
		this.infoLabel = new Label();
		this.infoLabel.setText("Validation : " + valid);
		this.infoLabel.setSize(500, 40);
		this.infoLabel.setLocation(135, 10);
		// initial money Label
		Label initialMoney = new Label();
		initialMoney.setText("Initial Money");
		initialMoney.setSize(300, 40);
		initialMoney.setLocation(600, 10);
		// actual money
		this.money = new TextField();
		this.money.setText("1000");
		// THIS SHOULD BE SET TO PLAY.GETCOINS()
		this.money.setSize(80, 40);
		this.money.setLocation(700, 10);

		upperView.add(this.controlButton);
		upperView.add(this.infoLabel);
		upperView.add(initialMoney);
		upperView.add(this.money);
		this.add(upperView);

		// Lower view containing the back and save buttons
		View lowerView = new View();
		lowerView.setLocation(0, 550);
		lowerView.setSize(800, 40);
		;
		// lowerView.setBackground(Color.blue);

		// back button
		this.saveButton = new Button();
		this.saveButton.setTitle("SAVE");
		this.saveButton.setSize(60, 20);
		this.saveButton.setLocation(730, 0);
		// save button
		this.backButton = new Button();
		this.backButton.setTitle("BACK");
		this.backButton.setSize(60, 20);
		this.backButton.setLocation(10, 0);

		lowerView.add(this.saveButton);
		lowerView.add(this.backButton);
		// lowerView.setBackground(Color.cyan);
		this.add(lowerView);

		// MapView
		this.mapView = new MapView();
		this.mapView.setMap(play.getMap());
		this.mapView.setSize(this.mapView.suggestedSize());
		int height = (600 - this.mapView.suggestedSize().height) / 2;
		int width = (800 - 180 - this.mapView.suggestedSize().width) / 2;
		// 180 is the size of the inspector View
		this.mapView.setLocation(width, height);
		this.add(mapView);
		this.mapView.setSelectionListener(this);
		
		// Inspectorview
		this.inspectorView = new InspectorView();
		this.inspectorView.setLocation(620, 60);
		this.inspectorView.setSize(180, 480);
		this.inspectorView.setBackground(new Color(0xEEEEEE));
		this.add(inspectorView);

	}

	@Override
	protected void initEvents() {

		backButton.addActionListener(new ActionListener() {

			@Override
			/**
			 * *perform the function that click the backbutton to go to editingscene
			 */
			public void actionPerformed(ActionEvent e) {
				EditingScene.this.viewFlow.pop();
			}
		});
	}

	/**
	 * This method responds to selections on the screen, when selected a cell
	 * should display its valid information
	 */
	@Override
	public void gridViewDidSelect() {
		MapItem item = play.getMap().getSelectedItem();
		if (item == null) {
			this.inspectorView.setOn(new InspectableScenery());
			this.inspectorView.update();
		} else if (item instanceof Road) {
			this.inspectorView.setOn(new InspectableRoad(item));
			this.inspectorView.update();
		} 
	}

	/**
	 * This class is used to display the information of a scenery cell, it will
	 * also let the user add a start, end and road point.
	 * 
	 * @author JeanRaymondDaher
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

		@Override
		public String title() {
			String title = "Scenery";
			return title;
		}

		@Override
		public String subtitle() {
			return "Scenery editing";
		}

		@Override
		public String description() {
			return "This is an empty cell where u can add a start, end or normal-road point";
			// TODO Auto-generated method stub
		}

		@Override
		public List<Command> commands() {
			List<Command> commands = new ArrayList<Command>();
			commands.add(this.buildStartPoint);
			commands.add(this.buildEndPoint);
			commands.add(this.buildRoad);
			return commands;
		}

		@Override
		public void execute(Command command) {
			if (command == buildRoad) {
				Road road = new Road();
				MapItemCell roadCell = MapItemCellFactory.cellFromItem(road);
				GridMap gridMap = play.getMap();
				gridMap.setItem(road, gridMap.getSelectedPoint());
				mapView.replaceCell(mapView.getSelectedCell(), roadCell);
			} else if (command == buildStartPoint) {
				Road road = new Road(Type.START);
				MapItemCell roadCell = MapItemCellFactory.cellFromItem(road);
				GridMap gridMap = play.getMap();
				gridMap.setItem(road, gridMap.getSelectedPoint());
				mapView.replaceCell(mapView.getSelectedCell(), roadCell);
			} else if (command == buildEndPoint) {
				Road road = new Road(Type.END);
				MapItemCell roadCell = MapItemCellFactory.cellFromItem(road);
				GridMap gridMap = play.getMap();
				gridMap.setItem(road, gridMap.getSelectedPoint());
				mapView.replaceCell(mapView.getSelectedCell(), roadCell);
			}
		}

	}

	/**
	 * This class is used to display the information of a road cell, it will
	 * also let the destroy this cell.
	 * 
	 * @author JeanRaymondDaher
	 */
	private class InspectableRoad implements IInspectable {

		private Command destroyCell;
		private MapItem item;

		public InspectableRoad(MapItem temp) {
			this.destroyCell = new Command("Delete", "");
			this.item = temp;
		}

		@Override
		public String title() {
			String title;
			if (this.item.getName() != "Road") {
				title = "Road =>" + this.item.getName();
			}else{
				title = "Road";
			}

			return title;
		}

		@Override
		public String subtitle() {
			return "Road editing";
		}

		@Override
		public String description() {
			return "This is a road cell that you can destroy";
		}

		@Override
		public List<Command> commands() {
			List<Command> commands = new ArrayList<Command>();
			commands.add(this.destroyCell);
			return commands;
		}

		@Override
		public void execute(Command command) {
			if (command == destroyCell) {
				MapItemCell cell = MapItemCellFactory.cellFromItem(null);
				GridMap gridMap = play.getMap();
				gridMap.removeItem(gridMap.getSelectedItem());
				mapView.replaceCell(mapView.getSelectedCell(), cell);
			}
		}
	}

}