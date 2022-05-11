package com.tejko.interfaces.api.services;

import java.util.UUID;

import com.tejko.models.general.payload.requests.YambRequest;
import com.tejko.models.yamb.Yamb;

public interface YambServiceInterface extends ServiceInterface<UUID, Yamb, YambRequest> {

}
