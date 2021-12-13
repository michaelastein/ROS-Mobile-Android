package com.schneewittchen.rosandroid.widgets.smartphonegps;

import com.schneewittchen.rosandroid.model.entities.widgets.PublisherWidgetEntity;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.message.Topic;

import sensor_msgs.NavSatFix;


public class SmartphonegpsEntity extends PublisherWidgetEntity {
    public String text;

    public SmartphonegpsEntity() {
        this.width = 4;
        this.height = 2;
        this.text = "Send GPS";


        this.topic = new Topic("smartphone_gps", NavSatFix._TYPE);
    }
}