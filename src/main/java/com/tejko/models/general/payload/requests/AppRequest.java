package com.tejko.models.general.payload.requests;

import javax.validation.constraints.NotBlank;

public class AppRequest {

    @NotBlank
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    public AppRequest(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public AppRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
