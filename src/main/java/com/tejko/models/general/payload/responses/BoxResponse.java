package com.tejko.models.general.payload.responses;

import java.io.Serializable;

import com.tejko.models.yamb.enums.BoxType;

public class BoxResponse implements Serializable {

    private BoxType type;
    private int value;
    private boolean filled;
    private boolean available;

    public BoxResponse(BoxType type, int value, boolean filled, boolean available) {
        this.type = type;
        this.value = value;
        this.filled = filled;
        this.available = available;
    }

    public BoxType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public boolean isFilled() {
        return filled;
    }

    public boolean isAvailable() {
        return available;
    }    
    
}
