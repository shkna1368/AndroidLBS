package com.shabab.snalbs.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.Locale;


public class AppController extends MultiDexApplication {

	public static final String TAG = AppController.class.getSimpleName();

	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;

	private static AppController mInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;


	/*	Picasso.Builder builder = new Picasso.Builder(this);
		builder.downloader(new OkHttpDownloader(this,Integer.MAX_VALUE));
		Picasso built = builder.build();
		built.setIndicatorsEnabled(true);
		built.setLoggingEnabled(true);
		Picasso.setSingletonInstance(built);*/

	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		//setLocale();
	}

	private void setLocale() {
		Resources res = this.getResources();
		Configuration conf = new Configuration(res .getConfiguration());
		SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
		int language=   sharedPreferences.getInt("language",-1);
		if (language!=-1){
			setLangugueToApp(language);}
		else {
			setLangugueToApp(0);
		}
		conf.locale = new Locale("fa_IR");

		res.updateConfiguration(conf, res.getDisplayMetrics());
	}
	public static synchronized AppController getInstance() {
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}

	public ImageLoader getImageLoader() {
		getRequestQueue();
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader(this.mRequestQueue,
					new LruBitmapCache());
		}
		return this.mImageLoader;
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
	public void setLangugueToApp(int pos){
		String languageToLoad=null;

		if (pos==0){
			languageToLoad  = "fa";

		}
		else if (pos==1){
			languageToLoad  = "en";
			// Toast.makeText(LoginActivity.this,"en",Toast.LENGTH_LONG).show();

		}
		else if (pos==2){
			languageToLoad  = "ar";
			//  Toast.makeText(LoginActivity.this,"ar",Toast.LENGTH_LONG).show();

		}
		//tring languageToLoad  = "en"; // your language
       /*  Locale locale = new Locale(languageToLoad);
         Locale.setDefault(locale);
         Configuration config = new Configuration();
         config.locale = locale;
         getBaseContext().getResources().updateConfiguration(config,
                 getBaseContext().getResources().getDisplayMetrics());

        */


		Locale locale = new Locale(languageToLoad);

		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getApplicationContext().getResources().updateConfiguration(config, null);


	}
	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(newBase);
		MultiDex.install(this);
	}
}