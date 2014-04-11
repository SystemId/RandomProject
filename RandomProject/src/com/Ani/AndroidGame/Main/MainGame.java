package com.Ani.AndroidGame.Main;

import org.springframework.beans.factory.annotation.Autowired;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.Ani.AndroidGame.Common.AndroidContext;
import com.Ani.AndroidGame.Common.BufferLibrary;
import com.Ani.AndroidGame.Level.LevelBuilder;
import com.Ani.AndroidGame.Level.LevelSystem;
import com.Ani.AndroidGame.Level.LevelTree;
import com.Ani.AndroidGame.Math.CollistionDetection;
import com.Ani.AndroidGame.OpenGl.Camera2D;
import com.Ani.AndroidGame.OpenGl.Texture;
import com.Ani.AndroidGame.RIFrameWork.GameRenderer;
import com.Ani.AndroidGame.RIFrameWork.HitPointPool;
import com.Ani.AndroidGame.RIFrameWork.ObjectManager;
import com.Ani.AndroidGame.Time.TimeTracker;
import com.Ani.AndroidGame.framework.InputGameInterface;
import com.Ani.AndroidGame.framework.Screen;
import com.Ani.AndroidGame.framework.impl.AndroidAudio;
import com.Ani.AndroidGame.framework.impl.AndroidInput;
import com.Ani.AndroidGame.framework.impl.GLGame;
import com.Ani.AndroidGame.framework.impl.MultiTouchHandler;
import com.Ani.AndroidGame.framework.impl.SingleTouchHandler;
import com.Ani.AndroidGame.framework.impl.TouchHandler;

public class MainGame extends GLGame {
	
	@Autowired
	GameObjectRegistry gameObjectRegistry;
	
	private GameThread mGameThread;
	private Thread mGame;
	private ObjectManager mGameRoot;
	private Activity activity;
	private GameRenderer mRenderer;
	private GLSurfaceView mSurfaceView;
	private boolean mRunning;
	private boolean mGameLoading;
	private LevelTree.Level mPendingLevel;
	private LevelTree.Level mCurrentLevel;
	private LevelTree.Level mLastLevel;
	private boolean mGLDataLoaded;
	private AndroidContext mContextParameters; 
	private TouchHandler mTouchHandler;

	View view;
	private AndroidInput mAndroidInput;
	private AssetFileDescriptor assetDescriptor;
	
	public MainGame(){
		super();
		
		  mRunning = false;
		  mGameLoading = false;
	      mGLDataLoaded = false;
	      mContextParameters = new AndroidContext();
		
	}
	
	public void bootstrap(Context context, int viewWidth, int viewHeight, int gameWidth, int gameHeight, int difficulty){
		if(!mGameLoading){
			mRenderer = new GameRenderer(context, this, gameWidth, gameHeight);
			
			AndroidContext androidContext = new AndroidContext();
			androidContext.viewHeight = viewHeight;
			androidContext.viewWidth = viewWidth;
			androidContext.gameWidth = gameWidth;
			androidContext.gameHeight = gameHeight;
			androidContext.viewScaleX = (float)viewWidth / gameWidth;
			androidContext.viewScaleY = (float)viewHeight/ gameHeight;
			androidContext.context = context;
			androidContext.difficulty = difficulty;
			
			
			gameObjectRegistry.androidContext = androidContext; 
			
			
			final int version = Build.VERSION.SDK_INT;
			
			//Figure out what the android View view is doing
			if(version < Build.VERSION_CODES.ECLAIR){
				mTouchHandler = new SingleTouchHandler(view, androidContext.viewScaleX,androidContext.viewScaleY);
			}else{
				mTouchHandler = new MultiTouchHandler(view, androidContext.viewScaleX,androidContext.viewScaleY);
			}
			
			
			// not yet using Texture Library from replica island going to load textures one by one here
			Texture texture = new Texture();
			
			
			//used to manage vbo
			gameObjectRegistry.bufferLibrary = new BufferLibrary();
			
			//have not yet set the sound for the game
			gameObjectRegistry.androidAudio = new AndroidAudio(activity);
			
			
			
			TimeTracker timeTacker = new TimeTracker();
			
			
			gameObjectRegistry.androidInput = new AndroidInput(context, view, androidContext.viewScaleX, androidContext.viewScaleY);
			
			gameObjectRegistry.androidInput  = mAndroidInput;
			gameObjectRegistry.enableReset(mAndroidInput);
			MainLoop gameRoot = new MainLoop();
			WindowManager windowMGr = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
			int rotationIndex = windowMGr.getDefaultDisplay().getOrientation();
			mAndroidInput.setScreenRotation(rotationIndex);
			
			//the following class was not at all coded by myself 
			InputGameInterface inputInterface = new InputGameInterface();
			gameRoot.add(inputInterface);
			gameObjectRegistry.inputGameInterface = inputInterface;
			
			LevelSystem level = new LevelSystem();
			gameObjectRegistry.levelSystem = level;
			
			CollistionDetection detection = new CollistionDetection();
			gameObjectRegistry.collision = detection;
			gameObjectRegistry.hitPointpool = new HitPointPool();
			
			
			GameObjectManager gameManager = new GameObjectManager(params.viewWidth * 2);
			

            GameObjectFactory objectFactory = new GameObjectFactory();
            gameObjectRegistry.gameObjectFactory = objectFactory;
            
        //    BaseObject.sSystemRegistry.hotSpotSystem = new HotSpotSystem();
            

            gameObjectRegistry.levelBuilder = new LevelBuilder();
            
		

//            BaseObject.sSystemRegistry.channelSystem = new ChannelSystem();
//            BaseObject.sSystemRegistry.registerForReset(BaseObject.sSystemRegistry.channelSystem);
            
            //camera system used in RI
            Camera2D camera = new Camera2D(glGraphics, startTime, startTime);
            

            gameObjectRegistry.cameraSystem = camera;
            gameObjectRegistry.registerForReset(camera);
    
            collision.loadCollisionTiles(context.getResources().openRawResource(R.raw.collision));
    
            gameRoot.add(gameManager);
            
            GameObjectCollisionSystem dynamicCollision = new GameObjectCollisionSystem();
            gameRoot.add(dynamicCollision);
            BaseObject.sSystemRegistry.gameObjectCollisionSystem = dynamicCollision;
            
            RenderSystem renderer = new RenderSystem();
            BaseObject.sSystemRegistry.renderSystem = renderer;
            BaseObject.sSystemRegistry.vectorPool = new VectorPool();
            BaseObject.sSystemRegistry.drawableFactory = new DrawableFactory();
            
	}
	
	
	
	
	
	
	

}

	public Screen getStartScreen() {
		// TODO Auto-generated method stub
		return null;
	}
