package com.sunhdj.doodlebundle;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * Created by huangdaju on 17/8/18.
 */

public abstract class ActionStrategy {

    public int color;

    public ActionStrategy() {
        color = Color.BLACK;
    }

    public ActionStrategy(int color) {
        this.color = color;
    }

    public abstract void draw(Canvas canvas);

    public abstract void move(float fx, float fy);
}
