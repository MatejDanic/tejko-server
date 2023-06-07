package com.tejko.models.general.payload.responses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.tejko.models.yamb.Yamb;
import com.tejko.models.yamb.enums.BoxType;

public class YambResponse extends ApiResponse<Yamb> {
    
    private UUID id;
    private SheetResponse sheet;
    private List<DiceResponse> diceList;
    private int rollCount;
    private BoxType announcement;

    public YambResponse(LocalDateTime createdDate, LocalDateTime lastModifiedDate, UUID id, SheetResponse sheet, List<DiceResponse> diceList, int rollCount, BoxType announcement) {
        super(createdDate, lastModifiedDate);
        this.id = id;
        this.sheet = sheet;
        this.diceList = diceList;
        this.rollCount = rollCount;
        this.announcement = announcement;
    }

    public UUID getId() {
        return id;
    }

    public SheetResponse getSheet() {
        return sheet;
    }

    public List<DiceResponse> getDiceList() {
        return diceList;
    }

    public int getRollCount() {
        return rollCount;
    }

    public BoxType getAnnouncement() {
        return announcement;
    }   

}