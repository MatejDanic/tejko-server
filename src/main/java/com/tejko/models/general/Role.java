package com.tejko.models.general;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tejko.models.DatabaseEntityWithId;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "auth_role")
@RestResource(rel = "roles", path = "roles")
public class Role extends DatabaseEntityWithId {

    @Column(nullable = false, unique = true)
    private String label;

    @Column
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    protected Role() { }

    protected Role(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public static Role create(String label, String description) {
        return new Role(label, description);
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}