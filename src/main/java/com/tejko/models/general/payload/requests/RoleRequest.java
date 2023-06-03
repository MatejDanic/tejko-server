package com.tejko.models.general.payload.requests;

import javax.validation.constraints.NotBlank;

public class RoleRequest {

    @NotBlank
    private Integer id;

    @NotBlank
    private String label;

    private String description;

    public RoleRequest(int id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

}
