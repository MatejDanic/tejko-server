package matej.tejkogames.models.yamb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import matej.tejkogames.constants.YambConstants;

public class YambForm {

    private List<Column> columns;

    private int sumA;

    private int sumB;

    private int sumC;

    private int totalSum;

    private int availableBoxes;

    public YambForm(List<Column> columns) {
        this.columns = columns;
        this.sumA = 0;
        this.sumB = 0;
        this.sumC = 0;
        this.totalSum = 0;
        this.availableBoxes = this.columns.size() * YambConstants.NUMBER_OF_BOXES;
    }

    public YambForm() {
    }

    public List<Column> getColumns() {
        return this.columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public int getSumA() {
        return this.sumA;
    }

    public void setSumA(int sumA) {
        this.sumA = sumA;
    }

    public int getSumB() {
        return this.sumB;
    }

    public void setSumB(int sumB) {
        this.sumB = sumB;
    }

    public int getSumC() {
        return this.sumC;
    }

    public void setSumC(int sumC) {
        this.sumC = sumC;
    }

    public int getTotalSum() {
        return this.totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public int getAvailableBoxes() {
        return this.availableBoxes;
    }

    public void setAvailableBoxes(int availableBoxes) {
        this.availableBoxes = availableBoxes;
    }

    @JsonIgnore
    public boolean isAnnouncementRequired() {
        for (Column column : this.columns) {
            if (column.getType() != ColumnType.ANNOUNCEMENT && !column.isFinished()) {
                return false;
            }
        }
        return true;
    }

    public void updateSums() {
        this.sumA = 0;
        this.sumB = 0;
        this.sumC = 0;
        for (Column column : this.columns) {
            column.updateSums();
            this.sumA += column.getSumA();
            this.sumB += column.getSumB();
            this.sumC += column.getSumC();
        }
        this.totalSum = this.sumA + this.sumB + this.sumC;
    }

    public void updateSums(ColumnType columnType) {
        this.sumA = 0;
        this.sumB = 0;
        this.sumC = 0;
        Column col = this.getColumnByType(columnType);
        col.updateSums();
        for (Column column : this.columns) {
            this.sumA += column.getSumA();
            this.sumB += column.getSumB();
            this.sumC += column.getSumC();
        }
        this.totalSum = this.sumA + this.sumB + this.sumC;
    }

    public Column getColumnByType(ColumnType type) {
        for (Column column : this.columns) {
            if (column.getType() == type) {
                return column;
            }
        }
        return null;
    }

}
