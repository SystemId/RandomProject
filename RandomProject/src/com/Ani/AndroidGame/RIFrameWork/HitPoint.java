package com.Ani.AndroidGame.RIFrameWork;

import com.Ani.AndroidGame.Common.MemoryLeakTracker;
import com.Ani.AndroidGame.Math.Vector2;

public class HitPoint extends MemoryLeakTracker {
	public Vector2 hitPoint;
	public Vector2 hitNormal;
	
	public HitPoint(){
		super();
	}
	
	 public final void reset() {
	        hitPoint = null;
	        hitNormal = null;
	    }
}
