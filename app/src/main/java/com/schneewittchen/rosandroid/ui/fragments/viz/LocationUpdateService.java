package com.schneewittchen.rosandroid.ui.fragments.viz;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;

import org.greenrobot.eventbus.EventBus;

/**
 * Starts location updates on background and publish LocationUpdateEvent upon
 * each new location result.
 *
 * Code von https://medium.com/@msaudi/android-getting-user-location-updates-in-a-background-service-and-draw-location-updates-on-a-map-225589d28cf6
 * angepasst an unser Projekt
 */
public class LocationUpdateService extends Service {

    //region data
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 3000;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private LocationSettingsRequest locationSettingsRequest;
    private Context context;


    //endregion

    //onCreate
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initData();
    }

    //Location Callback
    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            Location currentLocation = locationResult.getLastLocation();
            //Share/Publish Location
            LocationDTO location = new LocationDTO();
            location.latitude = currentLocation.getLatitude();
            location.longitude = currentLocation.getLongitude();
            location.speed = currentLocation.getSpeed();
            location.altitude = currentLocation.getAltitude();

            EventBus.getDefault().post(new LocationUpdateEvent(location));
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        startLocationUpdates();

        return START_STICKY;
    }

    //startet die Location Updates
    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {

        mFusedLocationClient.requestLocationUpdates(this.locationRequest,
                this.locationCallback, Looper.myLooper());
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void initData() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mFusedLocationClient =
                LocationServices.getFusedLocationProviderClient(context);

    }


}