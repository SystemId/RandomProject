package com.Ani.AndroidGame.CollisionRelated;

import com.Ani.AndroidGame.Common.ArraySizeFixed;
import com.Ani.AndroidGame.Common.MemoryLeakTracker;

public class CollisionTile extends MemoryLeakTracker{
    
        public ArraySizeFixed<LineSegment> segments;
        
        public CollisionTile(int maxSegments) {
            super();
            segments = new ArraySizeFixed<LineSegment>(maxSegments);
        }
        
        public boolean addSegment(LineSegment segment) {
            boolean success = false;
            if (segments.getCount() < segments.getCapacity()) {
                success = true;
            }
            segments.add(segment);
            return success;
        }
    }


