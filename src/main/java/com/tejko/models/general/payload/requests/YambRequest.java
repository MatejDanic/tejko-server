package com.tejko.models.general.payload.requests;

import java.util.Map;

import com.tejko.models.yamb.Dice;
import com.tejko.models.yamb.Sheet;
import com.tejko.models.yamb.enums.BoxType;

public class YambRequest extends GameRequest {

    private Sheet sheet;
    private Map<Integer, Dice> diceMap;
    private Integer rollCount;
    private BoxType announcement;

    public Sheet getSheet() {
        return sheet;
    }
    
    public Map<Integer, Dice> getDiceMap() {
        return diceMap;
    }
    
    public Integer getRollCount() {
        return rollCount;
    }

    public BoxType getAnnouncement() {
        return announcement;
    }

}
