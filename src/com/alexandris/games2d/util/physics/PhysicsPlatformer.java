package com.alexandris.games2d.util.physics;

import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.ArrayList;

public class PhysicsPlatformer {
	
	private boolean wjl = true, wjr = true;
	private int velx = 0, vely = 0;
	private double[] ret = new double[4];
	private double[] ret2 = new double[5];
	
	public double[] movementPhysics(Area block, int size, double x, double y, boolean w, boolean s, boolean a, boolean d) {
		
		if(size < 5) size = 5;
		Area up = new Area(getRectangle(x - 2, y - 8, size + 4, 5));
		Area down = new Area(getRectangle(x - 2, y + size - 1, size + 4, 2 + vely));
		Area left = new Area(getRectangle(x - 8, y + 2, 5, size - 4));
		Area right = new Area(getRectangle(x + size + 3, y + 2, 5, size - 4));
		
		
		up.intersect(block);
		if(!up.isEmpty()) {
			vely = 2;
		}
		
		down.intersect(block);
		if(down.isEmpty()) {
			vely += 1;
		}
		else {
			wjl = true;
			wjr = true;
			vely = 0;
			up.intersect(block);
			if(w && up.isEmpty()) {
				vely = -20;
			}
		}
		
		if(a) {
			left.intersect(block);
			if(!left.isEmpty()) {
				//x += 5;
				velx = 0;
				
				up.intersect(block);
				if(w && up.isEmpty() && wjl) {
					vely = -18;
					wjl = false;
				}
				
			}
			else {
				velx = -5;
			}
		}
		
		else if(d) {
			right.intersect(block);
			if(!right.isEmpty()) {
				//x -= 5;
				velx = 0;
				
				up.intersect(block);
				if(w && up.isEmpty() && wjr) {
					vely = -18;
					wjr = false;
				}
				
			}
			else {
				velx = 5;
			}
		}
		else velx = 0;
		
		ret[0] = x + velx;
		ret[1] = y + vely / 2;
		ret[2] = velx;
		ret[3] = vely;
		
		return ret;
	}
	
	public double[] swimmingPhysics(Area block, Area water, int size, double x, double y, boolean w, boolean s, boolean a, boolean d) {
		
		if(size < 5) size = 5;
		Area player = new Area(getRectangle((int) x, (int) y, size + 2, size + 2));
		Area down = new Area(getRectangle(x - 2, y + size - 1, size + 4, 2 + vely));
		Area left = new Area(getRectangle(x - 8, y + 2, 5, size - 4));
		Area right = new Area(getRectangle(x + size + 3, y + 2, 5, size - 4));
		
		player.intersect(water);
		down.intersect(block);
		left.intersect(block);
		right.intersect(block);
		
		
		if(!player.isEmpty()) {
			
			wjl = true;
			wjr = true;
			
			if(!left.isEmpty() && w && a) y += 4;
			if(!right.isEmpty() && w && d) y += 4;
			
			if(w) vely = -5;
			else vely = 1;
			
			if(a) velx = 1;
			else if(d) velx = -1;
			
			if(s && down.isEmpty()) vely = 5;
			
			
			ret[0] = x + velx;
			ret[1] = y + vely / 3;
			ret[2] = -velx;
			ret[3] = -vely;
		}
		
		return ret;
	}
	
//	public double[] shootingPhysics(Area obstacle, Area projectile, double x, double y, double velx, double vely, double x1, double y1, double x2, double y2) {
//		
//		projectile.intersect(obstacle);
//		if(projectile.isEmpty()) ret2[4] = 1;
//		else ret2[4] = 0;
//		
//		ret2[0] = x + velx;
//		vely = line(ret2[0], x1, y1, x2, y2) - line(x, x1, y1, x2, y2);
//		if(Math.abs(vely) > 15) {
//			if(vely > 0) vely = 15;
//			else vely = -15;
//		}
//		ret2[1] = y + vely;
//		
//		if(velx > 1) ret2[2] = velx - 0.05f;
//		else if(velx < 1) ret2[2] = velx + 0.05f;
//		else ret2[2] = 1;
//		ret2[3] = vely;
//		
//		return ret2;
//		
//	}
	
	public double[] projectilePhysics(ArrayList<Area> obstacles, Area projectile, double x, double y, double velx, double vely) {
		for(Area a : obstacles) {
			projectile.intersect(a);
			if(projectile.isEmpty()) ret2[4] = 1;
			else {
				ret2[4] = 0;
				break;
			}	
		}
		ret2[0] = x + velx;
		ret2[1] = y + vely;
		ret2[2] = velx;
		ret2[3] = vely;
		
		return ret2;
		
	}
	
	public double[] projectilePhysics(ArrayList<Area> obstacles, Area projectile, double velocity, double x, double y, double x1, double y1, double x2, double y2) {
		for(Area a : obstacles) {
			projectile.intersect(a);
			if(projectile.isEmpty()) ret2[4] = 1;
			else {
				ret2[4] = 0;
				break;
			}
		}
		double[] traj = calcTrajectory(velocity, x1, y1, x2, y2);
		double velx = traj[0], vely = traj[1];
		
		if(y2 < y1) vely = -vely;
		if(x2 < x1) velx = -velx;
		
		ret2[0] = x + velx;
		ret2[1] = y + vely;
		ret2[2] = velx;
		ret2[3] = vely;
		
		return ret2;
		
	}
	
	public double[] projectilePhysics(Area obstacle, Area projectile, double velocity, double x, double y, double x1, double y1, double x2, double y2) {
		projectile.intersect(obstacle);
		if(projectile.isEmpty()) ret2[4] = 1;
		else ret2[4] = 0;
		
		double[] traj = calcTrajectory(velocity, x1, y1, x2, y2);
		double velx = traj[0], vely = traj[1];
		
		if(y2 < y1) vely = -vely;
		if(x2 < x1) velx = -velx;
		
		ret2[0] = x + velx;
		ret2[1] = y + vely;
		ret2[2] = velx;
		ret2[3] = vely;
		
		return ret2;
		
	}
	
	public double[] bouncePhysics() {
		
		
		
		
		
		
		return null;
	}
	
	public static double[] calcTrajectory(double velocity, double x1, double y1, double x2, double y2) {
		double velx = 0, vely = 0;
		for(double i = 0; i < velocity; i += 0.1) {
			if( i + Math.abs(line(i, x1, y1, x2, y2)) >= velocity ) {
				velx = i;
				vely = Math.abs(line(i, x1, y1, x2, y2));
				break;
			}
		}
		double[] ret = {velx, vely};
		return ret;
	}
	
	public static double line(double x, double x1, double y1, double x2, double y2) {
		double l = (y2 - y1) / (x2 - x1);
		return l * x;
	}
	
	public Rectangle getRectangle(double a, double b, double c, double d) {
		return new Rectangle((int) a,(int) b, (int) c, (int) d);
	}
	
}
