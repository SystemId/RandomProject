package com.Ani.AndroidGame.framework.impl;

import com.Ani.AndroidGame.Math.Rectangle;
import com.Ani.AndroidGame.Math.Vector2;
import com.Ani.AndroidGame.framework.DynamicGameObject;

public class DynamicGameObjectImpl implements DynamicGameObject{
	
	private Vector2 position;
	private Rectangle bounds;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	public Vector2 getVelocity() {
		return velocity;
	}
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	public Vector2 getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}


	
	

}
