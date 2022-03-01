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

    private double longitude = 0.0;
    private double latitude = 0.0;
    private double altitude = 0.0;


    public  SmartphonegpsData() {

    }


    @Override
    public Message toRosMessage(Publisher<Message> publisher, BaseEntity widget) {
        sensor_msgs.NavSatFix message = (NavSatFix) publisher.newMessage();

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
