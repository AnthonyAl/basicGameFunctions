package com.alexandris.games2d.util.physics;

import java.awt.Rectangle;
import java.awt.geom.Area;

public class PhysicsRPG {
	
	private double ret[] = new double[2];
	
	public double[] movementPhysics(Area block, int size, double x, double y, boolean w, boolean s, boolean a, boolean d) {
		
		if(size < 5) size = 5;
		Area up = new Area(getRectangle(x - 2, y - 8, size + 4, 5));
		Area down = new Area(getRectangle(x - 2, y + size + 3, size + 4, 5));
		Area left = new Area(getRectangle(x - 8, y + 2, 5, size - 4));
		Area right = new Area(getRectangle(x + size + 3, y + 2, 5, size - 4));
				
		if(w) {
			up.intersect(block);
			if(up.isEmpty()) {
				y -= 5;
			}
		}	
		else if(s) {
			down.intersect(block);
			if(down.isEmpty()) {
				y += 5;
			}
		}
		
		
		if(a) {
			left.intersect(block);
			if(left.isEmpty()) {
				x -= 5;
			}
		}
		else if(d) {
			right.intersect(block);
			if(right.isEmpty()) {
				x += 5;
			}
		}
		
		ret[0] = x;
		ret[1] = y;
		
		return ret;
	}
	
	public Rectangle getRectangle(double a, double b, double c, double d) {
		return new Rectangle((int) a,(int) b, (int) c, (int) d);
	}
	
}
