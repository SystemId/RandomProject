package com.Ani.AndroidGame.framework;

import com.Ani.AndroidGame.framework.Graphics.PixmapFormat;

public interface Pixmap {
	
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
