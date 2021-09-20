package matej.tejkogames.interfaces.services;

import java.util.UUID;

import matej.tejkogames.models.yamb.Yamb;

public interface YambService extends ServiceInterface<Yamb, UUID> {

    public boolean hasPermission(UUID id, String username);

}
