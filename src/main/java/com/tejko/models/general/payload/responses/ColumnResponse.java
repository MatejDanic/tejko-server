package com.tejko.models.general.payload.responses;

import java.io.Serializable;
import java.util.Collection;

import com.tejko.models.yamb.enums.ColumnType;

public class ColumnResponse implements Serializable {

    private ColumnType type;
    private Collection<BoxResponse> getBoxList;
    private int topSectionSum;
    private int middleSectionSum;
    private int bottomSectionSum;
    private boolean completed;

    public ColumnResponse(ColumnType type, Collection<BoxResponse> getBoxList, int topSectionSum, int middleSectionSum,
            int bottomSectionSum, boolean completed) {
        this.type = type;
        this.getBoxList = getBoxList;
        this.topSectionSum = topSectionSum;
        this.middleSectionSum = middleSectionSum;
        this.bottomSectionSum = bottomSectionSum;
        this.completed = completed;
    }

    public ColumnType getType() {
        return type;
    }

    public Collection<BoxResponse> getGetBoxList() {
        return getBoxList;
    }

    public int getTopSectionSum() {
        return topSectionSum;
    }

    public int getMiddleSectionSum() {
        return middleSectionSum;
    }

    public int getBottomSectionSum() {
        return bottomSectionSum;
    }

    public boolean isCompleted() {
        return completed;
    }
    
}
