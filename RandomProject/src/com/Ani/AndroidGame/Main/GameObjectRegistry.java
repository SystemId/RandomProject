package com.Ani.AndroidGame.Main;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.Ani.AndroidGame.Common.AndroidContext;
import com.Ani.AndroidGame.Common.BufferLibrary;
import com.Ani.AndroidGame.Level.LevelBuilder;
import com.Ani.AndroidGame.Level.LevelSystem;
import com.Ani.AndroidGame.Math.CollistionDetection;
import com.Ani.AndroidGame.Math.OverlapTester;
import com.Ani.AndroidGame.OpenGl.Camera2D;
import com.Ani.AndroidGame.RIFrameWork.BaseObject;
import com.Ani.AndroidGame.RIFrameWork.HitPointPool;
import com.Ani.AndroidGame.Time.TimeSystem;
import com.Ani.AndroidGame.framework.InputGameInterface;
import com.Ani.AndroidGame.framework.Pool;
import com.Ani.AndroidGame.framework.impl.AndroidAudio;
import com.Ani.AndroidGame.framework.impl.AndroidInput;
import com.Ani.AndroidGame.framework.impl.AndroidMusic;
import com.Ani.AndroidGame.framework.impl.AndroidSound;
import com.Ani.AndroidGame.framework.impl.GLGame;
import com.Ani.AndroidGame.framework.impl.GameObjectImpl;
import com.Ani.AndroidGame.framework.impl.InputSystem;

@Service("gameObjectRegistry")
public class GameObjectRegistry {
	
	public Camera2D camera2D;
	public OverlapTester overlapColision;
    public LevelBuilder levelBuilder;
    public LevelSystem levelSystem;
    public Pool pool;
    public BufferLibrary bufferLibrary;
    public AndroidContext androidContext;
    public InputGameInterface inputGameInterface;
    public AndroidInput androidInput;
    public CollistionDetection collision;
    public InputSystem inputSystem;
    public GLGame glGame;
    public AndroidAudio androidAudio;
    public TimeSystem timeSystem;
    public HitPointPool hitPointpool;
    private ArrayList<TimeSystem> mItemsNeedingReset = new ArrayList<TimeSystem>();
    
  
    public void enableReset(BaseObject object){
    	final boolean contained = mItemsNeedingReset.contains(timeSystem);
    	assert !contained;
    	if(!contained){
    		mItemsNeedingReset.add(timeSystem);
    	}
    } 	

    
    public void reset() {
       	final int count = mItemsNeedingReset.size();
       	for (int x = 0; x < count; x++) {
       		mItemsNeedingReset.get(x).setPosition(null);
       	}
     }
        
      
}
