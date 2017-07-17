package com.shabab.snalbs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shabab.snalbs.R;


/**
 * Created by Sh-Java on 12/10/2016.
 */
public class PlaceViewHolder extends RecyclerView.ViewHolder {

    TextView textName;
    TextView textDistance;
    TextView textTypes;
    TextView textDetails;
    View itemView;

    public View getItemView() {
        return itemView;
    }

    public void setItemView(View itemView) {
        this.itemView = itemView;
    }

    public PlaceViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        this.textName = (TextView) itemView.findViewById(R.id.texPlaceName);
        this.textDistance = (TextView) itemView.findViewById(R.id.textDistance);
        this.textTypes = (TextView) itemView.findViewById(R.id.textCategory);
        this.textDetails = (TextView) itemView.findViewById(R.id.textDetails);




    }
}
