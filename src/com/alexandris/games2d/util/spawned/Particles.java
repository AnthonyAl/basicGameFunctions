package com.alexandris.games2d.util.spawned;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.Random;

import com.alexandris.games2d.util.graphics.GameObject;
import com.alexandris.games2d.util.graphics.Handler;
import com.alexandris.games2d.util.graphics.ID;

public class Particles extends GameObject {
	
	Color color;
	Handler handler;
	Random random = new Random();
	int size, offset, spawn_offsetX, spawn_offsetY, size_offset, counter, gravityX = 0, gravityY = 0;
	char shape;
	double time;
	
	public Particles(int x, int y, ID id, Handler handler, Color[] color, int size, int offset, int spawn_offsetX, int spawn_offsetY, int size_offset, double time, int gravityX, int gravityY, char shape) {
		super(x, y, id);
		if(time < 0) time = 0;
		if(size < 0) size = 0;
		if(offset <= 0) offset = 1;
		if(spawn_offsetX <= 0) spawn_offsetX = 1;
		if(spawn_offsetY <= 0) spawn_offsetY = 1;
		int i = random.nextInt(color.length);
		if(color.length > 0) this.color = color[i];
		else this.color = Color.red;
		this.size = size;
		this.offset = offset;
		this.spawn_offsetX = spawn_offsetX;
		this.spawn_offsetY = spawn_offsetY;
		this.size_offset = size_offset;
		this.counter = 0;
		this.time = random.nextInt(10) * time;
		this.handler = handler;
		this.gravityX = gravityX;
		this.gravityY = gravityY;
		this.shape = shape;
	}

	@Override
	public void tick() {
		int i = random.nextInt(offset)  - (int)(offset / 2) + gravityX;
		int j = random.nextInt(offset) - (int)(offset / 2) + gravityY;
		x += i;
		y += j;
		counter++;
	}

	@Override
	public void render(Graphics g) {
		if(counter < time) {
			g.setColor(color);
			if(shape == 's') g.fillRect((int) x + random.nextInt(spawn_offsetX) - (int)(spawn_offsetX / 2), (int) y + random.nextInt(spawn_offsetY) - (int)(spawn_offsetY / 2), size + random.nextInt(size_offset) - (int)(size_offset / 2), size+ random.nextInt(size_offset) - (int)(size_offset / 2));
			else if(shape == 'c') g.fillOval((int) x + random.nextInt(spawn_offsetX) - (int)(spawn_offsetX / 2), (int) y + random.nextInt(spawn_offsetY) - (int)(spawn_offsetY / 2), size + random.nextInt(size_offset) - (int)(size_offset / 2), size+ random.nextInt(size_offset) - (int)(size_offset / 2));
		}
		else {
			handler.removeObject(this);
		}

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, size, size);
	}

	@Override
	public Area getArea() {
		// TODO Auto-generated method stub
		return null;
	}

}
