package com.tejko.models.general.payload.responses;

import com.tejko.models.general.enums.ResponseStatus;
import com.tejko.models.yamb.Yamb;

public class YambResponse extends ApiResponse<Yamb> {

    public YambResponse(ResponseStatus status, String message, Yamb object) {
        super(status, message, object);
    }

    public YambResponse(Yamb object) {
        super(object);
    }

}