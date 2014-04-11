package com.Ani.AndroidGame.framework.impl;

import java.util.List;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import com.Ani.AndroidGame.RIFrameWork.BaseObject;
import com.Ani.AndroidGame.framework.Input;

public class AndroidInput extends BaseObject implements Input {    
    AccelerometerHandler accelHandler;
    KeyboardHandler keyHandler;
    TouchHandler touchHandler;
	private int mScreenRotation;

    public AndroidInput(Context context, View view, float scaleX, float scaleY) {
        accelHandler = new AccelerometerHandler(context);
        keyHandler = new KeyboardHandler(view);               
        if(Integer.parseInt(VERSION.SDK) < 5) 
            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        else
            touchHandler = new MultiTouchHandler(view, scaleX, scaleY);        
    }

 
    public boolean isKeyPressed(int keyCode) {
        return keyHandler.isKeyPressed(keyCode);
    }

 
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

 
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

 
    public float getAccelX() {
        return accelHandler.getAccelX();
    }

    
    public float getAccelY() {
        return accelHandler.getAccelY();
    }

 
    public float getAccelZ() {
        return accelHandler.getAccelZ();
    }

 
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }
    

    public List<EngineKeyEvent> getKeyEvents() {
        return keyHandler.getKeyEvents();
    }

    public void setScreenRotation(int rotation) {
		mScreenRotation = rotation;
	}
    
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
