package com.Ani.AndroidGame.Level;

import org.springframework.beans.factory.annotation.Autowired;

import com.Ani.AndroidGame.Main.GameObjectRegistry;
import com.Ani.AndroidGame.RIFrameWork.BaseObject;
import com.Ani.AndroidGame.RIFrameWork.ObjectManager;
import com.Ani.AndroidGame.World.World;
import com.Ani.AndroidGame.framework.GameObject;

public class LevelSystem extends BaseObject {
	
	@Autowired
	GameObjectRegistry gameObjectRegisty;

    public int mWidthInTiles;
    public int mHeightInTiles;
    public int mTileWidth;
    public int mTileHeight;
    public GameObject mBackgroundObject;
    public ObjectManager mRoot;
    private byte[] mWorkspaceBytes;
    private World mSpawnLocations;
    private GameFlowEvent mGameFlowEvent;
    private int mAttempts;
    private LevelTree.Level mCurrentLevel;
    private int mAttempts;
    private LevelTree.Level mCurrentLevel;
    
    public LevelSystem() {
        super();
        mWorkspaceBytes = new byte[4];
        mGameFlowEvent = new GameFlowEvent();
        reset();
    }
    @Override
    public void reset() {
        if (mBackgroundObject != null && mRoot != null) {
            mBackgroundObject.removeAll();
            mBackgroundObject.commitUpdates();
            mRoot.remove(mBackgroundObject);
            mBackgroundObject = null;
            mRoot = null;
        }
        mSpawnLocations = null;
        mAttempts = 0;
        mCurrentLevel = null;
    }
    public float getLevelWidth() {
        return mWidthInTiles * mTileWidth;
    }
    
    public float getLevelHeight() {
        return mHeightInTiles * mTileHeight;
    }
    
    public void sendRestartEvent() {
        mGameFlowEvent.post(GameFlowEvent.EVENT_RESTART_LEVEL, 0,
        		gameObjectRegisty.androidContext);
    }
    
    public void sendNextLevelEvent() {
        mGameFlowEvent.post(GameFlowEvent.EVENT_GO_TO_NEXT_LEVEL, 0,
        		gameObjectRegisty.androidContext);
    }
    
    public void sendGameEvent(int type, int index, boolean immediate) {
        if (immediate) {
        	mGameFlowEvent.postImmediate(type, index,
        			gameObjectRegisty.androidContext);
        } else {
        	mGameFlowEvent.post(type, index,
        			gameObjectRegisty.androidContext);
        }
    }
    
    
    
}
