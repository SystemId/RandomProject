package com.Ani.AndroidGame.RIFrameWork;

import org.springframework.beans.factory.annotation.Autowired;

import com.Ani.AndroidGame.Common.MemoryLeakTracker;
import com.Ani.AndroidGame.Main.GameObjectRegistry;


public abstract class BaseObject extends MemoryLeakTracker {
   
	@Autowired
    GameObjectRegistry gameObjectRegistry;

    public BaseObject() {
        super();
    }
    
    public void update(float timeDelta, BaseObject parent) {
        // Base class does nothing.
    }
    
    
    public abstract void reset();

}
