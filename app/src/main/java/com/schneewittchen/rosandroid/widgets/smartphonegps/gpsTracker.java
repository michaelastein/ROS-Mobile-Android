package com.schneewittchen.rosandroid.widgets.smartphonegps;

import android.Manifest;
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

public class gpsTracker extends Activity {
    private double longitude;
    private double latitude;
    private double altitude;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        try {


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

            }
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
}





    public double getLongitude(){
        return longitude;
    }
    public double getLatitude(){
        return latitude;
    }
    public double getALtitude(){
        return altitude;
    }
    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location l) {
            latitude = l.getLatitude();
            longitude = l.getLongitude();
            altitude = l.getAltitude();
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };


}
