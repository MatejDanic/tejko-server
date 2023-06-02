package com.tejko.models.yamb;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tejko.constants.YambConstants;
import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;

public class Column {

    private ColumnType type;

    @JsonIgnore
    private Map<BoxType, Box> boxMap;

    private Column() {}
    
    private Column(ColumnType type, Map<BoxType, Box> boxMap) {
        this.type = type;
        this.boxMap = boxMap;
    }

    public static Column createColumn(ColumnType type) {
        Column column = new Column(type, Column.generateBoxMap(type));
        return column;
    }

    private static Map<BoxType, Box> generateBoxMap(ColumnType columnType) {
        Map<BoxType, Box> boxMap = new HashMap<>();
        for (BoxType boxType : BoxType.values()) {
            boxMap.put(boxType, Box.createBox(columnType, boxType));
        }
        return boxMap;
    }

    public ColumnType getType() {
        return type;
    }

    public Map<BoxType, Box> getBoxMap() {
        return boxMap;
    }

    @JsonProperty("boxList")
    public Collection<Box> getBoxList() {
        return boxMap.values();
    }

    @JsonIgnore
    public int getTopSectionSum() {
        int topSectionSum = 0;
        for (BoxType boxType : YambConstants.TOP_SECTION) {
            topSectionSum += boxMap.get(boxType).getValue();
        }
        if (topSectionSum >= YambConstants.TOP_SECTION_SUM_BONUS_THRESHOLD) {
            topSectionSum += YambConstants.TOP_SECTION_SUM_BONUS;
        }
        return topSectionSum;
    }

    @JsonIgnore
    public int getMiddleSectionSum() {
        int middleSectionSum = 0;
        Box ones = boxMap.get(BoxType.ONES);
        Box max = boxMap.get(BoxType.MAX);
        Box min = boxMap.get(BoxType.MIN);
        if (ones.isFilled() && max.isFilled() && min.isFilled()) {
            middleSectionSum = ones.getValue() * (max.getValue() - min.getValue());
        }
        return middleSectionSum;
    }

    @JsonIgnore
    public int getBottomSectionSum() {
        int bottomSectionSum = 0;
        for (BoxType bType : YambConstants.BOTTOM_SECTION) {
            bottomSectionSum += boxMap.get(bType).getValue();
        }
        return bottomSectionSum;
    }

    @JsonIgnore
    public boolean isCompleted() {
        return getNumOfAvailableBoxes() == 0;
    }

    @JsonIgnore
    private int getNumOfAvailableBoxes() {
        int numOfAvailableBoxes = 0;
        for (Box box : boxMap.values()) {
            if (box.isAvailable()) {
                numOfAvailableBoxes += 1;
            }
        }
        return numOfAvailableBoxes;
    }  

    public void fillBox(BoxType boxType, int value) {
        Box selectedBox = boxMap.get(boxType);
        selectedBox.fill(value);
        makeNextBoxAvailable(boxType);
    }

    private void makeNextBoxAvailable(BoxType selectedBoxType) {
        Box nextBox = null;
        if (type == ColumnType.DOWNWARDS && selectedBoxType != BoxType.YAMB) {
            nextBox = boxMap.get(BoxType.values()[selectedBoxType.ordinal() + 1]);
        } else if (type == ColumnType.UPWARDS && selectedBoxType != BoxType.ONES) {
            nextBox = boxMap.get(BoxType.values()[selectedBoxType.ordinal() - 1]);
        }
    
        if (nextBox != null) {
            nextBox.makeAvailable();
        }
    }
    
}
