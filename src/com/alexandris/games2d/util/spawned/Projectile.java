package com.alexandris.games2d.util.spawned;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import com.alexandris.games2d.util.graphics.BufferedImageLoader;
import com.alexandris.games2d.util.graphics.GameObject;
import com.alexandris.games2d.util.graphics.Handler;
import com.alexandris.games2d.util.graphics.ID;
import com.alexandris.games2d.util.physics.PhysicsPlatformer;

public class Projectile extends GameObject {
	
	private PhysicsPlatformer pp;
	private ParticleCreator pc;
	private Color color;
	private Handler handler;
	private ArrayList<Area> obstacles = new ArrayList<Area>();
	private double velx = 0, vely = 0;
	public int velocity;
	private int life_timer = 0;
	private boolean direction;
	private double[] ret = new double[5];
	private double mouseX = 0, mouseY = 0, playerX = 0, playerY = 0;
	BufferedImageLoader loader = new BufferedImageLoader();
	
	public Projectile(int x, int y, double velx, double vely, int size, ArrayList<Area> obstacles, ID id, Color color, PhysicsPlatformer pp, ParticleCreator pc, Handler handler) {
		super(x, y, id);
		this.pp = pp;
		this.pc = pc;
		this.handler = handler;
		this.velx = velx;
		this.vely = vely;
		if(size > 5) this.size = size;
		else this.size = 5;
		this.color = color;
		this.obstacles = obstacles;
		playerX = x;
		playerY = y;
	}
	
	public Projectile(int x, int y, int velocity, int size, double mouseX, double mouseY, ArrayList<Area> obstacles, ID id, Color color, PhysicsPlatformer pp, ParticleCreator pc, Handler handler) {
		super(x, y, id);
		this.pp = pp;
		this.pc = pc;
		this.handler = handler;
		this.velocity = velocity;
		if(size > 5) this.size = size;
		else this.size = 5;
		this.color = color;
		this.obstacles = obstacles;
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		playerX = x;
		playerY = y;
	}

	@Override
	public void tick() {
		life_timer++;
		if(getId() == ID.Enemy_Projectile) ret = pp.projectilePhysics(obstacles, new Area(getBounds()), x, y, velx, vely + 0.5f);
		if(getId() == ID.Projectile) ret = pp.projectilePhysics(obstacles.get(0), new Area(getBounds()), velocity, x, y, playerX, playerY, mouseX, mouseY);
		x = ret[0];
		y = ret[1];
		velx = ret[2];
		vely = ret[3];
		if(velx > 0) direction = true;
		else if(velx < 0) direction = false;
		if(ret[4] == 0 || life_timer > 80) handler.removeObject(this);
		
		
		if(getId() == ID.Projectile) {
			if(direction) for(int i = 0; i < 10; i++) pc.spawn((int) x - 2, (int) y + size / 2, Color.WHITE, 3, 2, 2.2, 's');
			else for(int i = 0; i < 10; i++) pc.spawn((int) x + size + 2, (int) y + size / 2, Color.WHITE, 3, 2, 2.2, 's');
			
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Enemy_Projectile) {
					if(getArea().intersects(tempObject.getBounds())) {
						handler.removeObject(tempObject);
						handler.removeObject(this);
					}
				}
			}
			
		}
		else if(getId() == ID.Enemy_Projectile) {
			if(direction) for(int i = 0; i < 10; i++) pc.spawn((int) x + size / 2, (int) y + size / 2, Color.BLACK, 15, 5, 2.2, 'c');
			else for(int i = 0; i < 10; i++) pc.spawn((int) x + size / 2, (int) y + size / 2, Color.BLACK, 15, 5, 2.2, 'c');
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		Graphics2D g2d;
		g2d = (Graphics2D) g;
		//g2d.drawImage(loader.loadImage("/textures/attack1.png"), (int) x, (int) y, size, size, null);
		g2d.fillOval((int) x, (int) y, size, size);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, this.size, this.size);
	}

	@Override
	public Area getArea() {
		return new Area(new Ellipse2D.Double((int) x, (int) y, size, size));
	}

}
