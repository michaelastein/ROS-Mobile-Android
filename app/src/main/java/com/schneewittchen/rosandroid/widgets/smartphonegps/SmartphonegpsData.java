package com.schneewittchen.rosandroid.widgets.smartphonegps;

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

float longitude;
float latitude;
float altitude;

    public  SmartphonegpsData() {

    }


    @Override
    public Message toRosMessage(Publisher<Message> publisher, BaseEntity widget) {
        sensor_msgs.NavSatFix message = (NavSatFix) publisher.newMessage();
        // TODO gps daten bekommen vom Handy
        message.setLongitude(longitude);
        message.setLatitude(latitude);
        message.setAltitude(altitude);

        return message;
    }
}
