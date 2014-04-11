package com.Ani.AndroidGame.RIFrameWork;

import com.Ani.AndroidGame.framework.Pool;
import com.Ani.AndroidGame.framework.Pool.PoolObjectFactory;

public class RenderElementPool extends Pool<RenderElement> {
	private Pool<Object> pool;
	  int maxSize;
	  PoolObjectFactory<RenderElement> factory;
	
    RenderElementPool(int max) {
        super(factory, maxSize);
    }
    
    public void release(Object element) {
        RenderElement renderable = (RenderElement)element;
        // if this drawable came out of a pool, make sure it is returned to that pool.
         pool = renderable.mDrawable.getParentPool();
        if (pool != null) {
        	pool.free(renderable.mDrawable);
        }
        // reset on release
        renderable.reset();
       // super.free(element);
    }


protected void fill() {
  for (int x = 0; x < getMaxSize(); x++) {
      pool.free(new RenderElement());
  	}
	}

}