package com.Ani.AndroidGame.Common;


import com.Ani.AndroidGame.World.*;
import javax.microedition.khronos.opengles.GL10;

	public class BufferLibrary  {
	    private static final int GRID_LIST_SIZE = 256;
	    private ArraySizeFixed<Grid> mGridList;
	    private AndroidContext androidContext;
	   
	    public BufferLibrary() {
	        super();
	        
	        mGridList = new ArraySizeFixed<Grid>(GRID_LIST_SIZE);
	    }
	    
	    public void reset() {
	        removeAll();
	    }
	    
	    public void add(Grid grid) { 
	         mGridList.add(grid);
	    }
	    
	    public void removeAll() {
	        mGridList.clear();
	    }
	    
	    public void generateHardwareBuffers(GL10 gl) {
	    	if (androidContext.supportsVBOs) {
		        final int count = mGridList.getCount();
		        for (int x = 0; x < count; x++) {
		            Grid grid = mGridList.get(x);
		            grid.generateHardwareBuffers(gl);
		        }
	    	}
	    }
	    
	    public void releaseHardwareBuffers(GL10 gl) {
	    	if (androidContext.supportsVBOs) {
		        final int count = mGridList.getCount();
		        for (int x = 0; x < count; x++) {
		            Grid grid = mGridList.get(x);
		            grid.releaseHardwareBuffers(gl);
		        }
	    	}
	    }
	    
	    public void invalidateHardwareBuffers() {
	    	if (androidContext.supportsVBOs) {
		        final int count = mGridList.getCount();
		        for (int x = 0; x < count; x++) {
		            Grid grid = mGridList.get(x);
		            grid.invalidateHardwareBuffers();
		        }
	    	}
	    }

	}

