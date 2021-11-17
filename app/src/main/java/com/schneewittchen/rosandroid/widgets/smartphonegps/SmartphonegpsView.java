package com.schneewittchen.rosandroid.widgets.smartphonegps;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.ui.views.widgets.PublisherWidgetView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class SmartphonegpsView extends PublisherWidgetView {
    Paint buttonPaint;
    TextPaint textPaint;
    StaticLayout staticLayout;
    boolean sendingGPS;
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    Context context;



    public SmartphonegpsView(Context context) {
        super(context);
        this.context = context;
        init();
        sendingGPS = false;
    }

    public SmartphonegpsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        buttonPaint = new Paint();
        buttonPaint.setColor(getResources().getColor(R.color.colorPrimary));
        buttonPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextSize(26 * getResources().getDisplayMetrics().density);
        requestPermissionsIfNecessary(new String[]{
                // ACCESS_FINE_LOCATION is required in order to send GPS position
                Manifest.permission.ACCESS_FINE_LOCATION
        });
    }
    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this.getContext(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.editMode) {
            return super.onTouchEvent(event);
        }
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_UP:
                buttonPaint.setColor(getResources().getColor(R.color.colorPrimary));
                break;
            case MotionEvent.ACTION_DOWN:
                buttonPaint.setColor(getResources().getColor(R.color.color_attention));
                this.publishViewData(new SmartphonegpsData(context));

                break;
            default:
                return false;
        }



        return true;
    }

   /** public void sendGps(){

    this.publishViewData(new SmartphonegpsData());

    }
    **/


    @Override
    public void onDraw(Canvas canvas) {
// TODO hier regelmäßig daten senden?
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();
        float textLayoutWidth = width;

        SmartphonegpsEntity entity = (SmartphonegpsEntity) widgetEntity;



        canvas.drawRect(new Rect(0, 0, (int) width, (int) height),buttonPaint );

        staticLayout = new StaticLayout(entity.text,
                textPaint,
                (int) textLayoutWidth,
                Layout.Alignment.ALIGN_CENTER,
                1.0f,
                0,
                false);
        canvas.save();
        //canvas.rotate(entity.rotation, width / 2, height / 2);
        canvas.translate(((width / 2) - staticLayout.getWidth() / 2), height / 2 - staticLayout.getHeight() / 2);
        staticLayout.draw(canvas);
        canvas.restore();
    }
}