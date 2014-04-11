package com.Ani.AndroidGame.CollisionRelated;

import com.Ani.AndroidGame.Common.MemoryLeakTracker;
import com.Ani.AndroidGame.Math.Vector2;
import com.Ani.AndroidGame.framework.impl.GameObjectImpl;

public class LineSegment extends MemoryLeakTracker{
	private Vector2 startPoint;
	private Vector2 endPoint;
	private Vector2 normal;
	private GameObjectImpl owner;
	
	public LineSegment(){
		startPoint = new Vector2();
		endPoint = new Vector2();
		normal = new Vector2();
	}
	
	public void set(Vector2 start, Vector2 end, Vector2 norm){
		startPoint.set(start);
		endPoint.set(end);
		normal.set(norm);
	}
	
	public void setOwner(GameObjectImpl ownerObject){
		owner = ownerObject;
	}
	
	 public boolean calculateIntersection(Vector2 otherStart, Vector2 otherEnd,
             Vector2 hitPoint) {
         boolean intersecting = false;
         
         // Reference: http://local.wasp.uwa.edu.au/~pbourke/geometry/lineline2d/
         final float x1 = startPoint.x;
         final float x2 = endPoint.x;
         final float x3 = otherStart.x;
         final float x4 = otherEnd.x;
         final float y1 = startPoint.y;
         final float y2 = endPoint.y;
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
	 
	 
     // Based on http://www.garagegames.com/community/resources/view/309
     public boolean calculateIntersectionBox(float left, float right, float top, float bottom, 
             Vector2 hitPoint) {
         
         final float x1 = startPoint.x;
         final float x2 = endPoint.x;
         final float y1 = startPoint.y;
         final float y2 = endPoint.y;
         
         float startIntersect;
         float endIntersect;
         float intersectTimeStart = 0.0f;
         float intersectTimeEnd = 1.0f;
         
         if (x1 < x2) {
             if (x1 > right || x2 < left) {
                 return false;
             }
             final float deltaX = x2 - x1;
             startIntersect = (x1 < left) ? (left - x1) / deltaX : 0.0f;
             endIntersect = (x2 > right) ? (right - x1) / deltaX : 1.0f;
         } else {
             if (x2 > right || x1 < left) {
                 return false;
             }
             final float deltaX = x2 - x1;
             startIntersect = (x1 > right) ? (right - x1) / deltaX : 0.0f;
             endIntersect = (x2 < left) ? (left - x1) / deltaX : 1.0f;
         }
         
         if (startIntersect > intersectTimeStart) {
             intersectTimeStart = startIntersect;
         }
         if (endIntersect < intersectTimeEnd) {
             intersectTimeEnd = endIntersect;
         }
         if (intersectTimeEnd < intersectTimeStart) {
             return false;
         }
         
         // y
         if (y1 < y2) {
             if (y1 > top || y2 < bottom) {
                 return false;
             }
             final float deltaY = y2 - y1;
             startIntersect = (y1 < bottom) ? (bottom - y1) / deltaY : 0.0f;
             endIntersect = (y2 > top) ? (top - y1) / deltaY : 1.0f;
         } else {
             if (y2 > top || y1 < bottom) {
                 return false;
             }
             final float deltaY = y2 - y1;
             startIntersect = (y1 > top) ? (top - y1) / deltaY : 0.0f;
             endIntersect = (y2 < bottom) ? (bottom - y1) / deltaY : 1.0f;
         }
         
         if (startIntersect > intersectTimeStart) {
             intersectTimeStart = startIntersect;
         }
         if (endIntersect < intersectTimeEnd) {
             intersectTimeEnd = endIntersect;
         }
         if (intersectTimeEnd < intersectTimeStart) {
             return false;
         }
      
         hitPoint.set(endPoint);
         hitPoint.sub(startPoint);
         hitPoint.mul(intersectTimeStart);
         hitPoint.add(startPoint);
         
         return true;
     }
     
 }
	


