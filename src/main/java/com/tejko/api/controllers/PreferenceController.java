package com.tejko.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejko.api.services.PreferenceService;
import com.tejko.interfaces.api.controllers.PreferenceControllerInterface;
import com.tejko.models.general.Preference;
import com.tejko.models.general.payload.requests.PreferenceRequest;
import com.tejko.models.general.payload.responses.MessageResponse;

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
    @GetMapping("/bulk")
    @Override
    public ResponseEntity<List<Preference>> getBulkById(@RequestBody Set<UUID> idSet) {
        return new ResponseEntity<>(preferenceService.getBulkById(idSet), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    @Override
    public ResponseEntity<List<Preference>> getAll(@PathVariable Integer page, @PathVariable Integer size,
            @PathVariable String sort, @PathVariable String direction) {
        return new ResponseEntity<>(preferenceService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    @Override
    public ResponseEntity<Preference> create(PreferenceRequest objectRequest) {
        return new ResponseEntity<>(preferenceService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/bulk")
    @Override
    public ResponseEntity<List<Preference>> createBulk(List<PreferenceRequest> objectRequestList) {
        return new ResponseEntity<>(preferenceService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), preferenceService.getById(#id))")
    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<Preference> updateById(UUID id, JsonPatch objectPatch)
            throws JsonProcessingException, JsonPatchException {
        return new ResponseEntity<>(preferenceService.updateById(id, objectPatch), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/bulk")
    @Override
    public ResponseEntity<List<Preference>> updateBulkById(Map<UUID, JsonPatch> idObjectPatchMap)
            throws JsonProcessingException, JsonPatchException {
        return new ResponseEntity<>(preferenceService.updateBulkById(idObjectPatchMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<MessageResponse> deleteById(@PathVariable UUID id) {
        preferenceService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("Preference",
                "Preference has been successfully deleted."), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/bulk")
    @Override
    public ResponseEntity<MessageResponse> deleteBulkById(Set<UUID> idSet) {
        preferenceService.deleteBulkById(idSet);
        return new ResponseEntity<>(new MessageResponse("Preference",
                "All preferences have been successfully deleted."), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("")
    @Override
    public ResponseEntity<MessageResponse> deleteAll() {
        preferenceService.deleteAll();
        return new ResponseEntity<>(new MessageResponse("Preference",
                "All preferences have been successfully deleted."), HttpStatus.OK);
    }

}
