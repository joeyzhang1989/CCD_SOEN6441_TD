package com.soen6441.ui.parallel;

import java.util.ArrayList;
import java.util.List;

public class ViewFlow extends View {
	private List<View> contentViews;
	
	@Override
	protected void init() {
		super.init();
		this.setSize(800, 600);
		contentViews = new ArrayList<View>();
	}
	
	public void push(View view){
//		if (contentViews.size() > 0){
//			View oldView = contentViews.get(contentViews.size() - 1);
//			this.remove(oldView);
//		}
		
		contentViews.add(view);
		view.setViewFlow(this);
		view.setSize(this.getSize());
		this.add(view);
//		this.validate();
	}
	
	public void pop(){
		if (contentViews.size() > 0){
			View view = contentViews.get(contentViews.size() - 1);
			this.remove(view);
			contentViews.remove(view);
//			view = contentViews.get(contentViews.size() - 1);
//			this.add(view);
		}
	}
}
