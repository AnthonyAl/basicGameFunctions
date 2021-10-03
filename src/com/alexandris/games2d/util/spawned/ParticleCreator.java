package com.alexandris.games2d.util.spawned;

import java.awt.Color;

import com.alexandris.games2d.util.graphics.Handler;
import com.alexandris.games2d.util.graphics.ID;

public class ParticleCreator {
	
	Handler handler;

	public ParticleCreator(Handler handler) {
		this.handler = handler;
	}
	
	public void spawn(int num, int x, int y, Color[] color, int size, int offset, int spawn_offsetX, int spawn_offsetY, int size_offset, double time, int gravityX, int gravityY, char shape) {
		for(int i = 0; i < num; i++) {
			handler.addObject(new Particles(x, y, ID.Particles, handler, color, size, offset, spawn_offsetX, spawn_offsetY, size_offset, time, gravityX, gravityY, shape));
		}
	}

	public void spawn(int x, int y, Color color, int offset, int size, double time, char shape) {
		Color[] c = {color};
		for(int i = 0; i < 1; i++) {
			handler.addObject(new Particles(x, y, ID.Particles, handler, c, size, offset, 1, 1, 1, time, 0, 0, shape));
		}
	}
	
	
}
