package com.shabab.snalbs;

import android.content.Intent;
import android.location.Location;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.akhgupta.easylocation.EasyLocationAppCompatActivity;
import com.akhgupta.easylocation.EasyLocationRequest;
import com.akhgupta.easylocation.EasyLocationRequestBuilder;
import com.google.android.gms.location.LocationRequest;
import com.shabab.snalbs.adapter.PlaceRowTaped;
import com.shabab.snalbs.model.Place;
import com.shabab.snalbs.service.GetNearestPlaceService;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;

public class MainActivity extends EasyLocationAppCompatActivity implements  SwipeRefreshLayout.OnRefreshListener , PlaceRowTaped {
    RecyclerView recyclerView;
    GetNearestPlaceService getNearestPlaceService;
    boolean firsTime=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         recyclerView= (RecyclerView) findViewById(R.id.my_recycler_view);
        LinearLayoutManager   mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

         getNearestPlaceService=new GetNearestPlaceService(this,recyclerView);
        getLocation();
    }


    private void emptyRecycler(){
        recyclerView.setLayoutManager(null);

        recyclerView. setAdapter(null);
    }
private void callService(double lat,double lon){
    try {
        getNearestPlaceService.sendToServer(lat,lon,0);
    } catch (JSONException e) {
        e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }

};

    private void getLocation(){

    /*    EasyLocationRequest easyLocationRequest = new EasyLocationRequestBuilder()
                .setLocationRequest(locationRequest)
                .setLocationPermissionDialogTitle(getString(R.string.location_permission_dialog_title))
                .setLocationPermissionDialogMessage(getString(R.string.location_permission_dialog_message))
                .setLocationPermissionDialogNegativeButtonText("NO")
                .setLocationPermissionDialogPositiveButtonText("YES")
                .setLocationSettingsDialogTitle(getString(R.string.location_services_off))
                .setLocationSettingsDialogMessage(getString(R.string.open_location_settings))
                .setLocationSettingsDialogNegativeButtonText("NOt NOW")
                .setLocationSettingsDialogPositiveButtonText("YEs")
                .build();*/

   /*         LocationRequest locationRequest = new LocationRequest()
                        .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                        .setInterval(5000)
                        .setFastestInterval(5000);
                EasyLocationRequest easyLocationRequest = new EasyLocationRequestBuilder()
                        .setLocationRequest(locationRequest)
                        .setFallBackToLastLocationTime(3000)
                        .build();
                requestSingleLocationFix(easyLocationRequest);
            }
        */
        LocationRequest locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                //.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(30000)
                .setFastestInterval(30000);
        EasyLocationRequest easyLocationRequest = new EasyLocationRequestBuilder()
                .setLocationRequest(locationRequest)
                .setFallBackToLastLocationTime(3000)
                .build();
        requestLocationUpdates(easyLocationRequest);

    }

    @Override
    public void onLocationPermissionGranted() {

    }

    @Override
    public void onLocationPermissionDenied() {

    }

    @Override
    public void onLocationReceived(Location location) {
    //    Toast.makeText(this,location.getLatitude() + "," + location.getLongitude(),Toast.LENGTH_LONG).show();
        if (firsTime){
            callService(location.getLatitude(),location.getLongitude());
            firsTime=false;
        }

    }

    @Override
    public void onLocationProviderEnabled() {

    }

    @Override
    public void onLocationProviderDisabled() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(Place place) {
//Toast.makeText(this,place.toString(place.getDistance()),Toast.LENGTH_LONG).show();
        Intent i=new Intent(this,ActivityRadious.class);
        i.putExtra("lat",place.getLat());
        i.putExtra("lon",place.getLon());
        startActivity(i);
       // startActivity(new Intent(this,ActivityAR.class));
    }

    @Override
    public void onLongItemClick(Place place) {
        Toast.makeText(this,"Long Touch="+place.toString(place.getDistance()),Toast.LENGTH_LONG).show();
        Intent i=new Intent(this,MapActivity.class);
        i.putExtra("lat",place.getLat());
        i.putExtra("lon",place.getLon());
        i.putExtra("name",place.getPlaceName());
        i.putExtra("details",place.getPlaceDescrption());
        startActivity(i);

    }
}
