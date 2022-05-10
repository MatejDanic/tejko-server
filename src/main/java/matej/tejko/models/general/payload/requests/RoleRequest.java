package matej.tejko.models.general.payload.requests;

import javax.validation.constraints.NotBlank;

public class RoleRequest {

    @NotBlank
    private Integer id;

    @NotBlank
    private String label;

    private String description;

    public RoleRequest() {
    }

    public RoleRequest(Integer id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

}