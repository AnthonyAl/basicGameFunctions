package com.alexandris.games2d.util.graphics;

public class Camera {
	
	private double x, y;
	private int width = 0, height = 0, multiplier = 1;

	public Camera(double x, double y, int width, int height, int multiplier) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		if(multiplier > 1) this.multiplier = multiplier;
		// TODO Auto-generated constructor stub
	}

	public void tick(GameObject object, int width, int height) {
		
		x += ((object.getX() + object.size / 2 - x) - width / 2) * 0.05f;
		y += ((object.getY() + object.size / 2 - y) - height / 2) * 0.05f;
		
		if(x <= 0) x = 0;
		if(x >= this.width * multiplier - width + object.size / 2 - 10) x = this.width * multiplier - width + object.size / 2 - 10;
		if(y <= 0) y = 0;
		if(y >= this.height * multiplier - height + object.size / 2 + 10) y = this.height * multiplier - height + object.size / 2 + 10;
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}
