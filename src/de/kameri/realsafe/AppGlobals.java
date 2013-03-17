package de.kameri.realsafe;

import android.content.Context;

public final class AppGlobals {

	private static AppGlobals mInstance;
	private Context mCtx = null;
	
	private AppGlobals(){
	}
	
	public static AppGlobals getInstance(){
		if( mInstance != null ) 
			return mInstance;
		mInstance = new AppGlobals();
		return mInstance;
	}
	
	public void setContext(Context ctx){
		mCtx = ctx;
	}
	
	public Context getContext(){
		return mCtx;
	}
}
