package com.Ani.AndroidGame.RIFrameWork;

import com.Ani.AndroidGame.Math.Rectangle;
import com.Ani.AndroidGame.Math.Vector2;
import com.Ani.AndroidGame.OpenGl.Texture;
import com.Ani.AndroidGame.framework.GameObject;
import com.Ani.AndroidGame.framework.Pool;

public abstract class DrawableObject {
	
	  private float mPriority;
	    private Pool mParentPool;
	    
	    public abstract void draw(float x, float y, float scaleX, float scaleY);

	    public DrawableObject() {
	        super();
	    }
	    
	    public void setPriority(float f) {
	        mPriority = f;
	    }

	    public float getPriority() {
	        return mPriority;
	    }

	    public void setParentPool(Pool pool) {
	        mParentPool = pool;
	    }

	    public Pool getParentPool() {
	        return mParentPool;
	    }
	    
	    // Override to allow drawables to be sorted by texture.
	    public Texture getTexture() {
	        return null;
	    }
	    
	    // Function to allow drawables to specify culling rules.
	    public boolean visibleAtPosition(Vector2 position) {
	        return true;
	    }
	
	

}
