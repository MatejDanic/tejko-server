package com.tejko.models.yamb;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.tejko.models.general.Game;
import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;
import com.tejko.models.yamb.enums.YambType;
import com.tejko.utils.YambUtil;

@Entity
@TypeDef(name = "json_binary", typeClass = JsonBinaryType.class)
public class Yamb extends Game {

    @Column
    private YambType type = YambType.CLASSIC;

    @Min(5)
    @Max(6)
    @Column(updatable = false)
    private int numberOfDice = 5;

    @Type(type = "json_binary")
    @Column(columnDefinition = "jsonb")
    private YambForm form;

    @Size(min = 1, max = 6)
    private String formCode;

    @Column
    private BoxType announcement = null;

    @Type(type = "json_binary")
    @Column(columnDefinition = "jsonb")
    private Set<Dice> diceSet;

    @Column
    private int rollCount = 0;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    public Yamb() {
    }

    public YambType getType() {
        return type;
    }

    public void setType(YambType type) {
        this.type = type;
    }

    public int getNumberOfDice() {
        return numberOfDice;
    }

    public void setNumberOfDice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    public YambForm getForm() {
        return form;
    }

    public void setForm(YambForm form) {
        this.form = form;
    }

    public String getForCodem() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public BoxType getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(BoxType announcement) {
        this.announcement = announcement;
    }

    public Set<Dice> getDiceSet() {
        return diceSet;
    }

    public void setDiceSet(Set<Dice> diceSet) {
        this.diceSet = diceSet;
    }

    public int getRollCount() {
        return rollCount;
    }

    public void setRollCount(int rollCount) {
        this.rollCount = rollCount;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void unfreezeAllDice() {
        for (Dice dice : diceSet) {
            dice.setFrozen(false);
        }
    }

    public void freezeAllDice() {
        for (Dice dice : diceSet) {
            dice.setFrozen(true);
        }
    }

    public void rollDice() {
        rollCount++;

        for (Dice dice : diceSet) {
            if (!dice.isFrozen()) {
                dice.roll();
            }
        }
    }

    public void fill(ColumnType columnType, BoxType boxType) {

        int score = YambUtil.calculateScore(diceSet, boxType);

        for (Box box : form.getColumnByType(columnType).getBoxes()) {
            if (box.getType() == boxType) {
                box.fill(score);
            } else if (box.isNext(columnType, boxType)) {
                box.setAvailable(true);
            }
        }

        form.updateSums(columnType);
        form.setAvailableBoxes(form.getAvailableBoxes() - 1);

        unfreezeAllDice();
        rollCount = 0;
        announcement = null;

    }

}
