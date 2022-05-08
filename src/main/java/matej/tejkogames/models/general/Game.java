package matej.tejkogames.models.general;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.rest.core.annotation.RestResource;

import matej.tejkogames.interfaces.models.GameInterface;
import matej.tejkogames.models.general.payload.requests.GameRequest;

@Entity
@Table(name = "game")
@RestResource(rel = "games", path = "games")
public class Game implements GameInterface {

    @Id
    private int id;

    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private Set<Score> scores;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    public Game() {
    }

    public Game(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
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

    @Override
    public void updateByRequest(GameRequest objectRequest) {
        if (objectRequest.getName() != null) {
            this.setName(objectRequest.getName());
        }
        if (objectRequest.getDescription() != null) {
            this.setDescription(objectRequest.getDescription());
        }
    }

    @Override
    public boolean hasPermission(UUID userId) {
        // TODO Auto-generated method stub
        return false;
    }

}
