package com.Ani.AndroidGame.framework.impl;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnKeyListener;

import com.Ani.AndroidGame.framework.Input.EngineKeyEvent;
import com.Ani.AndroidGame.framework.Pool;
import com.Ani.AndroidGame.framework.Pool.PoolObjectFactory;
import android.view.KeyEvent;

public class KeyboardHandler implements OnKeyListener {
    boolean[] pressedKeys = new boolean[128];
    Pool<EngineKeyEvent> keyEventPool;
    List<EngineKeyEvent> keyEventsBuffer = new ArrayList<EngineKeyEvent>();    
    List<EngineKeyEvent> keyEvents = new ArrayList<EngineKeyEvent>();
    
   
	
    public KeyboardHandler(View view) {
        PoolObjectFactory<EngineKeyEvent> factory = new PoolObjectFactory<EngineKeyEvent>() {
            public EngineKeyEvent createObject() {
                return new EngineKeyEvent();
            }
        };
        keyEventPool = new Pool<EngineKeyEvent>(factory, 100);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

 
    public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {
        if (event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE)
            return false;

        synchronized (this) {
            EngineKeyEvent keyEvent = keyEventPool.newObject();
            keyEvent.keyCode = keyCode;
            keyEvent.keyChar = (char) event.getUnicodeChar();
            if (event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                keyEvent.type = EngineKeyEvent.KEY_DOWN;
                if(keyCode > 0 && keyCode < 127)
                    pressedKeys[keyCode] = true;
            }
            if (event.getAction() == android.view.KeyEvent.ACTION_UP) {
                keyEvent.type = EngineKeyEvent.KEY_UP;
                if(keyCode > 0 && keyCode < 127)
                    pressedKeys[keyCode] = false;
            }
            keyEventsBuffer.add(keyEvent);
        }
        return false;
    }

    public boolean isKeyPressed(int keyCode) {
        if (keyCode < 0 || keyCode > 127)
            return false;
        return pressedKeys[keyCode];
    }

    public List<EngineKeyEvent> getKeyEvents() {
        synchronized (this) {
            int len = keyEvents.size();
            for (int i = 0; i < len; i++)
                keyEventPool.free(keyEvents.get(i));
            keyEvents.clear();
            keyEvents.addAll(keyEventsBuffer);
            keyEventsBuffer.clear();
            return keyEvents;
        }
    }
}
