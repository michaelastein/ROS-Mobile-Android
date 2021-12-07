package com.schneewittchen.rosandroid.ui.fragments.viz;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.viewmodel.VizViewModel;
import com.schneewittchen.rosandroid.ui.general.DataListener;
import com.schneewittchen.rosandroid.ui.general.WidgetChangeListener;
import com.schneewittchen.rosandroid.model.entities.widgets.BaseEntity;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.node.BaseData;
import com.schneewittchen.rosandroid.widgets.smartphonegps.SmartphonegpsData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * TODO: Description
 *
 * @author Nico Studt
 * @version 1.0.2
 * @created on 10.01.20
 * @updated on 21.04.20
 * @modified by Nils Rottmann
 */
public class VizFragment extends Fragment implements DataListener, WidgetChangeListener {

    public static final String TAG = VizFragment.class.getSimpleName();

    private VizViewModel mViewModel;
    private WidgetViewGroup widgetViewGroupview;
    private DrawerLayout drawerLayout;
    private ImageButton optionsOpenButton;
    private SwitchMaterial vizEditModeSwitch;
    private ServiceConnection serviceConnection;
    private boolean isServiceBound;
    private BackgroundLocationUpdateService locationService;
    private Intent intent;
    private double longitude = 0, latitude = 0, altitude = 0;


    public static VizFragment newInstance() {
        return new VizFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_viz, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        widgetViewGroupview = view.findViewById(R.id.widget_groupview);
        widgetViewGroupview.setDataListener(this);
        widgetViewGroupview.setOnWidgetDetailsChanged(this);

        optionsOpenButton = view.findViewById(R.id.viz_options_open_button);

        drawerLayout = view.findViewById(R.id.viz_options_drawer);
        drawerLayout.setScrimColor(getResources().getColor(R.color.drawerFadeColor));

        vizEditModeSwitch = view.findViewById(R.id.edit_viz_switch);

     //  EventBus.getDefault().register(this);


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(VizViewModel.class);

        mViewModel.getCurrentWidgets().observe(getViewLifecycleOwner(), widgetEntities -> {
            widgetViewGroupview.setWidgets(widgetEntities);
        });

        mViewModel.getData().observe(getViewLifecycleOwner(), data -> {
            widgetViewGroupview.onNewData(data);
        });

        vizEditModeSwitch.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            widgetViewGroupview.setVizEditMode(isChecked);
        });

        optionsOpenButton.setOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END);
            } else {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });

        //getActivity().startService(new Intent(getActivity().getBaseContext(), LocationUpdateService.class));
        EventBus.getDefault().register(this);

    }

    @Override
    public void onNewWidgetData(BaseData data) {

        if (data instanceof SmartphonegpsData) {
            //EventBus.getDefault().register(this);
//TODO

            ((SmartphonegpsData) data).setGPS(longitude, latitude, altitude);


        }

        mViewModel.publishData(data);
    }

    @Override
    public void onWidgetDetailsChanged(BaseEntity widgetEntity) {
        mViewModel.updateWidget(widgetEntity);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LocationUpdateEvent event) {

        latitude = event.getLocation().latitude;
        longitude = event.getLocation().longitude;
        altitude = event.getLocation().altitude;

        // für ständige GPS Übertragung:

     //   SmartphonegpsData data = new SmartphonegpsData();
     //  onNewWidgetData(data);


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LocationUpdateEvent event) {

        onMessageEvent(event);



    }
}
