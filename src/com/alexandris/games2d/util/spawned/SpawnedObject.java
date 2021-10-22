package com.alexandris.games2d.util.spawned;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;

public abstract class SpawnedObject {
	
	public double x, y;
	public int size = 0;
	public SPAWNED_ID id;

	public SpawnedObject(int x, int y, SPAWNED_ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public abstract Area getArea();
	
	
	public void setY(double y) {
		this.y = y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public double getX() {
		return x;
	}
	public SPAWNED_ID getId() {
		return id;
	}
	public void setId(SPAWNED_ID id) {
		this.id = id;
	}
	
}
