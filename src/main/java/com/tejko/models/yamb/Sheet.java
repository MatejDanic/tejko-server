package com.tejko.models.yamb;

import java.io.Serializable;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;

public class Sheet implements Serializable {

    private Map<ColumnType, Column> columnMap;

    private Sheet() {}

    private Sheet(Map<ColumnType, Column> columnMap) {
        this.columnMap = columnMap;
    }

    public static Sheet create() {
        return new Sheet(generateColumnMap());
    }

    private static Map<ColumnType, Column> generateColumnMap() {
        Map<ColumnType, Column> columnMap = new HashMap<>();
        for (ColumnType columnType : ColumnType.values()) {
            columnMap.put(columnType, Column.create(columnType));
        }
        return columnMap;
    }

    public Map<ColumnType, Column> getColumnMap() {
        return columnMap;
    }

    @JsonIgnore
    public List<Column> getColumnList() { 
        return columnMap.values().stream().collect(Collectors.toList());
    }

    @JsonIgnore
    public int getTopSectionSum() { 
        int topSectionSum = 0;
        for (Column column : columnMap.values()) {
            topSectionSum += column.getTopSectionSum();
        }
        return topSectionSum;
    }

    @JsonIgnore
    public int getMiddleSectionSum() { 
        int middleSectionSum = 0;
        for (Column column : columnMap.values()) {
            middleSectionSum += column.getMiddleSectionSum();
        }
        return middleSectionSum;
    }

    @JsonIgnore
    public int getBottomSectionSum() {
        int bottomSectionSum = 0;
        for (Column column : columnMap.values()) {
            bottomSectionSum += column.getBottomSectionSum();
        }
        return bottomSectionSum;
    }
    
    @JsonIgnore
    public int getTotalSum() { 
        return getTopSectionSum() + getMiddleSectionSum() + getBottomSectionSum();
    }
    
    @JsonIgnore
    public boolean isCompleted() {
        for (ColumnType columnType : columnMap.keySet()) {
            if (!columnMap.get(columnType).isCompleted()) {
                return false;
            }
        }
        return true;
    }

    public void fillBox(ColumnType columnType, BoxType boxType, int value) {
        columnMap.get(columnType).fillBox(boxType, value);
    }

    public boolean areAllNonAnnouncementColumnsCompleted() {
        for (Column column : columnMap.values()) {
            if (column.getType() != ColumnType.ANNOUNCEMENT && !column.isCompleted()) {
                return false;
            }
        }
        return true;
    }

}
