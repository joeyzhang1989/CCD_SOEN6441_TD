package com.soen6441.ui.common;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.ImageAssets;

public class CommandButton extends Button {
	private Command command;

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
	
	@Override
	public void paint(Graphics g) {
		String title = command.getTitle();
		String subtitle = command.getSubtitle();
		String imageName = command.getImageName();
		
		Graphics2D graphics2d = (Graphics2D)g;
		graphics2d.setFont(this.getFont());
		boolean isPressed = this.getModel().isPressed();
		boolean enabled = this.isEnabled(); 
		boolean hasTitle = title != null;
		boolean hasSubtitle = subtitle != null;
		boolean hasImage = imageName != null;

		Color colorTint = enabled ? new Color(0x333333) : new Color(0xCCCCCC);
		Color colorBack = new Color(0xFFFFFF);
		
		if (hasTitle) {
	
			if (enabled && isPressed) {
				graphics2d.setColor(colorTint);
				graphics2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			} else {
				graphics2d.setColor(colorBack);
				graphics2d.fillRect(0, 0, this.getWidth(), this.getHeight());
				graphics2d.setColor(colorTint);
				graphics2d.drawRect(0, 0, this.getWidth(), this.getHeight());
			}
			
			FontMetrics metrics = graphics2d.getFontMetrics(this.getFont());
	
			int height = metrics.getHeight();
			int titleWidth = metrics.stringWidth(title);
			float y = (float)((this.getHeight() - height ) / 2.0f) + metrics.getAscent();
			
			
			if (hasSubtitle) {
				int subtitleWidth = metrics.stringWidth(subtitle);
				
				int margin = 10;
				graphics2d.setColor(isPressed ? colorBack : colorTint);
				
				graphics2d.drawString(title, margin, y);
				graphics2d.drawString(subtitle, this.getWidth() - margin - subtitleWidth, y);
				
			} else {
				graphics2d.setColor(isPressed ? colorBack : colorTint);
				graphics2d.drawString(title, (float)((this.getWidth() - titleWidth ) / 2.0f), y);
			}
			
		} else if (hasSubtitle){
			
		} else if (hasImage) {

			graphics2d.setColor(colorBack);
			graphics2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.drawImage(ImageAssets.imageNamed(imageName), 0, 0, null);

			graphics2d.setColor(colorTint);
			graphics2d.drawRect(0, 0, this.getWidth(), this.getHeight());
		}
	}
}
