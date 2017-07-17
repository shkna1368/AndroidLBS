package com.shabab.snalbs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.shabab.snalbs.R;
import com.shabab.snalbs.model.Place;

import java.util.List;

/**
 * Created by Sh-Java on 12/10/2016.
 */
public class PlaceAdapter extends RecyclerView.Adapter<PlaceViewHolder>  {
    private List<Place> places;
PlaceRowTaped placeRowTaped;
    Context ctx;


   // private EventBus bus = EventBus.getDefault();

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_row, parent, false);
 ctx=view.getContext();


        PlaceViewHolder myViewHolder = new PlaceViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        TextView textPlaceName = holder.textName;
        TextView textDistance = holder.textDistance;
        TextView textDetails = holder.textDetails;
        TextView textTypes = holder.textTypes;



  final Place place=places.get(position);
        textDetails.setText(place.getPlaceDescrption());
        textPlaceName.setText(place.getPlaceName());
        textDistance.setText((int)place.getDistance()+" m");

        textTypes.setText(place.getType());
     /*   String noeZamin="-";
        String qarateKontor="-";
      String[] foo_array = ctx.getResources().getStringArray(R.array.array_vaziate_qate);
      String[] qarate_array = ctx.getResources().getStringArray(R.array.array_vaziateqarate_kontor);
        int v=melkTo.getVaziateQete();
        int v2=melkTo.getVaziateQrateKontor();
     *//*   Log.e("vaziate qotro=",v2+"");
        Log.e("vaziate 2=",v+"");
        Log.e("vaziate 2=",foo_array.length+"");
        Log.e("vaziate 2=",qarate_array.length+"");*//*

if(v==0){
    noeZamin=ctx.getString(R.string.notselected);
}
        else {

    noeZamin=foo_array[v-1];
}


if(v2==0){
    qarateKontor=ctx.getString(R.string.notselected);
}
        else {
    Log.e("v2=",v2+"");

    qarateKontor=qarate_array[v2-1];
}



        textDetails.setText(ctx.getString(R.string.details)+'\n'
               +ctx.getString(R.string.vaziate_qte)+":"+noeZamin +'\n'
               +ctx.getString(R.string.vaziateqarate_kontor)+":"+qarateKontor) ;

*/



        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                placeRowTaped.onItemClick(place);


            }
        });


        holder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                placeRowTaped.onLongItemClick(place);
                return false;
            }


        });







    }

    public PlaceAdapter(List<Place> places, PlaceRowTaped placeRowTaped) {
        this.places = places;

        this.placeRowTaped=placeRowTaped;
    }

    @Override
    public int getItemCount() {
        return places.size();
    }



}
