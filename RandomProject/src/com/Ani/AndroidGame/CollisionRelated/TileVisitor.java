package com.Ani.AndroidGame.CollisionRelated;

import com.Ani.AndroidGame.Common.MemoryLeakTracker;
import com.Ani.AndroidGame.Math.Vector2;

public interface TileVisitor{
	
	
	public boolean visit(CollisionTile tile, Vector2 startPoint, Vector2 endPoint, Vector2 hitPoint, Vector2 hitNormal, int tileX, int tileY);
	
	

}
