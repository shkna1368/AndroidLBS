package com.shabab.snalbs.model;



/**
 * Created by Sh-Java on 6/27/2017.
 */


public class Place {


private long placeID;


    private double lat;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    private double lon;



    private double distance;

   // private double distance;


    private String placeName;


    private String placeDescrption;

  /*  @Type(type = "org.hibernate.spatial.GeometryType")
    @Column(name = "geo")
    private Geometry geo;*/


    private String type;

    public long getPlaceID() {
        return placeID;
    }

    public void setPlaceID(long placeID) {
        this.placeID = placeID;
    }

   /* public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
*/
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceDescrption() {
        return placeDescrption;
    }

    public void setPlaceDescrption(String placeDescrption) {
        this.placeDescrption = placeDescrption;
    }




    public String toString(double distance){

        return   "Latitute:"+lat+'\n'+
          "Longitude:"+lon+'\n'+
          "Distance:"+distance+'\n'+
        "PlaceName:"+placeName+'\n'+
       "PlacceDescryption:"+placeDescrption+'\n'+
       "type:"+type+'\n';


    }
}
