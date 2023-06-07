package com.tejko.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.tejko.api.services.UserChallengeService;
import com.tejko.interfaces.api.controllers.UserChallengeControllerInterface;
import com.tejko.models.general.ids.UserChallengeId;
import com.tejko.models.general.payload.requests.UserChallengeRequest;
import com.tejko.models.general.payload.responses.UserChallengeResponse;

@RestController
@RequestMapping("/api/user-challenges")
public class UserChallengeController implements UserChallengeControllerInterface {

    @Autowired
    UserChallengeService userChallengeService;

    @PreAuthorize("hasAuthority('ADMIN') or @authPermissionComponent.hasPermission(@jwtComponent.getUserIdFromHeader(#headerAuth), userChallengeService.getById(#id))")
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UserChallengeResponse> getById(@PathVariable UserChallengeId id) {
        return new ResponseEntity<>(userChallengeService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/bulk")
    @Override
    public ResponseEntity<List<UserChallengeResponse>> getBulkById(@RequestBody Set<UserChallengeId> idSet) {
        return new ResponseEntity<>(userChallengeService.getBulkById(idSet), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    @Override
    public ResponseEntity<List<UserChallengeResponse>> getAll(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String sort, @PathVariable String direction) {
        return new ResponseEntity<>(userChallengeService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("")
    @Override
    public ResponseEntity<UserChallengeResponse> create(@RequestBody UserChallengeRequest objectRequest) {
        return new ResponseEntity<>(userChallengeService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/bulk")
    @Override
    public ResponseEntity<List<UserChallengeResponse>> createBulk(@RequestBody List<UserChallengeRequest> objectRequestList) {
        return new ResponseEntity<>(userChallengeService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<UserChallengeResponse> updateById(@PathVariable UserChallengeId id, @RequestBody UserChallengeRequest objectPatch) {
        return new ResponseEntity<>(userChallengeService.updateById(id, objectPatch), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/bulk")
    @Override
    public ResponseEntity<List<UserChallengeResponse>> updateBulkById(@RequestBody Map<UserChallengeId, UserChallengeRequest> idUserChallengeRequest) {
        return new ResponseEntity<>(userChallengeService.updateBulkById(idUserChallengeRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deleteById(@PathVariable UserChallengeId id) {
        userChallengeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/bulk")
    @Override
    public ResponseEntity<?> deleteBulkById(@RequestBody Set<UserChallengeId> idSet) {
        userChallengeService.deleteBulkById(idSet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("")
    @Override
    public ResponseEntity<?> deleteAll() {
        userChallengeService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
