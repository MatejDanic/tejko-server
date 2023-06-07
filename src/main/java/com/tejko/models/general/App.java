package com.tejko.models.general;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tejko.models.DatabaseEntity;

@Entity
@Table(name = "app")
public class App extends DatabaseEntity {

    @Id
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "app")
    private Set<Game> gameSet;

    private App() {}

    private App(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static App create(int id, String name, String description) {
        return new App(id, name, description);
    }

    public int getId() {
        return id;
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
