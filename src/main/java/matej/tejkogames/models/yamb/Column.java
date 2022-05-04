package matej.tejkogames.models.yamb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import matej.tejkogames.models.yamb.enums.BoxType;
import matej.tejkogames.models.yamb.enums.ColumnType;

public class Column {

    private ColumnType type;

    private List<Box> boxes;

    private boolean locked;

    private int sumA;

    private int sumB;

    private int sumC;

    public Column(ColumnType type, List<Box> boxes, boolean locked) {
        this.type = type;
        this.boxes = boxes;
        this.locked = locked;
        this.sumA = 0;
        this.sumB = 0;
        this.sumC = 0;
    }

    public ColumnType getType() {
        return this.type;
    }

    public void setType(ColumnType type) {
        this.type = type;
    }

    public List<Box> getBoxes() {
        return this.boxes;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
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

    public Box getBoxByType(BoxType boxType) {
        for (Box box : this.boxes) {
            if (box.getType() == boxType) {
                return box;
            }
        }
        return null;
    }

    @JsonIgnore
    public boolean isFinished() {
        for (Box box : this.boxes) {
            if (!box.isFilled()) {
                return false;
            }
        }
        return true;
    }

    public void updateSums() {
        this.sumA = 0;
        this.sumB = 0;
        this.sumC = 0;
        for (Box box : this.boxes) {
            if (box.getType() == BoxType.ONES || box.getType() == BoxType.TWOS || box.getType() == BoxType.THREES
                    || box.getType() == BoxType.FOURS || box.getType() == BoxType.FIVES
                    || box.getType() == BoxType.SIXES) {
                sumA += box.getValue();
            } else if (box.getType() == BoxType.TRIPS || box.getType() == BoxType.STRAIGHT
                    || box.getType() == BoxType.BOAT || box.getType() == BoxType.CARRIAGE
                    || box.getType() == BoxType.YAMB) {
                sumC += box.getValue();
            }
        }
        Box ones = this.getBoxByType(BoxType.ONES);
        Box max = this.getBoxByType(BoxType.MAX);
        Box min = this.getBoxByType(BoxType.MIN);
        if (ones.isFilled() && max.isFilled() && min.isFilled()) {
            this.sumB = ones.getValue() * (max.getValue() - min.getValue());
        }
    }

}
