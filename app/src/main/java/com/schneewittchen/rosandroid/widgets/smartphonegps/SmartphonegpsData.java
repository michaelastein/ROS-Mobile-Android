package com.schneewittchen.rosandroid.widgets.smartphonegps;

import android.content.Context;
import android.content.Intent;


import com.schneewittchen.rosandroid.model.entities.widgets.BaseEntity;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.node.BaseData;

import org.ros.internal.message.Message;
import org.ros.node.topic.Publisher;

import sensor_msgs.NavSatFix;

/**
 * TODO: Description
 *
 *
 */

public class SmartphonegpsData extends BaseData {

    private double longitude;
    private double latitude;
    private double altitude;


    public  SmartphonegpsData(Context context) {

    }


    @Override
    public Message toRosMessage(Publisher<Message> publisher, BaseEntity widget) {
        sensor_msgs.NavSatFix message = (NavSatFix) publisher.newMessage();

        //longitude = gps.getLongitude();
       // latitude = gps.getLatitude();
        //altitude = gps.getAltitude();
        message.setLongitude(longitude);
        message.setLatitude(latitude);
        message.setAltitude(altitude);

        return message;
    }

    public void setGPS(double longitude, double latitude, double altitude){
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }
}
