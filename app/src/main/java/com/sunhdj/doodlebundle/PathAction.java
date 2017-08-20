package com.sunhdj.doodlebundle;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by huangdaju on 17/8/18.
 */

public class PathAction extends ActionStrategy {

    private Path mPath;
    private int size;


    public PathAction() {
        mPath = new Path();
        this.size = 1;
    }

    public PathAction(float fx, float fy, int size, int color) {
        super(color);
        mPath = new Path();
        this.size = size;
        mPath.moveTo(fx, fy);
        mPath.lineTo(fx, fy);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(color);
        paint.setStrokeWidth(size);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(mPath, paint);
    }

    @Override
    public void move(float fx, float fy) {
        mPath.lineTo(fx, fy);
    }
}
