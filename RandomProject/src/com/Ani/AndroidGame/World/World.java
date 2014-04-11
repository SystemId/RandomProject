package com.Ani.AndroidGame.World;

import java.io.IOException;
import java.io.InputStream;

import com.Ani.AndroidGame.Common.UtilityFunction;

import android.content.res.AssetManager;

public class World {
	
	private int [][] ArrayOfTiles;
	private int rowNumber;
	private int colNumber;
	public byte[] worldBytes;
		
	public World(int cols, int rows){
		
		ArrayOfTiles = new int[cols][rows];
		rowNumber = rows;
		colNumber = cols;
		
		
		for(int x = 0; x < cols; x++){
			for(int y= 0; y < rows; y++){
				ArrayOfTiles[x][y] = -1;
			}
		}
		
		worldBytes = new byte[4];
		
		calculateSkips();
		
		
		
		
	}
	
	public World(InputStream stream){
		
		worldBytes = new byte[4];
		parseIntput(stream);
		calculateSkips();
		
	}

	private boolean parseIntput(InputStream stream) {
		boolean successful = false;
		AssetManager.AssetInputStream byteStream = (AssetManager.AssetInputStream) stream;
		int signature;
		try{
			signature = (byte)byteStream.read();
			if(signature == 42){
				byteStream.read(worldBytes, 0 , 4);
				final int width = UtilityFunction.byteArrayToInt(worldBytes);
				byteStream.read(worldBytes, 0 , 4);
				final int height = UtilityFunction.byteArrayToInt(worldBytes);
				
				final int totalTiles = width * height;
				final int bytesRemaining = byteStream.available();
				assert bytesRemaining >= totalTiles;
				if(bytesRemaining >= totalTiles){
					ArrayOfTiles = new int[width][height];
					rowNumber = height;
					colNumber = width;
					
					for (int y =0; y < height; y++){
						for(int x = 0 ; x < width; x++){
							ArrayOfTiles[x][y] = (byte)byteStream.read();
						}
					}
					successful = true;
					}
				}
				
			}catch(IOException ex){
				
			}
			
		
		return successful;
	}
	
	public int getTile(int x, int y){
		int result = -1;
		if(x >= 0 && x < colNumber && y >= 0 && y < rowNumber){
			result = ArrayOfTiles[x][y];
		}
		return result;
	}

	private void calculateSkips() {
		int unusedTileCount = 0;
		for(int y = rowNumber -1; y >= 0; y--){
			for(int x = colNumber -1; x >= 0; x--){
					if(ArrayOfTiles[x][y] < 0){
						unusedTileCount++;
						ArrayOfTiles[x][y] = -unusedTileCount;
					}else{
						unusedTileCount = 0;
					}
				}
			}
		
		}
	

	public final int getWidth() {
        return colNumber;
    }

    public final int getHeight() {
        return rowNumber;
    }
    
    public final int[][] getTiles() {
        return ArrayOfTiles;
    }



	
	
	

}
