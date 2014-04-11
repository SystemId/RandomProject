package com.Ani.AndroidGame.framework.impl;


import com.Ani.AndroidGame.Common.CollisionParameters;
import com.Ani.AndroidGame.Common.MemoryLeakTracker;

public class CollisionVolume extends MemoryLeakTracker{
	
	public int hitType;
	
	public CollisionVolume(){
		super();
		hitType = CollisionParameters.HIT.getValue() ;
	}
	
	public void setHitType(int type) {
		 hitType = type;
	}
	    
	public int getHitType() {
	    return hitType;
	}
	    
	

}
