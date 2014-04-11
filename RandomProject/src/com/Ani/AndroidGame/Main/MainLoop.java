package com.Ani.AndroidGame.Main;

import org.springframework.beans.factory.annotation.Autowired;

import com.Ani.AndroidGame.RIFrameWork.BaseObject;
import com.Ani.AndroidGame.RIFrameWork.ObjectManager;
import com.Ani.AndroidGame.Time.TimeSystem;

public class MainLoop extends ObjectManager{
	
	@Autowired
	GameObjectRegistry gameObjectRegistry;
	
	// Ensures that time updates before everything else.
    public MainLoop() {
        super();
        mTimeSystem = new TimeSystem();
        gameObjectRegistry.timeSystem = mTimeSystem;
        gameObjectRegistry.enableReset(mTimeSystem);
    }

    @Override
    public void update(float timeDelta, BaseObject parent) {
        mTimeSystem.update(timeDelta, parent);
        final float newTimeDelta = mTimeSystem.getFrameDelta();  // The time system may warp time.
        super.update(newTimeDelta, parent);
    }

    private TimeSystem mTimeSystem;
}