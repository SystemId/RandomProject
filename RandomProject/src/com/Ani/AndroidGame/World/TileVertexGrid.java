package com.Ani.AndroidGame.World;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;

import com.Ani.AndroidGame.Common.AndroidContext;
import com.Ani.AndroidGame.Common.BufferLibrary;
import com.Ani.AndroidGame.Main.GameObjectRegistry;
import com.Ani.AndroidGame.OpenGl.Texture;
import com.Ani.AndroidGame.framework.FileIO;
import com.Ani.AndroidGame.framework.impl.AndroidGame;
import com.Ani.AndroidGame.framework.impl.GLGame;
import com.Ani.AndroidGame.framework.impl.GLGraphics;

public class TileVertexGrid {
	
	private static final float GL_MAGIC_OFFSET = 0.375f;
	private Grid tileMap;
	private World mWorld;
	private int mTileWidth;
	private int mTileHeight;
	GLGame glgame;
	String fileName;
	GLGraphics glGraphics;
	private int mWidth;
	private int mHeight;
	private Texture mTexture;
	Texture textureGl;
	GameObjectRegistry gameobjectregistry;

	private float mWorldPixelWidth;
	private float mWorldPixelHeight;
	
	private int mTilesPerRow;
    private int mTilesPerColumn;
	
	private Boolean mGenerated;
	
	
	public TileVertexGrid(Texture texture, int width, int height, int tileWidth, int tileHeight){
		mTileWidth = tileWidth;
		mTileHeight = tileHeight;
		mWidth = width;
		mHeight = height;
		mTexture = texture;
		mGenerated = false;
		
	}
	
	public void setWorld(World world){
		mWorld = world;
	}
	
