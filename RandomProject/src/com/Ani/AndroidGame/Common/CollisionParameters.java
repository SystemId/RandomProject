package com.Ani.AndroidGame.Common;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


	public enum CollisionParameters{
		NO_TOUCH(0),
		HIT(1),
		INSTANT_DEATH(2),
		INVALID(3);
		
	    private int code;
	    CollisionParameters(int code) { this.code = code; }
	    public int getValue() { return code; }
		
	    private void set(int code) {
          this.code = code;
     }

}