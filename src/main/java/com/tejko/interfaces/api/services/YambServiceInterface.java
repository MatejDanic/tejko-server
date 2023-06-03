package com.tejko.interfaces.api.services;

import java.util.List;
import java.util.UUID;

import com.tejko.exceptions.IllegalActionException;
import com.tejko.models.general.payload.requests.YambRequest;
import com.tejko.models.general.payload.responses.YambResponse;
import com.tejko.models.yamb.Yamb;
import com.tejko.models.yamb.enums.BoxType;
import com.tejko.models.yamb.enums.ColumnType;

public interface YambServiceInterface extends ServiceInterface<UUID, Yamb, YambRequest, YambResponse> {

        public YambResponse rollDiceById(UUID id, List<Integer> diceToRoll) throws IllegalActionException;

        public YambResponse announceById(UUID id, BoxType boxType) throws IllegalActionException;

        public YambResponse fillById(UUID id, ColumnType columnType, BoxType boxType) throws IllegalActionException;
        
        public YambResponse restartById(UUID id) throws IllegalActionException;

        public YambResponse toApiResponse(Yamb yamb);

}
