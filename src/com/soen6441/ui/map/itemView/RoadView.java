package com.soen6441.ui.map.itemView;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Observable;

import javax.imageio.ImageIO;

import com.soen6441.core.map.Road;
import com.soen6441.ui.map.MapItemView;
import com.soen6441.ui.parallel.ImageAssets;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class RoadView extends MapItemView{
	
	private static BufferedImage bufferedImage;
	
	private static Image[][] cachedImage;
	
	static {
		try{
			bufferedImage = ImageIO.read(new File("images/road_uniun.png"));
		}catch(Exception e) {
			
		}
		
		cachedImage = new Image[4][4];
	}
	
	
	/*
	 * Mark - Displaying - Methods
	 */
	
	/**
	 * Method update.
	 * @param o Observable
	 * @param arg Object
	 * @see java.util.Observer#update(Observable, Object)
     */
	@Override
	public void update(Observable o, Object arg) {
		super.update(o, arg);
		if (arg == Road.OBSERVABLE_EVENT_PROPERTY_CONNECTIONS_DID_CHANGE){
			this.repaint();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(roadImage(), 0, 0, null);
		g.drawImage(ImageAssets.imageNamed(this.getItem().getCellImageName()), 0, 0, null);
	}
	
	private Image roadImage() {
		Road road = getItemRoad();
		boolean[] connections = road.getConnections();
		
		int[] indexMap = {0, 1, 3, 2};
		
		int index, x, y;
		
		index = 0;
		if (connections[0]) index += 1;
		if (connections[2]) index += 2;
		index = indexMap[index];
		x = index;
		
		index = 0;
		if (connections[1]) index += 1;
		if (connections[3]) index += 2;
		index = indexMap[index];
		y = index;
		
		Image image = cachedImage[x][y];
		if (image == null) {
			image = bufferedImage.getSubimage(x * 40, y * 40, 40, 40);
			cachedImage[x][y] = image;
		}

		return image;
	}
	
	/*
	 * Mark - Convenience - Methods
	 */
	
	/**
	 * Method getItemRoad.	
	 * @return Road  
     */
	public Road getItemRoad(){
		return (Road)this.getItem();
	}
}
