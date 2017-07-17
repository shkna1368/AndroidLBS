package com.shabab.snalbs;


import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by kp6 on 8/23/2016.
 */
public class MapViewFragment extends Fragment implements ActivityCompat.OnRequestPermissionsResultCallback {

    public MapView mMapView;
    public GoogleMap googleMap;
    public static final int MULTIPLE_PERMISSIONS = 10;
    private static final int PERMISSION_REQUEST_CODE = 1;

  public   String[] permissions= new String[]{
           android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android. Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION};
    public GoogleMap getMap() {

        return googleMap;
    }

    private  boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:permissions) {
            result = ContextCompat.checkSelfPermission(getActivity(),p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MULTIPLE_PERMISSIONS );
            return false;
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mp, container, false);
        mMapView = (MapView) rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately



        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {


                if (googleMap == null){

              /*     if(ContextCompat.checkSelfPermission(getActivity(),
                            android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
*/

                     //  ActivityCompat.requestPermissions(getActivity(),new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);



                  /*  if (ContextCompat.checkSelfPermission(getActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                     //   private static final int REQUEST_INTERNET = 200;

                        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    }
*/
                    double lat=((MapActivity)getActivity()).getLat();
                    double lon=((MapActivity)getActivity()).getLon();
                    String name =((MapActivity)getActivity()).getName();
                    String details =((MapActivity)getActivity()).getDetails();
                    LatLng sydney = new LatLng(lat, lon);
                    mMap.addMarker(new MarkerOptions().position(sydney).title(name).snippet(details));

                    // For zooming automatically to the location of the marker
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(18).build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    MapActivity m= (MapActivity) getActivity();
                    m.setGoogleMap(mMap);

                   /* if(checkPermissions()){
                        Log.e(" if in act","");
                        act(googleMap);
                    }*/
               /*     if (checkPermission()) {

                        Toast.makeText(getActivity(),"Permission already granted.",Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getActivity(),"Please request permission.",Toast.LENGTH_LONG).show();


                    }
                    requestPermission(mMap);*/


                   }
               /* else {
                       return;
                   }*/


                    // For showing a move to my location button
              /*      if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                mMap.setMyLocationEnabled(true);*/

                // For dropping a marker at a point on the Map

            }
        });

        return rootView;
    }


void act(GoogleMap mMap){
Log.e("in act","");
   // mMap.setMyLocationEnabled(true);

    double lat=((MapActivity)getActivity()).getLat();
    double lon=((MapActivity)getActivity()).getLon();
    String name =((MapActivity)getActivity()).getName();
    String details =((MapActivity)getActivity()).getDetails();

    LatLng sydney = new LatLng(lat,lon);
    mMap.addMarker(new MarkerOptions().position(sydney).title(name).snippet(details));

    // For zooming automatically to the location of the marker
    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(18).build();
    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    MapActivity m= (MapActivity) getActivity();
    m.setGoogleMap(mMap);
    Log.e("in act2","");
}

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;

        }
    }

    public void actPerm(){
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.READ_PHONE_STATE}, PERMISSION_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(getActivity(),new String[]{android.Manifest.permission.READ_PHONE_STATE},PERMISSION_REQUEST_CODE);

            //TODO
        }
    }
    private void requestPermission(GoogleMap googleMap){

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION)){
            Log.e("emrooz","if");
            actPerm();
            Toast.makeText(getActivity(),"GPS permission allows us to access location data. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
            act(googleMap);
        } else {
            Log.e("emrooz","else");
            ActivityCompat.requestPermissions(getActivity(),new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_REQUEST_CODE);
          //  act(googleMap);
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case MULTIPLE_PERMISSIONS:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // permissions granted.
                } else {
                    // no permissions granted.
                }
                return;
            }
        }
    }





}