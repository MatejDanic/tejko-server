package com.tejko.models.general.payload.requests;

import javax.validation.constraints.NotBlank;

import com.tejko.models.general.App;
import com.tejko.models.general.payload.RestRequest;

public class AppRequest extends RestRequest<App> {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    public AppRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
