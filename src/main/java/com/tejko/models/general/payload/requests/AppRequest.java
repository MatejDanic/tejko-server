package com.tejko.models.general.payload.requests;

import javax.validation.constraints.NotBlank;

public class AppRequest {

    @NotBlank
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    public AppRequest(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
