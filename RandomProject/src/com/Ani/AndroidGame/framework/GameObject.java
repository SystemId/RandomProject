package com.Ani.AndroidGame.framework;

import com.Ani.AndroidGame.Math.Rectangle;
import com.Ani.AndroidGame.Math.Vector2;

/**
 * Ccode used to handle objects or compoenents that don't move
 */



public interface GameObject {
	
    public Vector2 getPosition();
    public void setPosition(Vector2 position);
    public Rectangle getBounds();
    public void setBounds(Rectangle bounds);
    
    
 
}
