package com.tejko.models.general.payload.requests;

import javax.validation.constraints.NotBlank;

import com.tejko.models.general.Role;

public class RoleRequest extends ApiRequest<Role> {

    @NotBlank
    private String label;

    private String description;

    public RoleRequest(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

}
