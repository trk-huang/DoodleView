package com.sunhdj.doodlebundle;

import android.graphics.Color;

/**
 * Created by huangdaju on 17/8/18.
 */

public class ActionFactory {

    public static ActionStrategy choiceAction(Type type, float fx, float fy, int color, int size) {
        ActionStrategy actionStrategy = null;
        switch (type) {
            case PATH:
                actionStrategy = new PathAction(fx, fy, size, color);
                break;
            case CIRCLE:
                actionStrategy = new CircleAction();
                break;
            case SQUARE:
                actionStrategy = new SquareAction();
                break;
            case FILL_CIRCLE:
                actionStrategy = new FillCircleAction();
                break;
            case FILL_SQUARE:
                actionStrategy = new FillSquareAction();
                break;
        }
        return actionStrategy;
    }
}
