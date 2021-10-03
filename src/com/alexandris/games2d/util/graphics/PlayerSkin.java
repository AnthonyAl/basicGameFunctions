package com.alexandris.games2d.util.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class PlayerSkin {
	
	String skin_Path;
	ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	
	public PlayerSkin(String skin_Path) {
		this.skin_Path = skin_Path;
		load();
	}
	
	public void load() {
		BufferedImageLoader loader = new BufferedImageLoader();
		 File dir = new File(skin_Path);
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		    	System.out.println(child.getPath().substring(3).replace('\\', '/'));
		    	images.add(loader.loadImage(child.getPath().substring(3).replace('\\', '/')));
		    }
		  }
	}
	
	public BufferedImage getImage(int i) {
		return images.get(i);
	}
	
	public int getListSize() {
		return images.size();
	}
}
