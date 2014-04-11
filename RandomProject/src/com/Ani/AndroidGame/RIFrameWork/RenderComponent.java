package com.Ani.AndroidGame.RIFrameWork;

import com.Ani.AndroidGame.Common.GameConstants.ComponentPhases;
import com.Ani.AndroidGame.Math.Vector2;

/** 
 did not implement Drawable component from replica island code
 
 */

public class RenderComponent {
	private DrawableObject drawable; 
	private int priority;
	private boolean cameraRelative;
	private Vector2 positionWorkSpace;
	private Vector2 screenLocation;
	private Vector2 drawOffset;
	private int phase;
	
	public RenderComponent(){
		
		setPhase(ComponentPhases.DRAW.ordinal());
		positionWorkSpace = new Vector2();
		screenLocation = new Vector2();
		drawOffset = new Vector2();
		reset();
		
	}

	private void setPhase(int phaseValue) {
		phase = phaseValue;
		
	}

	private void reset() {
		priority = 0;
		cameraRelative = true;
		drawOffset.set(0.0f, 0.0f);
				
	}
	
	public void update(float timeDelta){
		if(drawable != null){
			RenderSystem system 
		}
		
		
		
		
		
	}
	
	 public void setPriority(int mpriority) {
		priority = mpriority;
    }
    
    public int getPriority() {
        return priority;
    }

    public void setCameraRelative(boolean relative) {
        cameraRelative = relative;
    }
    
    public void setDrawOffset(float x, float y) {
        drawOffset.set(x, y);
    }
	

}
