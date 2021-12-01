package com.schneewittchen.rosandroid.ui.fragments.viz;

import android.location.Location;

import com.schneewittchen.rosandroid.ui.fragments.viz.LocationDTO;

public class LocationUpdateEvent {
    private LocationDTO location;

    public LocationUpdateEvent(LocationDTO locationUpdate) {
        this.location = locationUpdate;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }
}