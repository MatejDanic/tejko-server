package com.tejko.models.general.payload.requests;

import java.util.Map;
import java.util.UUID;

import com.tejko.models.yamb.Dice;
import com.tejko.models.yamb.Sheet;
import com.tejko.models.yamb.enums.BoxType;

public class YambRequest extends GameRequest {

    private Sheet sheet;
    private Map<Integer, Dice> diceMap;
    private int rollCount;
    private BoxType announcement;

    public YambRequest(UUID userId) {
        super(userId);
    }

    public Sheet getSheet() {
        return sheet;
    }
    
    public Map<Integer, Dice> getDiceMap() {
        return diceMap;
    }
    
    public int getRollCount() {
        return rollCount;
    }

    public BoxType getAnnouncement() {
        return announcement;
    }
    
}
