package com.shabab.snalbs;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.akhgupta.easylocation.EasyLocationAppCompatActivity;
import com.akhgupta.easylocation.EasyLocationRequest;
import com.akhgupta.easylocation.EasyLocationRequestBuilder;
import com.google.android.gms.location.LocationRequest;
import com.shabab.snalbs.adapter.PlaceRowTaped;
import com.shabab.snalbs.model.Place;
import com.shabab.snalbs.service.GetNearestByRadiousPlaceService;
import com.shabab.snalbs.service.GetNearestPlaceService;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;

public class ActivityRadious extends Activity implements  SwipeRefreshLayout.OnRefreshListener , PlaceRowTaped {
    RecyclerView recyclerView;
    GetNearestByRadiousPlaceService getNearestPlaceService;
    boolean firsTime=true;
    private double DISTANCE=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radious);
         recyclerView= (RecyclerView) findViewById(R.id.my_recycler_view);
        LinearLayoutManager   mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

         getNearestPlaceService=new GetNearestByRadiousPlaceService(this,recyclerView);
        Intent i= getIntent();
      double lat=  i.getDoubleExtra("lat",0);
      double lon=  i.getDoubleExtra("lon",0);
        callService(lat,lon);

    }


    private void emptyRecycler(){
        recyclerView.setLayoutManager(null);

        recyclerView. setAdapter(null);
    }
private void callService(double lat,double lon){
    try {
        getNearestPlaceService.sendToServer(lat,lon,DISTANCE);
    } catch (JSONException e) {
        e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }

};




    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(Place place) {
Toast.makeText(this,place.toString(place.getDistance()),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLongItemClick(Place place) {
        Toast.makeText(this,"Long Touch="+place.toString(place.getDistance()),Toast.LENGTH_LONG).show();
    }
}
