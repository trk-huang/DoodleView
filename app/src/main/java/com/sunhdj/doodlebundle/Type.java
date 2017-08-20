package com.sunhdj.doodlebundle;

/**
 * Created by huangdaju on 17/8/18.
 */

public enum Type {
    PATH(0), CIRCLE(1), SQUARE(2), FILL_CIRCLE(3), FILL_SQUARE(4),LINE(5);
    private int type;

    private Type(int type) {
        this.type = type;
    }
}
