package com.schneewittchen.rosandroid.widgets.smartphonegps;

import android.telephony.CarrierConfigManager;
import android.view.View;
import android.widget.EditText;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.model.entities.widgets.BaseEntity;
import com.schneewittchen.rosandroid.ui.views.details.PublisherWidgetViewHolder;
import com.schneewittchen.rosandroid.widgets.button.ButtonEntity;

import java.util.Collections;
import java.util.List;

import geometry_msgs.Twist;


public class SmartphonegpsDetailVH extends PublisherWidgetViewHolder {
    private EditText textText;



    @Override
    public void initView(View view) {
        textText = view.findViewById(R.id.btnTextTypeText);

    }

    @Override
    public void bindEntity(BaseEntity entity) {
        ButtonEntity buttonEntity = (ButtonEntity) entity;

        textText.setText(buttonEntity.text);

    }


    @Override
    public void updateEntity(BaseEntity entity) {
        ButtonEntity buttonEntity = (ButtonEntity)entity;

        buttonEntity.text = textText.getText().toString();



    }

    @Override
    public List<String> getTopicTypes() {
        return Collections.singletonList(Twist._TYPE);
    }
}