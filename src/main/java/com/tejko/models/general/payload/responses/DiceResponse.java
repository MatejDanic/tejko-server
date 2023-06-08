package com.tejko.models.general.payload.responses;

import java.io.Serializable;

public class DiceResponse implements Serializable {

    private int value;
    private int order;

    public DiceResponse(int value, int order) {
        this.value = value;
        this.order = order;
    }

    public int getValue() {
        return value;
    }

    public int getOrder() {
        return order;
    }    
    
}
