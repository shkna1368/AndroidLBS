package com.shabab.snalbs.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.shabab.snalbs.MainActivity;
import com.shabab.snalbs.adapter.PlaceAdapter;
import com.shabab.snalbs.app.AppController;
import com.shabab.snalbs.model.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Shabab
 * Date: 2/2/15
 * Time: 5:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class GetNearestPlaceService{
    MainActivity context;
    RecyclerView recyclerView;

    List<String> listSpinner;




    public GetNearestPlaceService(MainActivity ctx, RecyclerView recyclerView) {
        this.context = ctx;
        this. recyclerView=recyclerView;

    }

    public   void sendToServer(double lat,double lon,double distance) throws JSONException, UnsupportedEncodingException {
        //   http://192.168.1.2:9080/Datasnap/rest/TserverMethods/AddToRecipt/21/1/5/  [{"stuffCode":1001,"entity":1,"unitSellPrice":3,"discount":3,"deficitValue":8,"taxValue":10}, {"stuffCode":1002,"entity":12,"unitSellPrice":40,"discount":32,"deficitValue":5,"taxValue":15}]











        //this.factorBeanClass=factorBean;
      //  String   urlGetCategory= Services.getCategories+shopId+'/'+password;


       // String  urlNearest = "http://192.168.1.5:9090/v1/SnaLBS/findNearestPlace/35.2988736/46.9894574/";
        String  urlNearest = "http://192.168.1.2:9090/v1/SnaLBS/findNearestPlace/"+lat+"/"+lon+"/";



       // http://localhost:9090/v1/SnaLBS/findNearestPlace/35.2988736/46.9894574/
       // http://localhost:9090/v1/SnaLBS/findNearestPlaceByRadious/35.2988736/46.9894574/1000
       // Log.e("getVersionss=",urlGetNewAppVersion);




        JsonObjectRequest jsonObjReq=null;

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlNearest,null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {




                        if (response!=null){


                            parseJson(response);

                        }





                    }
                    // pDialog.hide();

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {




                // pDialog.hide();
                if ((error instanceof NetworkError) || (error instanceof NoConnectionError) ) {
                    Toast.makeText(context,"internet access error", Toast.LENGTH_SHORT).show();


                }
                if (error instanceof TimeoutError){
                    Toast.makeText(context,"timeout", Toast.LENGTH_SHORT).show();

                    return;
                }




                if ((error instanceof ServerError) || (error instanceof AuthFailureError)){

                    Toast.makeText(context, "server Error", Toast.LENGTH_SHORT).show();


                    return;
                }
            }
        }

        )

        {
            public String getBodyContentType()
            {
                return "application/json";
            }

           /* public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headerMap = new HashMap<String, String>();

                String credentials = "fara" + ":" + "faracyber";
                String base64EncodedCredentials =
                        Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headerMap.put("Authorization", "Basic " + base64EncodedCredentials);

                return headerMap;
            }*/

        };

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                8000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Log.e("Body",new String(jsonObjReq.getBody(), "UTF-8"));
        String tag_json_arry = "json_array_req";
// Adding request to request queue

        // mRequestQueue.add(jsonObjReq);
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_arry);





    }
    public void parseJson(JSONObject response){
        List<Place> placeList = null;
        try {
            JSONArray jsonArray=response.getJSONArray("list");
           int arraySize= jsonArray.length();
            placeList=new ArrayList<>(arraySize);
            for (int i=0;i<arraySize;i++){
                JSONObject jsonObjectPlace=jsonArray.getJSONObject(i);
                String  palceName=jsonObjectPlace.getString("placeName");
                String  placeDescrption=jsonObjectPlace.getString("placeDescrption");
                String  type=jsonObjectPlace.getString("type");
                double lat  =jsonObjectPlace.getDouble("lat");
                double lon  =jsonObjectPlace.getDouble("lon");
                double distance  =jsonObjectPlace.getDouble("distance");
                long id   =jsonObjectPlace.getLong("placeID");

                Place place=new Place();
                place.setDistance(distance);
                place.setLat(lat);
                place.setPlaceID(id);
                place.setPlaceDescrption(placeDescrption);
                place.setType(type);
                place.setLon(lon);
                place.setPlaceName(palceName);

                placeList.add(place);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
      //  Toast.makeText(context, response.toString(),Toast.LENGTH_LONG).show();
        PlaceAdapter placeAdapter=new PlaceAdapter(placeList,context);
        recyclerView.setAdapter(placeAdapter);
        placeAdapter.notifyDataSetChanged();
        Toast.makeText(context, "adapter finish",Toast.LENGTH_LONG).show();

/*
        "placeID": 697,
                "lat": 35.2988736,
                "lon": 46.9894574,
                "distance": 0,
                "placeName": ""Lavash Bread"",
                "placeDescrption": ""Sanandaj, Sa'di Street"",
        "type": "["bakery","food","store","point_of_interest","establishment"]"*/



    }



}
