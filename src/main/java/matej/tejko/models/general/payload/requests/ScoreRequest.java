package matej.tejko.models.general.payload.requests;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ScoreRequest {

    @NotBlank
    private UUID userId;

    @NotBlank
    private Integer appId;

    @NotBlank
    @Min(0)
    private Integer value;

    protected ScoreRequest() {
    }

    public ScoreRequest(UUID userId, Integer appId, Integer value) {
        this.userId = userId;
        this.appId = appId;
        this.value = value;
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}