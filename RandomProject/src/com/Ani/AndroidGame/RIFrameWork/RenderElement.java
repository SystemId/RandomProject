package com.Ani.AndroidGame.RIFrameWork;

import com.Ani.AndroidGame.Common.UtilityFunction;
import com.Ani.AndroidGame.Math.Vector2;
import com.Ani.AndroidGame.OpenGl.Texture;

 public class RenderElement extends PhasedObject  {
	 
	 private static final int TEXTURE_SORT_BUCKET_SIZE = 1000;
		 public DrawableObject mDrawable;
		 public float x;
		 public float y;
		 public boolean cameraRelative;
		 
		 public RenderElement() {
	       super();
        }

	    public void set(DrawableObject drawable, Vector2 position, int priority, boolean isCameraRelative) {
	        mDrawable = drawable;
	         x = position.x;
	         y = position.y;
	        cameraRelative = isCameraRelative;
	        final int sortBucket = priority * TEXTURE_SORT_BUCKET_SIZE;
	        int sortOffset = 0;
	        if (drawable != null) {
	            Texture tex = drawable.getTexture();
	           if (tex != null) {
	               sortOffset = (tex.textureId % TEXTURE_SORT_BUCKET_SIZE) * UtilityFunction.sign(priority);
	              }
	          }
	           setPhase(sortBucket + sortOffset);
	        }
	    
	    public void reset() {
	          mDrawable = null;
	          x = 0.0f;
	          y = 0.0f;
	          cameraRelative = false;
	      }
	    	
	    	
		  
	    
	 }