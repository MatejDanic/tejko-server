package com.tejko.models.general;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tejko.models.DatabaseEntityWithId;

@Entity
@Table(name = "app")
public class App extends DatabaseEntityWithId {

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "app")
    private Set<Game> gameSet;

    private App() {}

    private App(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static App create(String name, String description) {
        return new App(name, description);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
