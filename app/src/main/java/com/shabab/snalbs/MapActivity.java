package com.shabab.snalbs;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends Activity {
	ImageView back;
	// Google Map
	 private GoogleMap googleMap;
	private double lat,lon;
	String name,details;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public GoogleMap getGoogleMap() {
		return googleMap;
	}

	public void setGoogleMap(GoogleMap googleMap) {
		this.googleMap = googleMap;
	}

	MarkerOptions markerPoint;
	RelativeLayout relBack;
	AlphaAnimation alphaDown1, alphaUp1,alphaDown2, alphaUp2;
	MapViewFragment mapFragment;
	CameraPosition cameraPosition;
	int x=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.mapz);


		ImageView iv = (ImageView) findViewById(R.id.imageVi);
		relBack= (RelativeLayout) findViewById(R.id.relBack);
		//back = (ImageView) findViewById(R.id.imageView6);

		Intent i=getIntent();
lat=i.getDoubleExtra("lat",0);
lon=i.getDoubleExtra("lon",0);
name=i.getStringExtra("name");
details=i.getStringExtra("details");
		initFrag();

alphaAnimation();
		overrideFonts(this,findViewById(R.id.content));
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		int width=0,height=0;
		try {


			display.getSize(size);
			width = size.x;height = size.y;}
		catch (Exception ex){
			width = display.getWidth();  // deprecated
			height = display.getHeight();
		}
		/*iv.setX(width / 2);
iv.GR
		iv.setY((float)(11.5*height/100));*/

		//float	centreX=iv.getX() + iv.getWidth()  / 2;
		//centreY=imageView.getY() + imageView.getHeight() / 2;
		iv.setY((float)(5*height)/100);
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)iv.getLayoutParams();
		//params.setMargins(0, (12.5*height)/100, 0, 0); //substitute parameters for left, top, right, bottom
		iv.setLayoutParams(params);

		RadioGroup rgViews = (RadioGroup) findViewById(R.id.rg_views);

		rgViews.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId == R.id.rb_normal){
					googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				}else if(checkedId == R.id.rb_satellite){
					googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				}else if(checkedId == R.id.rb_terrain){
					googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
				}
			}
		});



		relBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				back.startAnimation(alphaDown2);
				back.startAnimation(alphaUp2);
				finish();
			}
		});

		/*Fragment fragment = Fragment.instantiate(this, MapViewFragment.class.getName());
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, fragment);
		ft.commit();
*/

		//   btn= (Button) findViewById(R.id.btn) ;


	}
	void initFrag(){


		mapFragment=new MapViewFragment();
		FragmentManager fragmentManager=getFragmentManager();
		FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.container,mapFragment);
		fragmentTransaction.commit();
		this.	googleMap=	mapFragment.getMap();
	}
	void  showLocation(){
		MarkerOptions markerOptions = new MarkerOptions();

		markerOptions.position(new LatLng(lat,lon));
		markerOptions.title(name);
		googleMap.addMarker(markerOptions);
		//googleMap.addMarker(new MarkerOptions().position(new LatLng(Util.managerDetail.getLat(), Util.managerDetail.getLon())).title(Util.managerDetail.getEnglishShopName()));
		cameraPosition = new CameraPosition.Builder()
				.target(new LatLng(lat,
						lon)).zoom(18).build();

		googleMap.animateCamera(CameraUpdateFactory
				.newCameraPosition(cameraPosition));

	}





	private void overrideFonts(final Context context, final View v) {
		try {
			if (v instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0; i < vg.getChildCount(); i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			} else if (v instanceof TextView) {
				((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "Yekan.ttf"));
			}
		} catch (Exception e) {
		}}
	private   void alphaAnimation(){
		alphaDown1 = new AlphaAnimation(1.0f, 0.3f);
		alphaDown2 = new AlphaAnimation(1.0f, 0.3f);

		alphaUp1 = new AlphaAnimation(0.3f, 1.0f);
		alphaUp2 = new AlphaAnimation(0.3f, 1.0f);

		alphaDown1.setDuration(500);
		alphaDown2.setDuration(500);

		alphaUp1.setDuration(500);
		alphaUp2.setDuration(500);

		alphaDown1.setFillAfter(true);
		alphaDown2.setFillAfter(true);

		alphaUp1.setFillAfter(true);
		alphaUp2.setFillAfter(true);

	}


}
