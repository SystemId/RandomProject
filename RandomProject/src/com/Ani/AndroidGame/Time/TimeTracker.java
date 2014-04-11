package com.Ani.AndroidGame.Time;

import org.springframework.beans.factory.annotation.Autowired;

import com.Ani.AndroidGame.Main.GameObjectRegistry;
import com.Ani.AndroidGame.RIFrameWork.BaseObject;
import com.Ani.AndroidGame.RIFrameWork.ObjectManager;

public class TimeTracker extends ObjectManager {
	
	@Autowired
	GameObjectRegistry gameObjectRegistry;
	
	
	private TimeSystem timeSystem = new TimeSystem();
	
	public TimeTracker(){
	
		timeSystem = new TimeSystem();
		gameObjectRegistry.timeSystem = timeSystem;
		gameObjectRegistry.enableReset(timeSystem);
		
	
	}
	  public void update(float timeDelta, BaseObject parent) {
		  timeSystem.update(timeDelta);
	      final float newTimeDelta = timeSystem.getFrameDelta();  // The time system may warp time.
	      super.update(newTimeDelta, parent);
	    }

	
}
