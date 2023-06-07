package com.tejko.interfaces.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tejko.interfaces.api.ObjectControllerInterface;
import com.tejko.models.general.Role;
import com.tejko.models.general.payload.requests.RoleRequest;
import com.tejko.models.general.payload.responses.RoleResponse;
import com.tejko.models.general.payload.responses.UserResponse;

public interface RoleControllerInterface extends ObjectControllerInterface<Integer, Role, RoleRequest, RoleResponse> {

    @GetMapping("/{id}/users")
    public ResponseEntity<List<UserResponse>> getUsersByRoleId(@PathVariable Integer id);

}
