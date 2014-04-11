package com.Ani.AndroidGame.RIFrameWork;

import com.Ani.AndroidGame.Math.Vector2;
import com.Ani.AndroidGame.framework.impl.InputButton;

public class InputXY {
	private InputButton x;
	private InputButton y;
	
	public InputXY(){
		x = new InputButton();
		y = new InputButton();
	}
	
	public InputXY(InputButton x, InputButton y){
		this.x = x;
		this.y = y;
		
	}
	
	public final void press(float currentTime, float x, float y){
		this.x.press(currentTime, x);
		this.y.press(currentTime, y);
	}
	
	public final void release(){
		x.release();
		y.release();
	}
	public boolean getPressed() {
		return x.getPressed() || y.getPressed();
	}
	
	
	public boolean getTriggered(float time){
		return x.getPressed() || y.getPressed();
	}
	public final void setVector(Vector2 vector){
		vector.x = x.getMagnitude();
		vector.y = y.getMagnitude();
		
	}
	
	public final float getX(){
		return x.getMagnitude();
	}
	public final float getY() {
		return y.getMagnitude();
	}
	
	public final float getLastPressedTime() {
		return Math.max(x.getLastPressedTime(), y.getLastPressedTime());
	}
	
	public final void releaseX() {
		x.release();
	}
	
	public final void releaseY() {
		y.release();
	}
	

	public void setMagnitude(float x, float y) {
		this.x.setMagnitude(x);
		this.y.setMagnitude(y);
	}
	
	public void reset() {
		x.reset();
		y.reset();
	}
	
	public void clone(InputXY other) {
		if (other.getPressed()) {
			press(other.getLastPressedTime(), other.getX(), other.getY());
		} else {
			release();
		}
	}
}