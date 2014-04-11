package com.Ani.AndroidGame.framework;

import java.util.ArrayList;
import java.util.List;

import com.Ani.AndroidGame.CollisionRelated.LineSegmentPool;
import com.Ani.AndroidGame.Common.ArraySizeFixed;

public class Pool<T> {
    public interface PoolObjectFactory<T> {
        public T createObject();
    }

    private ArraySizeFixed<Object> available;
    private final List<T> freeObjects;
    private final PoolObjectFactory<T> factory;
   

	private final int maxSize;

    public Pool(PoolObjectFactory<T> factory, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        this.freeObjects = new ArrayList<T>(maxSize);
    }

    public T newObject() {
        T object = null;

        if (freeObjects.size() == 0)
            object = factory.createObject();
        else
            object = freeObjects.remove(freeObjects.size() - 1);

        return object;
    }

    public void free(T object) {
        if (freeObjects.size() < maxSize)
            freeObjects.add(object);
    }
    
    public int getMaxSize() {
		return maxSize;
	}
    
    protected ArraySizeFixed<Object> getAvailable(){
    	return available;
    }

	
}
