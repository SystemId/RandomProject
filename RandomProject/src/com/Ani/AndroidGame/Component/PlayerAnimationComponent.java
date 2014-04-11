package com.Ani.AndroidGame.Component;


import com.Ani.AndroidGame.Common.GameConstants.Animations;
import com.Ani.AndroidGame.Common.GameConstants.PlayerWeapons;
import com.Ani.AndroidGame.framework.Sound;
import com.Ani.AndroidGame.framework.impl.AndroidSound;
import com.Ani.AndroidGame.framework.impl.DynamicGameObjectImpl;

public class PlayerAnimationComponent  {
	
	private Animations playerState;
	private PlayerWeapons playerWeapons;
			
	public DynamicGameObjectImpl getDyanamicObject() {
		return dyanamicObject;
	}


	public void setDyanamicObject(DynamicGameObjectImpl dyanamicObject) {
		this.dyanamicObject = dyanamicObject;
	}


	public Animations getPlayerState() {
		return playerState;
	}


	public void setPlayerState(Animations playerState) {
		this.playerState = playerState;
	}
	
	

	DynamicGameObjectImpl dynamicObject;
	
	
	private static final float RUN_SPEED = 1.0f; 
	private static final float WALK_SPEED = 0.5f;
	private static final float RELOAD_DELAY_SLOW = 0.01f;
	private static final float RELOAD_DELAY_FAST = 0.005f;
	private static final int MAX_HITS = 3;
	private static final int JUGANAG_HITS = 5;
	
	
	
	
	final int STARTING_GUN_AMMO = 10;
	
	private Sound pistolShot;
	private Sound Shotgun;
	private Sound machineGun;
			
	public PlayerAnimationComponent(){
		
	}
		
			
	public void reset(){
		
		dynamicObject = null;
		
		
		
		
		
		
		
	}
	
	public void update(float timeDelta, DynamicGameObjectImpl dynamicGameObject){
		//updateSound(timeDelta, dynamicGameObject);
		
		if(dynamicObject != null){
			
			final float velocityX = dynamicObject.getVelocity().x;
			final float velocityY = dynamicObject.getVelocity().y;
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
	}



}
