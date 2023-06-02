package com.tejko.models.yamb;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tejko.constants.YambConstants;
import com.tejko.exceptions.IllegalActionException;
import com.tejko.models.general.Game;
import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;
import com.tejko.utils.YambUtil;

@Entity
@TypeDef(name = "json_binary", typeClass = JsonBinaryType.class)
public class Yamb extends Game {

    @Type(type = "json_binary")
    @Column(columnDefinition = "jsonb")
    private Sheet sheet;

    @Type(type = "json_binary")
    @Column(columnDefinition = "jsonb")
    private Map<Integer, Dice> diceMap;

    @Column
    private int rollCount = 0;
    
    @Column
    public BoxType announcement = null;

    private Yamb() {}

    private Yamb(Sheet sheet, Map<Integer, Dice> diceMap) {
        this.sheet = sheet;
        this.diceMap = diceMap;
    }

    public static Yamb createYamb() {
        return new Yamb(Sheet.createSheet(), generateDiceMap());
    }

    public Sheet getSheet() {
        return sheet;
    }

    private static Map<Integer, Dice> generateDiceMap() {
        Map<Integer, Dice> diceMap = new HashMap<>();
        for (int i = 0; i < YambConstants.NUMBER_OF_DICE; i++) {
            diceMap.put(i, Dice.createDice(i));
        }
        return diceMap;
    }

    @JsonProperty("diceList")
    public Collection<Dice> getDiceList() { 
        return diceMap.values();
    }


    @JsonProperty("announcementRequired")
    public boolean isAnnouncementRequired() {
        return announcement != null && sheet.areAllNonAnnouncementColumnsCompleted();
    }
    
    public void rollDice(List<Integer> diceToRoll) {
        validateRollAction();
        // always roll all dice for the first roll
        if (rollCount == 0) {
            for (Dice dice : diceMap.values()) {
                dice.roll();
            }
        } else {
            for (int diceOrder : diceToRoll) {
                Dice dice = diceMap.get(diceOrder);
                dice.roll();
            }
        }
        rollCount += 1;
    }   
    
    public void fillBox(ColumnType columnType, BoxType boxType) {
        validateFillBoxAction(columnType, boxType);
        sheet.fillBox(columnType, boxType, YambUtil.calculateScore(diceMap.values(), boxType));
    }
    
    public void announce(BoxType boxType) {
        validateAnnouncementAction(boxType);
        announcement = boxType;
    }

    public void restart() {
        validateRestartAction();
        rollCount = 0;
        announcement = null;
        sheet = Sheet.createSheet();
        diceMap = generateDiceMap();
    }

    private void validateRollAction() {
        if (rollCount == 3) {
            throw new IllegalActionException(YambConstants.ERROR_MESSAGE_ROLL_LIMIT_REACHED);
        } else if (rollCount == 1 && isAnnouncementRequired()) {
            throw new IllegalActionException(YambConstants.ERROR_MESSAGE_ANNOUNCEMENT_REQUIRED);
        }
    }

    private void validateFillBoxAction(ColumnType columnType, BoxType boxType) {
        Box box = sheet.getColumnMap().get(columnType).getBoxMap().get(boxType);
        if (box.isFilled()) {
            throw new IllegalActionException(YambConstants.ERROR_MESSAGE_BOX_ALREADY_FILLED);
        } else if (!box.isAvailable()) {
			throw new IllegalActionException(YambConstants.ERROR_MESSAGE_BOX_NOT_AVAILABLE);
        } else if (rollCount == 0) {
			throw new IllegalActionException(YambConstants.ERROR_MESSAGE_DICE_ROLL_REQUIRED);
        } else if (announcement != null && (columnType != ColumnType.ANNOUNCEMENT || boxType != announcement)) {
			throw new IllegalActionException(YambConstants.ERROR_MESSAGE_BOX_NOT_ANNOUNCED);
        }
    }

    private void validateAnnouncementAction(BoxType boxType) {
        if (announcement != null) {
            throw new IllegalActionException(YambConstants.ERROR_MESSAGE_ANNOUNCEMENT_ALREADY_DECLARED);
        } else if (rollCount > 1) {
            throw new IllegalActionException(YambConstants.ERROR_MESSAGE_ANNOUNCEMENT_NOT_AVAILABLE);
        }
    }

    private void validateRestartAction() {
        if (sheet.isCompleted()) {
            throw new IllegalActionException(YambConstants.ERROR_MESSAGE_RESTART_COMPLETED_SHEET);
        }
    }

}
