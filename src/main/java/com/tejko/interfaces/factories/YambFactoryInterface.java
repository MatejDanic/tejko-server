package com.tejko.interfaces.factories;

import com.tejko.interfaces.DatabaseEntityFactory;
import com.tejko.models.general.payload.requests.YambRequest;
import com.tejko.models.yamb.Yamb;

public interface YambFactoryInterface extends DatabaseEntityFactory<Yamb, YambRequest> {

}
