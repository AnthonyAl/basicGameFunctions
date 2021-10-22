package com.alexandris.games2d.util.spawned;

import java.awt.Graphics;
import java.util.LinkedList;

public class SpawnedHandler {
	
	public LinkedList<SpawnedObject> object = new LinkedList<SpawnedObject>();
	
	public void tick() {
		
		for(int i = 0; i < object.size(); i++) {
			SpawnedObject tempObject = object.get(i);
			
			tempObject.tick();
		}	
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			SpawnedObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(SpawnedObject object) {
		this.object.add(object);
	}
	
	public void removeObject(SpawnedObject object) {
		this.object.remove(object);
	}
}
