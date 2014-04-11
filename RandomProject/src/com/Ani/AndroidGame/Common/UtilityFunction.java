package com.Ani.AndroidGame.Common;

public class UtilityFunction {
	
	 public final static int byteArrayToInt(byte[] b) {
	        if (b.length != 4) {
	            
	        }
	       return 0;
	 }
	 
	  public final static int sign(float a) {
	        if (a >= 0.0f) {
	            return 1;
	        } else {
	            return -1;
	        }
	    }
	    
	    private static final float EPSILON = 0.0001f;

	    public final static boolean close(float a, float b) {
	        return close(a, b, EPSILON);
	    }

	    public final static boolean close(float a, float b, float epsilon) {
	        return Math.abs(a - b) < epsilon;
	    }

	 
	 

}
