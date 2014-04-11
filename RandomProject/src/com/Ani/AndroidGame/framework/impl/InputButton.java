package com.Ani.AndroidGame.framework.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.Ani.AndroidGame.Main.GameObjectRegistry;
import com.Ani.AndroidGame.RIFrameWork.BaseObject;

public class InputButton {
	
	@Autowired
	GameObjectRegistry gameObjectRegistry;
	
	private boolean down;
	private float lastPressedTime;
	private float downTime;
	private float magnitude;
	
	public void press(float currentTime, float magnitude) {
		if (!down) {
			down = true;
			downTime = currentTime;
		} 
		this.magnitude = magnitude;
		lastPressedTime = currentTime;
	}
	
	public void release() {
		down = false;
	}

	public final boolean getPressed() {
		return down;
	}
	public final boolean getTriggered(float currentTime) {
		return down && currentTime - downTime <= gameObjectRegistry.timeSystem.getFrameDelta() * 2.0f;
	}
	public final float getPressedDuration(float currentTime) {
		return currentTime - downTime;
	}
	public final float getLastPressedTime() {
		return lastPressedTime;
	}
	public final float getMagnitude() {
		float magnitude = 0.0f;
		if (down) {
			this.magnitude = magnitude;
		}
		return magnitude;
	}

	public final void setMagnitude(float magnitude) {
		this.magnitude = magnitude;
	}
	
	public final void reset() {
		down = false;
		magnitude = 0.0f;
		lastPressedTime = 0.0f;
		downTime = 0.0f;
	}
	
	
	
}
