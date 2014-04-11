

package com.Ani.AndroidGame.Common;

import com.Ani.AndroidGame.framework.DebugLog;


public class MemoryLeakTracker {
    public static boolean sGuardActive = false;
    public MemoryLeakTracker() {
        if (sGuardActive) {
            // An allocation has occurred while the guard is active!  Report it.
            DebugLog.e("AllocGuard", "An allocation of type " + this.getClass().getName() 
                    + " occurred while the AllocGuard is active.");
            
            
        }
    }
}
