package com.Ani.AndroidGame.RIFrameWork;

import com.Ani.AndroidGame.Common.ArraySizeFixed;
import com.Ani.AndroidGame.framework.Pool;
import com.Ani.AndroidGame.framework.Input.TouchEvent;
import com.Ani.AndroidGame.framework.Pool.PoolObjectFactory;
import com.Ani.AndroidGame.framework.impl.GameObjectImpl;

public class ObjectManager extends BaseObject {
	
	protected static final int DEFAULT_ARRAY_SIZE = 64;
	
	private ArraySizeFixed<BaseObject> objects;
	private ArraySizeFixed<BaseObject> pendingAdditions;
	private ArraySizeFixed<BaseObject> pendingRemovals;
	  	
	public ObjectManager(){
				
		objects = new ArraySizeFixed<BaseObject>( DEFAULT_ARRAY_SIZE);
		pendingAdditions =  new ArraySizeFixed<BaseObject>( DEFAULT_ARRAY_SIZE);
		pendingRemovals =  new ArraySizeFixed<BaseObject>( DEFAULT_ARRAY_SIZE);					
	}
	
	public ObjectManager (int arraysize){
		
		objects =  new ArraySizeFixed<BaseObject>( DEFAULT_ARRAY_SIZE);
		pendingAdditions = new ArraySizeFixed<BaseObject>( DEFAULT_ARRAY_SIZE);
		pendingRemovals =  new ArraySizeFixed<BaseObject>( DEFAULT_ARRAY_SIZE);
		
	}
	 
	    public void reset() {
	        commitUpdates();
	        final int count = objects.getCount();
	        for (int i = 0; i < count; i++) {
	            BaseObject object = objects.get(i);
	            object.reset();
	        }
	    }

	
	    public void commitUpdates() {
	        final int additionCount = pendingAdditions.getCount();
	        if (additionCount > 0) {
	            final Object[] additionsArray = pendingAdditions.getArray();
	            for (int i = 0; i < additionCount; i++) {
	                BaseObject object = (BaseObject)additionsArray[i];
	                objects.add(object);
	            } 
	            pendingAdditions.clear();
	        }
	        
	        final int removalCount = pendingRemovals.getCount();
	        if (removalCount > 0) {
	            final Object[] removalsArray = pendingRemovals.getArray();
	    
	            for (int i = 0; i < removalCount; i++) {
	                BaseObject object = (BaseObject)removalsArray[i];
	                objects.remove(object, true);
	            } 
	            pendingRemovals.clear();
	        }
	    }
	    
	    public void update(float timeDelta, BaseObject parent) {
	        commitUpdates();
	        final int count = objects.getCount();
	        if (count > 0) {
	            final Object[] objectArray = objects.getArray();
	            for (int i = 0; i < count; i++) {
	                BaseObject object = (BaseObject)objectArray[i];
	                object.update(timeDelta, this);
	            }
	        }
	    }
	    
	    public final ArraySizeFixed<BaseObject> getObjects() {
	        return objects;
	    }
	    
	    public final int getCount() {
	        return objects.getCount();
	    }
	    
	    /** Returns the count after the next commitUpdates() is called. */
	    public final int getConcreteCount() {
	        return objects.getCount() + pendingAdditions.getCount() - pendingRemovals.getCount();
	    }
	    
	    public final BaseObject get(int index) {
	        return objects.get(index);
	    }

	    public void add(BaseObject object) {
	        pendingAdditions.add(object);
	    }

	    public void remove(BaseObject object) {
	        pendingRemovals.add(object);
	    }
	    
	    public void removeAll() {
	        final int count = objects.getCount();
	        final Object[] objectArray = objects.getArray();
	        for (int i = 0; i < count; i++) {
	            pendingRemovals.add((BaseObject)objectArray[i]);
	        }
	        pendingAdditions.clear();
	    }

	    /** 
	     * Finds a child object by its type.  Note that this may invoke the class loader and therefore
	     * may be slow.
	     * @param classObject The class type to search for (e.g. BaseObject.class).
	     * @return
	     */
	    public <T> T findByClass(Class<T> classObject) {
	        T object = null;
	        final int count = objects.getCount();
	        for (int i = 0; i < count; i++) {
	            BaseObject currentObject = objects.get(i);
	            if (currentObject.getClass() == classObject) {
	                object = classObject.cast(currentObject);
	                break;
	            }
	        }
	        return object;
	    }
	    
	    protected ArraySizeFixed<BaseObject> getPendingObjects() {
	        return pendingAdditions;
	    }

	}

	    


