package com.Ani.AndroidGame.OpenGl;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;

import com.Ani.AndroidGame.framework.DebugLog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLU;
import android.opengl.GLUtils;

public class TextureLoader {
	
	Texture[] textureHash;
	int[] textureNameWorkSpace;
	int[] cropWorkSpace;
	static final int DEFAULT_SIZE = 512;
	static BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
	
	public TextureLoader(){
		super();
		textureHash = new Texture[DEFAULT_SIZE];
		for(int x = 0; x < textureHash.length; x++){
			textureHash[x] = new Texture();
			
		}
		
		textureNameWorkSpace = new int[1];
		cropWorkSpace = new int[4];
		bitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
		
		
	}
	
	  public Texture loadTexture(Context context, GL10 gl, int resourceID) {
	        Texture texture = allocateTexture(resourceID);
	        texture = loadBitmap(context, gl, texture);
	        return texture;
	    }
	
	   public Texture allocateTexture(int resourceID) {
	        Texture texture = getTextureByResource(resourceID);
	        if (texture == null) {
	            texture = addTexture(resourceID, "none", 0, 0);
	        }

	        return texture;
	    }
	   
	   public void loadAll(Context context, GL10 gl) {
	        for (int x = 0; x < textureHash.length; x++) {
	            if (textureHash[x].resource != -1 && textureHash[x].loaded == false) {
	                loadBitmap(context, gl, textureHash[x]);
	            }
	        }
	    }

	   protected Texture loadBitmap(Context context, GL10 gl, Texture texture) {
	        assert gl != null;
	        assert context != null;
	        assert texture != null;
	       
	            gl.glGenTextures(1, textureNameWorkSpace, 0);
	            
	            int error = gl.glGetError();
	            if (error != GL10.GL_NO_ERROR) {
	                DebugLog.d("Texture Load 1", "GLError: " + error + " (" + GLU.gluErrorString(error) + "): " + texture.resource);
	            }
	            
	            assert error == GL10.GL_NO_ERROR;
	            
	            int textureName = textureNameWorkSpace[0];
	            
	            gl.glBindTexture(GL10.GL_TEXTURE_2D, textureName);
	            
	            error = gl.glGetError();
	            if (error != GL10.GL_NO_ERROR) {
	                DebugLog.d("Texture Load 2", "GLError: " + error + " (" + GLU.gluErrorString(error) + "): " + texture.resource);
	            }
	            
	            assert error == GL10.GL_NO_ERROR;

	            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
	            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

	            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
	            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

	            gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_MODULATE); //GL10.GL_REPLACE);

	            InputStream is = context.getResources().openRawResource(texture.resource);
	            Bitmap bitmap;
	            try {
	                bitmap = BitmapFactory.decodeStream(is);
	            } finally {
	                try {
	                    is.close();
	                } catch (IOException e) {
	                	e.printStackTrace();
	                    // Ignore.
	                }
	            }
	            
	            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
	            
	            error = gl.glGetError();
	            if (error != GL10.GL_NO_ERROR) {
	                DebugLog.d("Texture Load 3", "GLError: " + error + " (" + GLU.gluErrorString(error) + "): " + texture.resource);
	            }
	            
	            assert error == GL10.GL_NO_ERROR;

	            cropWorkSpace[0] = 0;
	            cropWorkSpace[1] = bitmap.getHeight();
	            cropWorkSpace[2] = bitmap.getWidth();
	            cropWorkSpace[3] = -bitmap.getHeight();

	            ((GL11) gl).glTexParameteriv(GL10.GL_TEXTURE_2D, GL11Ext.GL_TEXTURE_CROP_RECT_OES,
	            		cropWorkSpace, 0);

	            texture.name = textureName;
	            texture.width = bitmap.getWidth();
	            texture.height = bitmap.getHeight();

	            bitmap.recycle();
	            
	            error = gl.glGetError();
	            if (error != GL10.GL_NO_ERROR) {
	                DebugLog.d("Texture Load 4", "GLError: " + error + " (" + GLU.gluErrorString(error) + "): " + texture.resource);
	            }
	            
	            assert error == GL10.GL_NO_ERROR;
	            
	           

	        

	        return texture;
	    }
	   public Texture getTextureByResource(int resourceID) {
	        int index = getHashIndex(resourceID);
	        int realIndex = findFirstKey(index, resourceID);
	        Texture texture = null;
	        if (realIndex != -1) {
	            texture = textureHash[realIndex];
	        }        
	        return texture;
	    }
	   private int findFirstKey(int startIndex, int key) {
	        int index = -1;
	        for (int x = 0; x < textureHash.length; x++) {
	            final int actualIndex = (startIndex + x) % textureHash.length;
	            if (textureHash[actualIndex].resource == key) {
	                index = actualIndex;
	                break;
	            } else if (textureHash[actualIndex].resource == -1) {
	                break;
	            }
	        }
	        return index;
	    }
	   
	   protected Texture addTexture(int id, String name, int width, int height) {
	        int index = findFirstKey(getHashIndex(id), -1);
	        Texture texture = null;
	        assert index != -1;
	        
	        if (index != -1) {
	            textureHash[index].textureId = id;
	            textureHash[index].fileName = name;
	            textureHash[index].width = width;
	            textureHash[index].height = height;
	            texture = textureHash[index];
	        }

	        return texture;
	    }
	   
	   
	   private int getHashIndex(int id) {
	        return id % textureHash.length;
	    }

	    public void removeAll() {
	        for (int x = 0; x < textureHash.length; x++) {
	            textureHash[x].dispose();
	        }
	    }

	   

}
