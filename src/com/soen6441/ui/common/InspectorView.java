package com.soen6441.ui.common;




import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.ImageView;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.View;

// not finish 
/**
 * This class defines the inspector view. On the right side of our game the menu that will show editing options or tower information is the inspector view.
 * An element needs to be inspectable to be displayed on the view.
 * 
 * @author Zhe Zhao
 * @author jean raymond daher
 * @author chenglong zhang
 * @see IInspectable
 */
public class InspectorView extends View{

	/*
	 * Properties
	 */
	
	private Label titleLabel;
	private Label subtitleLabel;
	private ImageView imageView;
	private Label descriptionLabel;
	private List<Button> commandButtons;
	
	private IInspectable on;
	private List<Command> commands;
	
	/*
	 * View Life-Cycle
	 */
	
	@Override
	protected void init() {
		super.init();
		this.setSize(180, 500);
	}
	
	@Override
	protected void initSubviews() {
		super.initSubviews();
		
		Label titleLabel = new Label();
		titleLabel.setSize(160, 20);
		titleLabel.setLocation(10, 10);
		this.add(titleLabel);
		this.titleLabel = titleLabel;
		
		Label subtitleLabel = new Label();
		subtitleLabel.setSize(160, 20);
		subtitleLabel.setLocation(10, titleLabel.getY() + titleLabel.getHeight() + 10);
		this.add(subtitleLabel);
		this.subtitleLabel = subtitleLabel;
		
		Label descriptionLabel = new Label();
		descriptionLabel.setSize(160, 100);
		descriptionLabel.setLocation(10, 160);
		this.add(descriptionLabel);
		this.descriptionLabel = descriptionLabel;
	
	}
	
	@Override
	protected void initEvents() {
		super.initEvents();
		
		this.addPropertyChangeListener("on", new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("change");
			}
		});
	}
	
	
	/*
	 * Getters & Setters
	 */
	
	public IInspectable getOn() {
		return on;
	}
	public void setOn(IInspectable on) {
		this.on = on;
	}
	
	public static void main(String[] args) {
		InspectorView v = new InspectorView();
		v.setOn(new IInspectable() {
			
			@Override
			public String title() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String subtitle() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void execute(Command command) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String description() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<Command> commands() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
		
	}
}
