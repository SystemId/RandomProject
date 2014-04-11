package com.Ani.AndroidGame.RIFrameWork;

import com.Ani.AndroidGame.Common.ArraySizeFixed;
import com.Ani.AndroidGame.Common.UtilityFunction;
import com.Ani.AndroidGame.Math.Vector2;
import com.Ani.AndroidGame.OpenGl.Texture;
import com.Ani.AndroidGame.framework.GameObject;
import com.Ani.AndroidGame.framework.Pool;
import com.Ani.AndroidGame.framework.Pool.PoolObjectFactory;

public class RenderSystem {
	
	
	private RenderElementPool elementPool;
	private ObjectManager[] renderQueues;
	private int queueIndex;

    private final static int DRAW_QUEUE_COUNT = 2;
    private final static int MAX_RNDER_OBJECTS_PER_FRAME = 384;
    private final static int MAX_RENDER_OBJECTS = MAX_RNDER_OBJECTS_PER_FRAME * DRAW_QUEUE_COUNT;
    
    public RenderSystem() {
    	super();
    	elementPool = new RenderElementPool(MAX_RENDER_OBJECTS);
    	renderQueues = new ObjectManager[DRAW_QUEUE_COUNT];
    	for (int x = 0; x < DRAW_QUEUE_COUNT; x++){
    		renderQueues = new ObjectManager[MAX_RNDER_OBJECTS_PER_FRAME];
    	}
    	queueIndex = 0;
    }
    
    public void scheduleForDraw(DrawableObject object, Vector2 position, int priority, boolean cameraRelative){
    		RenderElement element = elementPool.release(element);
    		if(element != null){
    			element.set(object, position, priority, cameraRelative);
    			renderQueues[queueIndex].
    		}
    }
    
    public void clearQueue(ArraySizeFixed<GameObject> objects){
    	final int count = objects.getCount();
    	final Object[] objectArray = objects.getArray();
    	final RenderElement element = (RenderElement)objectArray[i];
    	elementPool.release(element);
    	objects.removeLast();
    	
    }
    
    public void swap(GameRenderer renderer, float cameraX, float cameraY){
    	renderQueues[queueIndex]
    	
    	
    	
    	
    	
    }
    
    
	
		
	
	 
	  
	 
	
}
