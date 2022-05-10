package matej.tejko.models.general.payload.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

public abstract class GameRequest {

    @NotBlank
    private UUID userId;

    @NotBlank
    private Integer appId;

    public GameRequest() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

}
