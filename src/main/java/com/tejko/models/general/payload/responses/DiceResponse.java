package com.tejko.models.general.payload.responses;

public class DiceResponse {

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
