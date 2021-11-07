package com.schneewittchen.rosandroid.widgets.smartphonegps;
import android.content.Context;
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

public class SmartphonegpsView extends PublisherWidgetView {
    Paint buttonPaint;
    TextPaint textPaint;
    StaticLayout staticLayout;
    boolean sendingGPS;


    public SmartphonegpsView(Context context) {
        super(context);
        init();
        sendingGPS = false;
    }

    public SmartphonegpsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
         = new Paint();
        .setColor(getResources().getColor(R.color.colorPrimary));
        .setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextSize(26 * getResources().getDisplayMetrics().density);
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
                this.publishViewData(new SmartphonegpsData());
                // TODO Daten
                //TODO regelmäßig senden implementieren später
                break;
            default:
                return false;
        }



        return true;
    }


    @Override
    public void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();
        float textLayoutWidth = width;

        SmartphonegpsEntity entity = (SmartphonegpsEntity) widgetEntity;

        if (entity.rotation == 90 || entity.rotation == 270) {
            textLayoutWidth = height;
        }

        canvas.drawRect(new Rect(0, 0, (int) width, (int) height), );

        staticLayout = new StaticLayout(entity.text,
                textPaint,
                (int) textLayoutWidth,
                Layout.Alignment.ALIGN_CENTER,
                1.0f,
                0,
                false);
        canvas.save();
        canvas.rotate(entity.rotation, width / 2, height / 2);
        canvas.translate(((width / 2) - staticLayout.getWidth() / 2), height / 2 - staticLayout.getHeight() / 2);
        staticLayout.draw(canvas);
        canvas.restore();
    }
}