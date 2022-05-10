package matej.tejko.interfaces.api.controllers;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import matej.tejko.models.general.Preference;
import matej.tejko.models.general.Role;
import matej.tejko.models.general.Score;
import matej.tejko.models.general.User;
import matej.tejko.models.general.payload.requests.UserRequest;

public interface UserControllerInterface extends ControllerInterface<UUID, User, UserRequest> {

    @PutMapping("/{id}/assign-role")
    public ResponseEntity<Set<Role>> assignRoleByUserId(@PathVariable UUID id, @RequestBody Integer roleId);

    @GetMapping("/{id}/preferences")
    public ResponseEntity<Preference> getPreferenceByUserId(UUID id);

    @DeleteMapping("/{id}/preferences")
    public ResponseEntity<String> deletePreferenceByUserId(UUID id);

    @GetMapping("/{id}/scores")
    public ResponseEntity<List<Score>> getScoresByUserId(UUID id);

}
