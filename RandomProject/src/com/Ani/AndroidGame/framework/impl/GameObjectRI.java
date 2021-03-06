package com.Ani.AndroidGame.framework.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.Ani.AndroidGame.Common.CollisionParameters;
import com.Ani.AndroidGame.Common.UtilityFunction;
import com.Ani.AndroidGame.Main.GameObjectRegistry;
import com.Ani.AndroidGame.Math.Vector2;
import com.Ani.AndroidGame.Time.TimeSystem;

/**
 * GameObject defines any object that resides in the game world (character, background, special
 * effect, enemy, etc).  It is a collection of GameComponents which implement its behavior;
 * GameObjects themselves have no intrinsic behavior.  GameObjects are also "bags of data" that
 * components can use to share state (direct component-to-component communication is discouraged).
 */
public class GameObjectRI{
  @Autowired
  GameObjectRegistry gameObjectRegistry;
  
	private final static float COLLISION_SURFACE_DECAY_TIME = 0.3f;
    // These fields are managed by components.
    private Vector2 mPosition;
    private Vector2 mVelocity;
    private Vector2 mTargetVelocity;
    private Vector2 mAcceleration;
    private Vector2 mImpulse;

    private Vector2 mBackgroundCollisionNormal;

    private float mLastTouchedFloorTime;
    private float mLastTouchedCeilingTime;
    private float mLastTouchedLeftWallTime;
    private float mLastTouchedRightWallTime;
  
    public boolean positionLocked;
    
    public float activationRadius;
    public boolean destroyOnDeactivation;
    
    public int life;
    
    public int lastReceivedHitType;
    
    public Vector2 facingDirection;
    public float width;
    public float height;
    
    private static final int DEFAULT_LIFE = 1;
    
    public enum ActionType {
        INVALID,
        IDLE,
        MOVE,
        ATTACK,
        HIT_REACT,
        DEATH,
        HIDE,
        FROZEN
    }
    
    private ActionType mCurrentAction;
    
    public enum Team {
        NONE,
        PLAYER,
        ENEMY
    }
    
    public Team team;
    
    public GameObjectRI() {
       

        mPosition = new Vector2();
        mVelocity = new Vector2();
        mTargetVelocity = new Vector2();
        mAcceleration = new Vector2();
        mImpulse = new Vector2();
        mBackgroundCollisionNormal = new Vector2();
        
        facingDirection = new Vector2(1, 0);
        
        reset();
    }    
  
    public void reset() {
     
        
        mPosition.zero(0,0);
        mVelocity.zero(0,0);
        mTargetVelocity.zero(0,0);
        mAcceleration.zero(0,0);
        mImpulse.zero(0,0);
        mBackgroundCollisionNormal.zero(0,0);
        facingDirection.set(1.0f, 1.0f);
        
        mCurrentAction = ActionType.INVALID;
        positionLocked = false;
        activationRadius = 0;
        destroyOnDeactivation = false;
        life = DEFAULT_LIFE;
        team = Team.NONE;
        width = 0.0f;
        height = 0.0f;
        
        lastReceivedHitType = CollisionParameters.INVALID.getValue();
    }
    
    // Utility functions
    public final boolean touchingGround() {
        final TimeSystem time = gameObjectRegistry.timeSystem;
        final float gameTime = time.getGameTime();
        final boolean touching = gameTime > 0.1f &&
        		UtilityFunction.close(mLastTouchedFloorTime, time.getGameTime(), COLLISION_SURFACE_DECAY_TIME);
        return touching;
    }
    
    public final boolean touchingCeiling() {
        final TimeSystem time = gameObjectRegistry.timeSystem;
        final float gameTime = time.getGameTime();
        final boolean touching = gameTime > 0.1f && 
            UtilityFunction.close(mLastTouchedCeilingTime, time.getGameTime(), COLLISION_SURFACE_DECAY_TIME);
        return touching;
    }
    
    public final boolean touchingLeftWall() {
        final TimeSystem time = gameObjectRegistry.timeSystem;
        final float gameTime = time.getGameTime();
        final boolean touching = gameTime > 0.1f &&
        		UtilityFunction.close(mLastTouchedLeftWallTime, time.getGameTime(), COLLISION_SURFACE_DECAY_TIME);
        return touching;
    }
    
    public final boolean touchingRightWall() {
        final TimeSystem time = gameObjectRegistry.timeSystem;
        final float gameTime = time.getGameTime();
        final boolean touching = gameTime > 0.1f &&
        		UtilityFunction.close(mLastTouchedRightWallTime, time.getGameTime(), COLLISION_SURFACE_DECAY_TIME);
        return touching;
    }

    public final Vector2 getPosition() {
        return mPosition;
    }

    public final void setPosition(Vector2 position) {
        mPosition.set(position);
    }
    
    public final float getCenteredPositionX() {
        return mPosition.x + (width / 2.0f);
    }
    
    public final float getCenteredPositionY() {
        return mPosition.y + (height / 2.0f);
    }

    public final Vector2 getVelocity() {
        return mVelocity;
    }

    public final void setVelocity(Vector2 velocity) {
        mVelocity.set(velocity);
    }

    public final Vector2 getTargetVelocity() {
        return mTargetVelocity;
    }

    public final void setTargetVelocity(Vector2 targetVelocity) {
        mTargetVelocity.set(targetVelocity);
    }

    public final Vector2 getAcceleration() {
        return mAcceleration;
    }

    public final void setAcceleration(Vector2 acceleration) {
        mAcceleration.set(acceleration);
    }

    public final Vector2 getImpulse() {
        return mImpulse;
    }

    public final void setImpulse(Vector2 impulse) {
        mImpulse.set(impulse);
    }

    public final Vector2 getBackgroundCollisionNormal() {
        return mBackgroundCollisionNormal;
    }

    public final void setBackgroundCollisionNormal(Vector2 normal) {
        mBackgroundCollisionNormal.set(normal);
    }

    public final float getLastTouchedFloorTime() {
        return mLastTouchedFloorTime;
    }

    public final void setLastTouchedFloorTime(float lastTouchedFloorTime) {
        mLastTouchedFloorTime = lastTouchedFloorTime;
    }

    public final float getLastTouchedCeilingTime() {
        return mLastTouchedCeilingTime;
    }

    public final void setLastTouchedCeilingTime(float lastTouchedCeilingTime) {
        mLastTouchedCeilingTime = lastTouchedCeilingTime;
    }

    public final float getLastTouchedLeftWallTime() {
        return mLastTouchedLeftWallTime;
    }

    public final void setLastTouchedLeftWallTime(float lastTouchedLeftWallTime) {
        mLastTouchedLeftWallTime = lastTouchedLeftWallTime;
    }

    public final float getLastTouchedRightWallTime() {
        return mLastTouchedRightWallTime;
    }

    public final void setLastTouchedRightWallTime(float lastTouchedRightWallTime) {
        mLastTouchedRightWallTime = lastTouchedRightWallTime;
    }
    
    public final ActionType getCurrentAction() {
        return mCurrentAction;
    }
    
    public final void setCurrentAction(ActionType type) {
        mCurrentAction = type;
    }
}
