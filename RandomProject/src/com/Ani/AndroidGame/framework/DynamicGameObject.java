package com.Ani.AndroidGame.framework;

import com.Ani.AndroidGame.Math.Vector2;

/**
 * Code used to handle moving objects or compoenents 
 */


public interface DynamicGameObject extends GameObject {
    
	public Vector2 getVelocity();
    public void setVelocity(Vector2 velocity);
    public void setAcceleration(Vector2 acceleration);
    public Vector2 getAcceleration();   
   
}
