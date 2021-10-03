package com.alexandris.games2d.util.spawned;

import java.awt.Color;
import java.awt.geom.Area;
import java.util.ArrayList;

import com.alexandris.games2d.util.graphics.Handler;
import com.alexandris.games2d.util.graphics.ID;
import com.alexandris.games2d.util.physics.*;

public class ProjectileCreator {
	
	Handler handler;
	PhysicsPlatformer pp;
	ParticleCreator pc;

	public ProjectileCreator(Handler handler, PhysicsPlatformer pp, ParticleCreator pc) {
		this.handler = handler;
		this.pp = pp;
		this.pc = pc;
	}
	
	public void spawn(int id, ArrayList<Area> tempList, Color color, int size, int speed, float x1, float y1, float x2, float y2) {
		double[] traj = PhysicsPlatformer.calcTrajectory(25, x1, y1, x2, y2);
		double velx = traj[0];
		double vely = traj[1];

		if(y2 < y1) vely = -vely;
		if(x2 < x1) velx = -velx;
		
		handler.addObject(new Projectile((int) x1, (int) y1, velx, vely, size, tempList, ID.values()[id], color, pp, pc, handler));
	}
	
}