	private Grid generateGrid(int width, int height, int startTileX, int startTileY){
		  final int tileWidth = mTileWidth;
	      final int tileHeight = mTileHeight;
	      final int tilesAcross = width / tileWidth;
	      final int tilesDown = height / tileHeight;
	      final Texture texture = mTexture;
	      final float texelWidth = 1.0f / texture.width;
	      final float texelHeight = 1.0f / texture.height;
	      final int textureTilesAcross = texture.width / tileWidth;
	      final int textureTilesDown = texture.height / tileHeight;
	      final int tilesPerWorldColumn = mWorld.getHeight();
	      final int totalTextureTiles = textureTilesAcross * textureTilesDown;
	      boolean entirelyEmpty = true;
	        for (int tileY = 0; tileY < tilesDown && entirelyEmpty; tileY++) {
	            for (int tileX = 0; tileX < tilesAcross && entirelyEmpty; tileX++) {
	                int tileIndex = mWorld.getTile(startTileX + tileX, 
	                        (tilesPerWorldColumn - 1 - (startTileY + tileY)));
	                if (tileIndex >= 0) {
	                    entirelyEmpty = false;
	                    break;
	                }
	            }
	        }
	        
	        Grid grid = null;
	        if (!entirelyEmpty) {
	            grid = new Grid(tilesAcross, tilesDown, false);
	            for (int tileY = 0; tileY < tilesDown; tileY++) {
	                for (int tileX = 0; tileX < tilesAcross; tileX++) {
	                    final float offsetX = tileX * tileWidth;
	                    final float offsetY = tileY * tileHeight;
	                    int tileIndex = mWorld.getTile(startTileX + tileX, 
	                            (tilesPerWorldColumn - 1 - (startTileY + tileY)));
	                    if (tileIndex < 0) {
	                        tileIndex = totalTextureTiles - 1; // Assume that the last tile is empty.
	                    }
	                    int textureOffsetX = (tileIndex % textureTilesAcross) * tileWidth;
	                    int textureOffsetY = (tileIndex / textureTilesAcross) * tileHeight;
	                    if (textureOffsetX < 0 || 
	                            textureOffsetX > texture.width - tileWidth ||
	                            textureOffsetY < 0 ||
	                            textureOffsetY > texture.height - tileHeight) {
	                        textureOffsetX = 0;
	                        textureOffsetY = 0; 
	                    }
	                    final float u = (textureOffsetX + GL_MAGIC_OFFSET) * texelWidth;
	                    final float v = (textureOffsetY + GL_MAGIC_OFFSET) * texelHeight;
	                    final float u2 = ((textureOffsetX + tileWidth - GL_MAGIC_OFFSET) * texelWidth);
	                    final float v2 = ((textureOffsetY + tileHeight - GL_MAGIC_OFFSET) * texelHeight);
	                    
	                    final float[] p0 = { offsetX, offsetY, 0.0f };
	                    final float[] p1 = { offsetX + tileWidth, offsetY, 0.0f };
	                    final float[] p2 = { offsetX, offsetY + tileHeight, 0.0f };
	                    final float[] p3 = { offsetX + tileWidth, offsetY + tileHeight, 0.0f };
	                    final float[] uv0 = { u, v2 };
	                    final float[] uv1 = { u2, v2 };
	                    final float[] uv2 = { u, v };
	                    final float[] uv3 = { u2, v };
	                    
	                    final float[][] positions = { p0, p1, p2, p3 };
	                    final float[][] uvs = { uv0, uv1, uv2, uv3 };

	                    grid.set(tileX, tileY, positions, uvs);
	                    
	                }
	            }
	        }
	        return grid;
	    }
	   
	                    
	  public void draw(float x, float y, float scrollOriginX, float scrollOriginY) {
	        World world = mWorld;
	        GL10 gl = glGraphics.getGL();
	        if (!mGenerated && world != null && gl != null && mTexture != null) {
	            final int tilesAcross = mWorld.getWidth();
	            final int tilesDown = mWorld.getHeight();
	            
	            mWorldPixelWidth = mWorld.getWidth() * mTileWidth;
	            mWorldPixelHeight = mWorld.getHeight() * mTileHeight;
	            mTilesPerRow = tilesAcross;
	            mTilesPerColumn = tilesDown;
	            
	            
	            BufferLibrary bufferLibrary = gameobjectregistry.bufferLibrary;
	            
	            Grid grid = generateGrid((int)mWorldPixelWidth, (int)mWorldPixelHeight, 0, 0);
	            tileMap = grid;
	            mGenerated = true;
	            if (grid != null) {
	                bufferLibrary.add(grid);
	                if (gameobjectregistry.androidContext.supportsVBOs) {
	                	grid.generateHardwareBuffers(gl);
	                }
	            }
	                       
	        }
	  
	        final Grid mTileMap = tileMap;
	        if(tileMap != null){
	        	Texture texture = mTexture;
	        	if(gl != null && texture != null) {
	        		int originX = (int) (x - scrollOriginX);
	        		int originY = (int) (y - scrollOriginY);
	        	
	        	
	        	final float worldPixelWidth = mWorldPixelWidth;
	        	
	        	final float percentageScrollRight = scrollOriginX != 0.0f ? scrollOriginX / worldPixelWidth : 0.0f;
	        	
	        	final float tileSpaceX = percentageScrollRight * mTilesPerRow;
	        	final int leftTile = (int)tileSpaceX;
	        	
	        	final float worldPixelHeight = mWorldPixelHeight;
	        	
	        	final float percentageScrollUp = scrollOriginY != 0.0f ? scrollOriginY / worldPixelHeight : 0.0f;
	        	final float tileSpaceY = percentageScrollUp * mTilesPerColumn;
	        	final int bottomTile = (int)tileSpaceY;
	        	
	            final int horizontalSlop = ((tileSpaceX - leftTile) * mTileWidth) > 0 ? 1 : 0;
	            final int verticalSlop = ((tileSpaceY - bottomTile) * mTileHeight) > 0 ? 1 : 0;
	            
	            
	            texture = new Texture(glgame, fileName);
	            texture.bind();
	            tileMap.beginDrawingStrips(gl, true);
	        	
	            final int horzTileCount = (int)Math.ceil((float)mWidth / mTileWidth);
                final int vertTileCount = (int)Math.ceil((float)mHeight / mTileHeight);
                // draw vertex strips
                final int startX = leftTile;
                final int startY = bottomTile;
                final int endX = startX + horizontalSlop + horzTileCount;
                final int endY = startY + verticalSlop +  vertTileCount;
                
                gl.glPushMatrix();
                gl.glLoadIdentity();
                gl.glTranslatef(originX, originY, 0.0f);
                
                final int indexesPerTile = 6;
                final int indexesPerRow = mTilesPerRow * indexesPerTile;
                final int startOffset = (startX * indexesPerTile);
                final int count = (endX - startX) * indexesPerTile;
                
                for (int tileY = startY; tileY < endY && tileY < mTilesPerColumn; tileY++) {
                	final int row = tileY * indexesPerRow;
                	tileMap.drawStrip(gl, true, row + startOffset, count);
                }
                
                gl.glPopMatrix();
                
                Grid.endDrawing(gl);
                
            }
        }
    }
         

	

}
