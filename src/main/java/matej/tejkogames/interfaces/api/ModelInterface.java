package matej.tejkogames.interfaces.api;

import java.util.UUID;

public interface ModelInterface<R> {

	public void updateByRequest(R objectRequest);

	public boolean hasPermission(UUID userId);

}
