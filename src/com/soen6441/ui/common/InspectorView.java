package com.soen6441.ui.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.TextView;
import com.soen6441.ui.parallel.View;

// not finish 
/**
 * This class defines the inspector view. On the right side of our game the menu that will show editing options or tower information is the inspector view.
 * An element needs to be inspectable to be displayed on the view.
 * 
 * @author Zhe Zhao
 * @author JeanRaymondDaher
 * @author Chenglong Zhang
 * 
 * @see IInspectable
 * @version $Revision: 1.0 $
 */
public class InspectorView extends View implements GridViewSelectionListener{
	
	/*
	 * Mark - Command - Properties
	 */

	private List<Command> gridCommands;
	private List<Command> commands;
	
	
	/*
	 * Mark - Views - Properties
	 */
	
	private Label titleLabel;
	private Label subtitleLabel;
//	private ImageView imageView;
	private TextView descriptionTextArea;
	private GridView gridView;
	private List<CommandButton> commandButtons;
	
	/*
	 * Mark - Views - Life Cycle 
	 */
	
	@Override
	protected void init() {
		super.init();
		this.setSize(180, 500);
		this.commandButtons = new ArrayList<CommandButton>();
	}
	
	@Override
	protected void initSubviews() {
		super.initSubviews();
		
		Label titleLabel = new Label();
		titleLabel.setFontSize(16);
		titleLabel.setSize(160, 20);
		titleLabel.setLocation(10, 10);
		this.add(titleLabel);
		this.titleLabel = titleLabel;
		
		Label subtitleLabel = new Label();
		subtitleLabel.setFontSize(12);
		subtitleLabel.setSize(160, 20);
		subtitleLabel.setLocation(10, titleLabel.getY() + titleLabel.getHeight() + 0);
		this.add(subtitleLabel);
		this.subtitleLabel = subtitleLabel;
		
		TextView descriptionTextView = new TextView();
		descriptionTextView.setFontSize(12);
		descriptionTextView.setSize(160, 200);
		descriptionTextView.setLocation(10, 60);
		descriptionTextView.setBackground(new Color(0xEEEEEE));
        
		GridView gridView = new GridView();
		gridView.setSelectionListener(this);
		gridView.setVisible(false);
		this.add(gridView);
		this.gridView = gridView;
		
		this.add(descriptionTextView);
		this.descriptionTextArea = descriptionTextView;
	}
	
	private static final int _NUMBER_OF_COLUMN = 4;
	private static final int _UNIT_LENGTH = 40;

	/**
	 * To refersh the inspector view.
	 */
	public void update() {
		titleLabel.setText(on.title());
		subtitleLabel.setText(on.subtitle());
		descriptionTextArea.setText(on.description());
		
		//remove old buttons
		for (CommandButton button:commandButtons){
			this.remove(button);
		}
		
		//add new buttons
		commandButtons.clear();
		commands = on.commands();
		if (commands != null) {
			for (int i = 0; i < commands.size(); i++){
				Command command = commands.get(i);
				
				CommandButton button = new CommandButton();
				button.setFontSize(12);
				button.setCommand(command);
				button.setSize(160, 40);
				button.setLocation(10, this.getHeight() - 50 * (commands.size() - i));
				this.add(button);
				this.commandButtons.add(button);
				
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						CommandButton commandButton = (CommandButton)e.getSource();
						on.execute(commandButton.getCommand());
					}
				});
			}
		}
		

		gridCommands = on.gridCommands();
		if (gridCommands != null) {
			gridView.setNumberOfColumns(_NUMBER_OF_COLUMN);
			gridView.setNumberOfRows((gridCommands.size() - 1) / _NUMBER_OF_COLUMN + 1);
			gridView.setUnitHeight(_UNIT_LENGTH);
			gridView.setUnitWidth(_UNIT_LENGTH);
			
			Dimension dimension = gridView.suggestedSize();
			gridView.setSize(dimension);
			gridView.setLocation(10, (int) (this.getHeight() - 50 * commands.size() - 10 - dimension.getHeight()));
			
			for (int i = 0 ; i < gridCommands.size() ; i ++) {
				CommandButton commandButton = new CommandButton();
				commandButton.setCommand(gridCommands.get(i));
				commandButton.setSize(_UNIT_LENGTH, _UNIT_LENGTH);
				
				GridViewCell cell = new GridViewCell();
				cell.add(commandButton);

				int row = i / _NUMBER_OF_COLUMN;
				int column = i % _NUMBER_OF_COLUMN;
				gridView.addCell(cell, new GridPoint(row, column));
				
				commandButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						CommandButton commandButton = (CommandButton)e.getSource();
						on.execute(commandButton.getCommand());
					}
				});
			}
			
			gridView.setVisible(true);
		} else {
			gridView.setVisible(false);
		}
	}
	
	/*
	 * Mark - On - Properties
	 */
	
	/**
	 * The thing which the inspectorView inspecting on.
	 */
	private IInspectable on;
	
	/*
	 * Mark - On - Getters & Setters
	 */
	
	/**
	 * Method getOn.
	 * @return IInspectable
     */
	public IInspectable getOn() {
		return on;
	}
	
	/**
	 * To set the thing be inspected.
	 * @param on IInspectable
	 */
	public void setOn(IInspectable on) {
		this.on = on;
	}

	/*
	 * Mark - Grid View Selection - Methods
	 */
	 
	/**
	 * Method gridViewDidSelect.
	 * @see com.soen6441.ui.common.GridViewSelectionListener#gridViewDidSelect()
	 */
	@Override
	public void gridViewDidSelect() {
		
	}
}
