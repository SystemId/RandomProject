package com.Ani.AndroidGame.Common;

import com.Ani.AndroidGame.RIFrameWork.BaseObject;

import android.content.Context;

public class AndroidContext extends BaseObject{
	
	 public int viewWidth;
	 public int viewHeight;
	 public Context context;
     public int gameWidth;
	 public int gameHeight;
	 public float viewScaleX;
	 public float viewScaleY;
	 public boolean supportsDrawTexture;
	 public boolean supportsVBOs;
	 public int difficulty;

	 
	 @Override
	public void reset() {
				
	}

}
