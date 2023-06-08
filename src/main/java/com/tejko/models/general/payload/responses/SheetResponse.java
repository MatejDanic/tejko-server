package com.tejko.models.general.payload.responses;

import java.io.Serializable;
import java.util.Collection;

public class SheetResponse implements Serializable {

    private Collection<ColumnResponse> columnList;
    private int topSectionSum;
    private int middleSectionSum;
    private int bottomSectionSum;
    private int totalSum;
    private boolean completed;

    public SheetResponse(Collection<ColumnResponse> columnList, int topSectionSum, int middleSectionSum, int bottomSectionSum, int totalSum, boolean completed) {
        this.columnList = columnList;
        this.topSectionSum = topSectionSum;
        this.middleSectionSum = middleSectionSum;
        this.bottomSectionSum = bottomSectionSum;
        this.totalSum = totalSum;
        this.completed = completed;
    }

    public Collection<ColumnResponse> getColumnList() {
        return columnList;
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

    public int getTotalSum() {
        return totalSum;
    }

    public boolean isCompleted() {
        return completed;
    }

}
