package matej.tejkogames.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Preference> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(preferenceService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    @Override
    public ResponseEntity<List<Preference>> getAll(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String direction) {
        return new ResponseEntity<>(preferenceService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}")
    @Override
    public ResponseEntity<Preference> create(@RequestBody PreferenceRequest objectRequest) {
        return new ResponseEntity<>(preferenceService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/bulk")
    @Override
    public ResponseEntity<List<Preference>> createBulk(@RequestBody List<PreferenceRequest> objectRequestList) {
        return new ResponseEntity<>(preferenceService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), preferenceService.getById(#id))")
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Preference> updateById(@PathVariable UUID id,
            @RequestBody PreferenceRequest objectRequest) {
        return new ResponseEntity<>(preferenceService.updateById(id, objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/bulk")
    @Override
    public ResponseEntity<List<Preference>> updateBulkById(
            @RequestBody Map<UUID, PreferenceRequest> idObjectRequestMap) {
        return new ResponseEntity<>(preferenceService.updateBulkById(idObjectRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<MessageResponse> deleteById(@RequestHeader(value = "Authorization") String headerAuth,
            @PathVariable UUID id) {
        preferenceService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("Preference",
                "Preference has been successfully deleted."), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("")
    @Override
    public ResponseEntity<MessageResponse> deleteAll(@RequestHeader(value = "Authorization") String headerAuth) {
        preferenceService.deleteAll();
        return new ResponseEntity<>(new MessageResponse("Preference",
                "All preferences have been successfully deleted."), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/bulk")
    @Override
    public ResponseEntity<MessageResponse> deleteBulkById(@RequestHeader(value = "Authorization") String headerAuth,
            @RequestBody Set<UUID> idSet) {
        preferenceService.deleteBulkById(idSet);
        return new ResponseEntity<>(new MessageResponse("Preference",
                "All preferences have been successfully deleted."), HttpStatus.OK);
    }

}
