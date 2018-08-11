package com.mounica.pheramor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatSeekBar;

public class AgeSeekBar extends AppCompatSeekBar {

    public AgeSeekBar(final Context context) {
        super(context);
    }

    @Override
    protected synchronized void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        int thumb = (int) (( (double)this.getProgress()/this.getMax() ) * (double)this.getWidth());
        float middle = (float) (this.getHeight());

        Paint paint = new Paint();
        paint.setTextSize(20);
        canvas.drawText(String.valueOf(this.getProgress()),thumb,middle,paint);
    }
}
