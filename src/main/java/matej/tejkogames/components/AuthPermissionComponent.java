package matej.tejkogames.components;

import java.util.UUID;

import org.springframework.stereotype.Component;

import matej.tejkogames.interfaces.api.ModelInterface;

@Component
public class AuthPermissionComponent {

    public boolean hasPermission(UUID userId, ModelInterface<?> object) {
        return object.hasPermission(userId);
    }

}