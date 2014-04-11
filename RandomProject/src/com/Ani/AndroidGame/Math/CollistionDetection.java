package com.Ani.AndroidGame.Math;

import com.Ani.AndroidGame.Common.ArraySizeFixed;
import com.Ani.AndroidGame.Common.MemoryLeakTracker;
import com.Ani.AndroidGame.Math.CollistionDetection.LineSegment.CollisionTile;
import com.Ani.AndroidGame.RIFrameWork.BaseObject;
import com.Ani.AndroidGame.World.World;
import com.Ani.AndroidGame.framework.Pool;
import com.Ani.AndroidGame.framework.impl.GameObjectImpl;

public class CollistionDetection extends BaseObject {
	private World mWorld;
	private CollisionTile[] mCollisionTiles;
	private TileTestVisitor tileTester;
	private Pool<LineSegment> lineSegmentPool;
	
	
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	
	protected class LineSegment extends MemoryLeakTracker {
        private Vector2 mStartPoint;
        private Vector2 mEndPoint;
        public Vector2 mNormal;
        public GameObjectImpl owner;
        
        public LineSegment() {
            super();
            mStartPoint = new Vector2();
            mEndPoint = new Vector2();
            mNormal = new Vector2();
        }
        
        /* Sets up the line segment.  Values are copied to local storage. */
        public void set(Vector2 start, Vector2 end, Vector2 norm) {
            mStartPoint.set(start);
            mEndPoint.set(end);
            mNormal.set(norm);
        }
        
        public void setOwner(GameObjectImpl ownerObject) {
            owner = ownerObject;
        }
        /**
         * Checks to see if these lines intersect by projecting one onto the other and then
         * assuring that the collision point is within the range of each segment.
         */
        public boolean calculateIntersection(Vector2 otherStart, Vector2 otherEnd,
                Vector2 hitPoint) {
            boolean intersecting = false;
            
            // Reference: http://local.wasp.uwa.edu.au/~pbourke/geometry/lineline2d/
            final float x1 = mStartPoint.x;
            final float x2 = mEndPoint.x;
            final float x3 = otherStart.x;
            final float x4 = otherEnd.x;
            final float y1 = mStartPoint.y;
            final float y2 = mEndPoint.y;
            final float y3 = otherStart.y;
            final float y4 = otherEnd.y;
            
            final float denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
            if (denom != 0) {
             final float uA = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denom;
             final float uB = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denom; 
             
             if (uA >= 0.0f && uA <= 1.0f && uB >= 0.0f && uB <= 1.0f) {
                 final float hitX = x1 + (uA * (x2 - x1));
                 final float hitY = y1 + (uA * (y2 - y1));
                 hitPoint.set(hitX, hitY);
                 intersecting = true;
             }
            }
            return intersecting;
        }
	
    protected class CollisionTile extends MemoryLeakTracker {
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
	}
}
