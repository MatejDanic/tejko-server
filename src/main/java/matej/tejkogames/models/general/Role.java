package matej.tejkogames.models.general;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.rest.core.annotation.RestResource;

import matej.tejkogames.interfaces.models.RoleInterface;
import matej.tejkogames.models.general.payload.requests.RoleRequest;

@Entity
@Table(name = "auth_role")
@RestResource(rel = "roles", path = "roles")
public class Role implements RoleInterface {

    @Id
    private int id;

    @Column(nullable = false, unique = true)
    private String label;

    @Column
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Role(int id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void updateByRequest(RoleRequest objectRequest) {
        if (objectRequest.getLabel() != null) {
            this.setLabel(objectRequest.getLabel());
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