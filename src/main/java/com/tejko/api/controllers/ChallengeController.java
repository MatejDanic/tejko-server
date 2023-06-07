package com.tejko.api.controllers;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejko.api.services.ChallengeService;
import com.tejko.interfaces.api.controllers.ChallengeControllerInterface;
import com.tejko.models.general.payload.requests.ChallengeRequest;
import com.tejko.models.general.payload.responses.ChallengeResponse;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController implements ChallengeControllerInterface {

    @Autowired
    ChallengeService challengeService;

    @PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), challengeService.getById(#id))")
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ChallengeResponse> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(challengeService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/bulk")
    @Override
    public ResponseEntity<List<ChallengeResponse>> getBulkById(@RequestBody Set<UUID> idSet) {
        return new ResponseEntity<>(challengeService.getBulkById(idSet), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    @Override
    public ResponseEntity<List<ChallengeResponse>> getAll(@PathVariable Integer page, @PathVariable Integer size,
            @PathVariable String sort, @PathVariable String direction) {
        return new ResponseEntity<>(challengeService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("")
    @Override
    public ResponseEntity<ChallengeResponse> create(@RequestBody ChallengeRequest objectRequest) {
        return new ResponseEntity<>(challengeService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/bulk")
    @Override
    public ResponseEntity<List<ChallengeResponse>> createBulk(@RequestBody List<ChallengeRequest> objectRequestList) {
        return new ResponseEntity<>(challengeService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<ChallengeResponse> updateById(@PathVariable UUID id, @RequestBody ChallengeRequest challengeRequest)  {
        return new ResponseEntity<>(challengeService.updateById(id, challengeRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/bulk")
    @Override
    public ResponseEntity<List<ChallengeResponse>> updateBulkById(@RequestBody Map<UUID, ChallengeRequest> idChallengeRequestMap) {
        return new ResponseEntity<>(challengeService.updateBulkById(idChallengeRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deleteById(@PathVariable UUID id) {
        challengeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/bulk")
    @Override
    public ResponseEntity<?> deleteBulkById(@RequestBody Set<UUID> idSet) {
        challengeService.deleteBulkById(idSet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("")
    @Override
    public ResponseEntity<?>  deleteAll() {
        challengeService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
