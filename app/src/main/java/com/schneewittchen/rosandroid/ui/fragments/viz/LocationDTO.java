package com.schneewittchen.rosandroid.ui.fragments.viz;

public class LocationDTO {
    public double latitude;
    public double longitude;
    public double altitude;
    public double speed;

    @Override
    public String toString() {
        return "lat:"+latitude +"- lng:"+longitude + "- alt:"+altitude +"- speed:"+speed;
    }
}