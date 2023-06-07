package com.tejko.models.yamb;

import java.io.Serializable;

import com.tejko.constants.YambConstants;
import com.tejko.exceptions.IllegalActionException;
import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;

public class Box implements Serializable {

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
        validateFillAction();
        this.value = value;
        filled = true;
        available = false;
    }

    private void validateFillAction() {
        if (filled) {
            throw new IllegalActionException(YambConstants.ERROR_MESSAGE_BOX_ALREADY_FILLED);
        } else if (!available) {
			throw new IllegalActionException(YambConstants.ERROR_MESSAGE_BOX_NOT_AVAILABLE);
        }
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
