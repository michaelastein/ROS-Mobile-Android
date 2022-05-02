package com.schneewittchen.rosandroid.widgets.smartphonegps;

import com.schneewittchen.rosandroid.model.entities.widgets.PublisherWidgetEntity;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.message.Topic;

import sensor_msgs.NavSatFix;

/**
 * @author Michaela Stein
  *In der Entitätsklasse werden die Objekteigenschaften und Standardwerte definiert
*/
public class SmartphonegpsEntity extends PublisherWidgetEntity {
    public String text;

    // Im Konstrukter werden Größe und der Text für den Button
    // sowie den Topic-Namen und die ROS-Nachrichtenart festgelegt

    public SmartphonegpsEntity() {
        this.width = 4;
        this.height = 2;
        this.text = "Send GPS";


        this.topic = new Topic("smartphone_gps", NavSatFix._TYPE);
    }
}