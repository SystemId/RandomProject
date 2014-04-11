package com.Ani.AndroidGame.framework.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.Ani.AndroidGame.Common.CollisionParameters;
import com.Ani.AndroidGame.Common.GameConstants.Animations;
import com.Ani.AndroidGame.Common.GameConstants.ObjectType;
import com.Ani.AndroidGame.Common.UtilityFunction;
import com.Ani.AndroidGame.Main.GameObjectRegistry;
import com.Ani.AndroidGame.Math.Rectangle;
import com.Ani.AndroidGame.Math.Vector2;
import com.Ani.AndroidGame.Time.TimeSystem;
import com.Ani.AndroidGame.framework.GameObject;

public class GameObjectImpl implements GameObject {
	private final static float COLLISION_SURFACE_DECAY_TIME = 0.3f;
	
	@Autowired
	GameObjectRegistry gameObjectRegistry;
	
	private Vector2 position;

	private Vector2 velocity;
	private Vector2 goalVelocity;
	private Vector2 acceleration;
	private Animations currentAnimation;
	private ObjectType type;
	public float width;
	public float height;
	
	private float lastTouchedFloorTime;
	private float lastTouchedCeilingTime;
	private float lastTouchedLeftWallTime;
	private float lastTouchedRightWallTime;
	
	public Vector2 facingDirection;
	public int lastReceivedHitType;
	
	private Rectangle bounds;
	
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	public Vector2 getGoalVelocity() {
		return goalVelocity;
	}
	public void setGoalVelocity(Vector2 goalVelocity) {
		this.goalVelocity = goalVelocity;
	}
	public Vector2 getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}
	public Animations getCurrentAnimation() {
		return currentAnimation;
	}
	public void setCurrentAnimation(Animations currentAnimation) {
		this.currentAnimation = currentAnimation;
	}
	public ObjectType getType() {
		return type;
	}
	public void setType(ObjectType type) {
		this.type = type;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getLastTouchedFloorTime() {
		return lastTouchedFloorTime;
	}
	public void setLastTouchedFloorTime(float lastTouchedFloorTime) {
		this.lastTouchedFloorTime = lastTouchedFloorTime;
	}
	public float getLastTouchedCeilingTime() {
		return lastTouchedCeilingTime;
	}
	public void setLastTouchedCeilingTime(float lastTouchedCeilingTime) {
		this.lastTouchedCeilingTime = lastTouchedCeilingTime;
	}
	public float getLastTouchedLeftWallTime() {
		return lastTouchedLeftWallTime;
	}
	public void setLastTouchedLeftWallTime(float lastTouchedLeftWallTime) {
		this.lastTouchedLeftWallTime = lastTouchedLeftWallTime;
	}
	public float getLastTouchedRightWallTime() {
		return lastTouchedRightWallTime;
	}
	public void setLastTouchedRightWallTime(float lastTouchedRightWallTime) {
		this.lastTouchedRightWallTime = lastTouchedRightWallTime;
	}
	public Vector2 getFacingDirection() {
		return facingDirection;
	}
	public void setFacingDirection(Vector2 facingDirection) {
		this.facingDirection = facingDirection;
	}
	public int getLastReceivedHitType() {
		return lastReceivedHitType;
	}
	public void setLastReceivedHitType(int lastReceivedHitType) {
		this.lastReceivedHitType = lastReceivedHitType;
	}
	public static float getCollisionSurfaceDecayTime() {
		return COLLISION_SURFACE_DECAY_TIME;
	}
	
	public GameObjectImpl(){
		position = new Vector2();
		velocity = new Vector2();
		goalVelocity = new Vector2();
		acceleration = new Vector2();
		facingDirection = new Vector2();
		
	}
	
	public void reset(){
		position.set(0, 0);
		velocity.set(0, 0);
		goalVelocity.set(0, 0);
		acceleration.set(0, 0);
		facingDirection.set(0, 0);
		
		currentAnimation = Animations.INVALID;
		lastReceivedHitType = CollisionParameters.INVALID.getValue();		
				
	}
	
	public final boolean touchingGround(){
		final TimeSystem time = gameObjectRegistry.timeSystem;
		final float gameTime = time.getGameTime();
		final boolean touching = gameTime > 0.1f && UtilityFunction.close(lastTouchedFloorTime, time.getGameTime(), COLLISION_SURFACE_DECAY_TIME);
		return touching;
	}
	public final boolean touchingCeiling(){
		final TimeSystem time = gameObjectRegistry.timeSystem;
		final float gameTime = time.getGameTime();
		final boolean touching = gameTime > 0.1f && UtilityFunction.close(lastTouchedCeilingTime, time.getGameTime(), COLLISION_SURFACE_DECAY_TIME);
		return touching;
	}
	public final boolean touchingLeftWall(){
		final TimeSystem time = gameObjectRegistry.timeSystem;
		final float gameTime = time.getGameTime();
		final boolean touching = gameTime > 0.1f && UtilityFunction.close(lastTouchedLeftWallTime, time.getGameTime(), COLLISION_SURFACE_DECAY_TIME);
		return touching;
	}
	public final boolean touchingRightWall(){
		final TimeSystem time = gameObjectRegistry.timeSystem;
		final float gameTime = time.getGameTime();
		final boolean touching = gameTime > 0.1f && UtilityFunction.close(lastTouchedRightWallTime, time.getGameTime(), COLLISION_SURFACE_DECAY_TIME);
		return touching;
	}


}
