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

import com.tejko.api.services.RoleService;
import com.tejko.interfaces.api.controllers.RoleControllerInterface;
import com.tejko.models.general.payload.requests.RoleRequest;
import com.tejko.models.general.payload.responses.RoleResponse;
import com.tejko.models.general.payload.responses.UserResponse;

@RestController
@RequestMapping("/api/roles")
public class RoleController implements RoleControllerInterface {

    @Autowired
    RoleService roleService;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<RoleResponse> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(roleService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/bulk")
    @Override
    public ResponseEntity<List<RoleResponse>> getBulkById(@RequestBody Set<UUID> idSet) {
        return new ResponseEntity<>(roleService.getBulkById(idSet), HttpStatus.OK);
    }

    @GetMapping("")
    @Override
    public ResponseEntity<List<RoleResponse>> getAll(@PathVariable Integer page, @PathVariable Integer size,
            @PathVariable String sort, @PathVariable String direction) {
        return new ResponseEntity<>(roleService.getAll(page, size, sort, direction), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("")
    @Override
    public ResponseEntity<RoleResponse> create(@RequestBody RoleRequest objectRequest) {
        return new ResponseEntity<>(roleService.create(objectRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/bulk")
    @Override
    public ResponseEntity<List<RoleResponse>> createBulk(@RequestBody List<RoleRequest> objectRequestList) {
        return new ResponseEntity<>(roleService.createBulk(objectRequestList), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<RoleResponse> updateById(@PathVariable UUID id, @RequestBody RoleRequest roleRequest) {
        return new ResponseEntity<>(roleService.updateById(id, roleRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/bulk")
    @Override
    public ResponseEntity<List<RoleResponse>> updateBulkById(@RequestBody Map<UUID, RoleRequest> idRoleRequestMap) {
        return new ResponseEntity<>(roleService.updateBulkById(idRoleRequestMap), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deleteById(@PathVariable UUID id) {
        roleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/bulk")
    @Override
    public ResponseEntity<?> deleteBulkById(@RequestBody Set<UUID> idSet) {
        roleService.deleteBulkById(idSet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("")
    @Override
    public ResponseEntity<?> deleteAll() {
        roleService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}/users")
    @Override
    public ResponseEntity<List<UserResponse>> getUsersByRoleId(@PathVariable UUID roleId) {
        return new ResponseEntity<>(roleService.getUsersByRoleId(roleId), HttpStatus.OK);
    }

}
