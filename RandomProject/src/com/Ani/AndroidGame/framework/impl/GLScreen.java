package com.Ani.AndroidGame.framework.impl;

import com.Ani.AndroidGame.framework.Game;
import com.Ani.AndroidGame.framework.Screen;

public abstract class GLScreen implements Screen{
    protected final GLGraphics glGraphics;
    protected final GLGame glGame;
    
    public GLScreen(Game game) {
        
        glGame = (GLGame)game;
        glGraphics = ((GLGame)game).getGLGraphics();
        
    }

}
