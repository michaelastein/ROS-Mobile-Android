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
    private GpsTracker gps;

    public  SmartphonegpsData(Context context) {
        gps = new GpsTracker();
        Intent intent = new Intent(context, GpsTracker.class);
        context.startActivity(intent);
        intent.getData();
        longitude = gps.getLongitude();
        latitude = gps.getLatitude();
        altitude = gps.getAltitude();
        // TODO nach test rausnehmen
        if (longitude == 0.0){ longitude = 1;}
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
}
