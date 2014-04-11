package com.Ani.AndroidGame.Common;

public class GameConstants {
	
	public enum Animations {
		INVALID,
		IDLE,
        MOVE,
        MOVE_FAST,
        STOMP,
        HIT_REACT,
        DEATH,
        FROZEN,
        JUGANAG
    }
    
	public enum PlayerWeapons{
		PISTOL,
		SHOTGUN,
		MINI_MACHINE_GUN	
		
	}
	
	  public enum ComponentPhases {
	        THINK,                  // decisions are made
	        PHYSICS,                // impulse velocities are summed
	        POST_PHYSICS,           // inertia, friction, and bounce
	        MOVEMENT,               // position is updated
	        COLLISION_DETECTION,    // intersections are detected
	        COLLISION_RESPONSE,     // intersections are resolved
	        POST_COLLISION,         // position is now final for the frame
	        ANIMATION,              // animations are selected
	        PRE_DRAW,               // drawing state is initialized
	        DRAW,                   // drawing commands are scheduled.
	        FRAME_END,              // final cleanup before the next update
	    }
	    
	  
	    public static final int FLY_BUTTON_REGION_X = 0;
	    public static final int FLY_BUTTON_REGION_Y = 0;
	    public static final int FLY_BUTTON_REGION_WIDTH = 90; //128 - 24;
	    public static final int FLY_BUTTON_REGION_HEIGHT = 256;
	    
	    public static final int STOMP_BUTTON_REGION_X = 100;
	    public static final int STOMP_BUTTON_REGION_Y = 0;
	    public static final int STOMP_BUTTON_REGION_WIDTH = 70;
	    public static final int STOMP_BUTTON_REGION_HEIGHT = 80;
	    
	    public static final int MOVEMENT_SLIDER_REGION_X = 0;
	    public static final int MOVEMENT_SLIDER_REGION_Y = 0;
	    public static final int MOVEMENT_SLIDER_REGION_WIDTH = 220;
	    public static final int MOVEMENT_SLIDER_REGION_HEIGHT = 200;
	    public static final int MOVEMENT_SLIDER_BAR_WIDTH = 140;
	    public static final int MOVEMENT_SLIDER_X = 20;
	
	    public enum ObjectType{
	    	NONE,
	    	PLAYER,
	    	ENEMY;
	    }
	

}
