package com.Ani.AndroidGame.RIFrameWork;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.os.Build;

import com.Ani.AndroidGame.Common.AndroidContext;
import com.Ani.AndroidGame.Main.GameObjectRegistry;
import com.Ani.AndroidGame.Main.MainGame;
import com.Ani.AndroidGame.OpenGl.Texture;
import com.Ani.AndroidGame.framework.DebugLog;
import com.Ani.AndroidGame.framework.Game;

public class GameRenderer {
	
    private static final int PROFILE_REPORT_DELAY = 3 * 1000;
    
    private int mWidth;
    private int mHeight;
    private int mHalfWidth;
    private int mHalfHeight;
    
    private float mScaleX;
    private float mScaleY;
    private Context mContext;
    private long mLastTime;
    private int mProfileFrames;
    private long mProfileWaitTime;
    private long mProfileFrameTime;
    private long mProfileSubmitTime;
    private int mProfileObjectCount;
    
    private ObjectManager mDrawQueue;
    private boolean mDrawQueueChanged;
    private MainGame mGame;
    private Object mDrawLock;
    
    float mCameraX;
    float mCameraY;
    
    boolean mCallbackRequested;
    
    public GameRenderer(Context context, MainGame game, int gameWidth, int gameHeight) {
        mContext = context;
        mGame = game;
        mWidth = gameWidth;
        mHeight = gameHeight;
        mHalfWidth = gameWidth / 2;
        mHalfHeight = gameHeight / 2;
        mScaleX = 1.0f;
        mScaleY = 1.0f;
        mDrawQueueChanged = false;
        mDrawLock = new Object();
        mCameraX = 0.0f;
        mCameraY = 0.0f;
        mCallbackRequested = false;
    }
    
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        /*
         * Some one-time OpenGL initialization can be made here probably based
         * on features of this particular context
         */
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        gl.glShadeModel(GL10.GL_FLAT);
        gl.glDisable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        /*
         * By default, OpenGL enables features that improve quality but reduce
         * performance. One might want to tweak that especially on software
         * renderer.
         */
        gl.glDisable(GL10.GL_DITHER);
        gl.glDisable(GL10.GL_LIGHTING);

        gl.glTexEnvx(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_MODULATE);

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
       
        String extensions = gl.glGetString(GL10.GL_EXTENSIONS); 
        String version = gl.glGetString(GL10.GL_VERSION);
        String renderer = gl.glGetString(GL10.GL_RENDERER);
        boolean isSoftwareRenderer = renderer.contains("PixelFlinger");
        boolean isOpenGL10 = version.contains("1.0");
        boolean supportsDrawTexture = extensions.contains("draw_texture");
        // VBOs are standard in GLES1.1
        // No use using VBOs when software renderering, esp. since older versions of the software renderer
        // had a crash bug related to freeing VBOs.
        boolean supportsVBOs = !isSoftwareRenderer && (!isOpenGL10 || extensions.contains("vertex_buffer_object"));
       
        GameObjectRegistry gameObjectRegistry = new GameObjectRegistry();
        
        AndroidContext params = gameObjectRegistry.androidContext;
        
        params.supportsDrawTexture = supportsDrawTexture;
        params.supportsVBOs = supportsVBOs;
          
        hackBrokenDevices();
        
        DebugLog.i("Graphics Support", version + " (" + renderer + "): " +(supportsDrawTexture ?  "draw texture," : "") + (supportsVBOs ? "vbos" : ""));
        
        mGame.onSurfaceCreated();

    }

    private void hackBrokenDevices() {
    	// Some devices are broken.  Fix them here.  This is pretty much the only
    	// device-specific code in the whole project.  Ugh.
    	 GameObjectRegistry gameObjectRegistry = new GameObjectRegistry();
         
         AndroidContext params = gameObjectRegistry.androidContext;

       
    	if (Build.PRODUCT.contains("morrison")) {
    		// This is the Motorola Cliq.  This device LIES and says it supports
    		// VBOs, which it actually does not (or, more likely, the extensions string
    		// is correct and the GL JNI glue is broken).
    		params.supportsVBOs = false;
    		// TODO: if Motorola fixes this, I should switch to using the fingerprint
    		// (blur/morrison/morrison/morrison:1.5/CUPCAKE/091007:user/ota-rel-keys,release-keys)
    		// instead of the product name so that newer versions use VBOs.
    	}
    }
    
    public void loadTextures(GL10 gl, Texture texture) {
        if (gl != null) {
        	texture.loadAll(mContext, gl);
            DebugLog.d("AndouKun", "Textures Loaded.");
        }
    }
    
    
