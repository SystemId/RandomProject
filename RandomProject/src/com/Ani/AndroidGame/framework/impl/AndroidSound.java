package com.Ani.AndroidGame.framework.impl;

import android.media.SoundPool;

import com.Ani.AndroidGame.framework.Sound;

public class AndroidSound implements Sound {
    int soundId;
    SoundPool soundPool;
    private boolean soundEnabled;
    
    public boolean isSoundEnabled() {
		return soundEnabled;
	}


	public void setSoundEnabled(boolean soundEnabled) {
		this.soundEnabled = soundEnabled;
	}


	public AndroidSound(SoundPool soundPool,int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }


    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }


    public void dispose() {
        soundPool.unload(soundId);
    }
    
    

}
