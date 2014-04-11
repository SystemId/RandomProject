package com.Ani.AndroidGame.framework;

public interface Screen {    

    public void update(float deltaTime);

    public void present(float deltaTime);

    public void pause();

    public void resume();

    public  void dispose();
}
