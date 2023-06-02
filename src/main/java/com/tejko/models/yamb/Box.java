package com.tejko.models.yamb;

import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;

public class Box {

    private BoxType type;

    private int value = 0;

    private boolean filled = false;

    private boolean available;

    private Box() {}

    private Box(BoxType type, boolean available) {
        this.type = type;
        this.available = available;
    }

    public static Box createBox(ColumnType columnType, BoxType type) {
        return new Box(type, Box.isAvailableAtStart(columnType, type));
    }
    
    public int getValue() {
        return value;
    }

    public BoxType getType() {
        return type;
    }

    public boolean isFilled() {
        return filled;
    }

    public boolean isAvailable() {
        return available;
    }

    public void fill(int value) {
        this.value = value;
        filled = true;
        available = false;
    }

    public void makeAvailable() {
        available = true;
    }

    private static boolean isAvailableAtStart(ColumnType columnType, BoxType boxType) {
        return columnType == ColumnType.FREE
            || columnType == ColumnType.ANNOUNCEMENT
            || columnType == ColumnType.DOWNWARDS
                && boxType == BoxType.ONES
            || columnType == ColumnType.UPWARDS
                && boxType == BoxType.YAMB;
    }

}
