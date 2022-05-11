package com.tejko.models.yamb;

import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;

public class Box {

    private int value;

    private BoxType type;

    private boolean filled;

    private boolean available;

    public Box(BoxType type, boolean available) {
        this.type = type;
        this.available = available;
        this.value = 0;
        this.filled = false;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BoxType getType() {
        return this.type;
    }

    public void setType(BoxType type) {
        this.type = type;
    }

    public boolean isFilled() {
        return this.filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void fill(int value) {
        this.value = value;
        this.filled = true;
        this.available = false;
    }

    public boolean isNext(ColumnType currentColumnType, BoxType currentBoxType) {
        return (currentColumnType == ColumnType.DOWNWARDS && currentBoxType != BoxType.YAMB
                && currentBoxType.ordinal() + 1 == type.ordinal())
                || (currentColumnType == ColumnType.UPWARDS && currentBoxType != BoxType.ONES
                        && currentBoxType.ordinal() - 1 == type.ordinal());
    }
}
