package com.soen6441.ui.parallel;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = -1894544342715903093L;
	
	private View contentView;
	
	public Window(View contentView) {
		this.contentView = contentView;

		this.setResizable(false);
		this.setSize(800, 600);
		this.setLayout(null);
//		this.setUndecorated(true);
		
		this.add(contentView);
	}
	
	@Override
	public void setSize(Dimension d) {
		super.setSize(d);
		contentView.setSize(d);
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		contentView.setSize(width, height);
	}
}

