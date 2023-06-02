package com.tejko.interfaces.api.services;

import java.util.List;
import java.util.UUID;

import com.tejko.exceptions.IllegalActionException;
import com.tejko.models.general.payload.requests.YambRequest;
import com.tejko.models.yamb.Yamb;
import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;

public interface YambServiceInterface extends ServiceInterface<UUID, Yamb, YambRequest> {

        public Yamb rollDiceById(UUID id, List<Integer> diceToRoll) throws IllegalActionException;

        public Yamb announceById(UUID id, BoxType boxType) throws IllegalActionException;

        public Yamb fillById(UUID id, ColumnType columnType, BoxType boxType) throws IllegalActionException;
        
        public Yamb restartById(UUID id) throws IllegalActionException;

}
