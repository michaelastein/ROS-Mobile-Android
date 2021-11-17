package com.schneewittchen.rosandroid.widgets.smartphonegps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

public class GpsTracker extends Activity {
    private double longitude;
    private double latitude;
    private double altitude;



    // @SuppressLint("MissingPermission")
    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        try {


            // TODO Permission check?

            lm.requestLocationUpdates("gps", 60000, 1, locationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (l != null) {
            latitude = l.getLatitude();
            longitude = l.getLongitude();
            altitude = l.getAltitude();

        }
        else{
            longitude = 2;
        }
        if (longitude == 0.0){
            longitude = 3;
        }
    }


    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    // final?
    private  LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location l) {
            latitude = l.getLatitude();
            longitude = l.getLongitude();
            altitude = l.getAltitude();
            //TODO

        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };




}
