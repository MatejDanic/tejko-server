package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matej.tejkogames.api.services.PreferenceService;
import matej.tejkogames.interfaces.api.controllers.PreferenceControllerInterface;
import matej.tejkogames.models.general.Preference;
import matej.tejkogames.models.general.payload.requests.PreferenceRequest;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@RestController
@RequestMapping("/api/preferences")
public class PreferenceController implements PreferenceControllerInterface {

    @Autowired
    PreferenceService preferenceService;

    @PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), preferenceService.getById(#id))")
    @Override
    public ResponseEntity<Preference> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(preferenceService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<Preference>> getBulkById(@RequestBody Set<UUID> idSet) {
        return new ResponseEntity<>(preferenceService.getBulkById(idSet), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<Preference>> getAll(@PathVariable Integer page, @PathVariable Integer size,
            @PathVariable String sort, @PathVariable String direction) {
        return new ResponseEntity<>(preferenceService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<Preference> create(PreferenceRequest objectRequest) {
        return new ResponseEntity<>(preferenceService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<Preference>> createBulk(List<PreferenceRequest> objectRequestList) {
        return new ResponseEntity<>(preferenceService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), preferenceService.getById(#id))")
    @Override
    public ResponseEntity<Preference> updateById(UUID id, PreferenceRequest objectRequest) {
        return new ResponseEntity<>(preferenceService.updateById(id, objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<List<Preference>> updateBulkById(Map<UUID, PreferenceRequest> idObjectRequestMap) {
        return new ResponseEntity<>(preferenceService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteById(@PathVariable UUID id) {
        preferenceService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("Preference",
                "Preference has been successfully deleted."), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteBulkById(Set<UUID> idSet) {
        preferenceService.deleteBulkById(idSet);
        return new ResponseEntity<>(new MessageResponse("Preference",
                "All preferences have been successfully deleted."), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<MessageResponse> deleteAll() {
        preferenceService.deleteAll();
        return new ResponseEntity<>(new MessageResponse("Preference",
                "All preferences have been successfully deleted."), HttpStatus.OK);
    }

}
