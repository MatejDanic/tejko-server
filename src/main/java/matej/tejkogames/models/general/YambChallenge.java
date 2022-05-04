package matej.tejkogames.models.general;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.rest.core.annotation.RestResource;

import matej.tejkogames.models.general.payload.requests.YambChallengeRequest;
import matej.tejkogames.interfaces.models.YambChallengeInterface;

@Entity
@Table(name = "yamb_challenge")
@RestResource(rel = "challenges", path = "challenges")
public class YambChallenge implements YambChallengeInterface {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @JsonIncludeProperties({ "id" })
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    private Set<Yamb> yambs;

    @OneToMany(mappedBy = "challenge")
    private Set<UserYambChallenge> userYambChallenges;

    @Type(type = "json_binary")
    @Column(columnDefinition = "jsonb")
    private Map<UUID, Boolean> userIdAcceptedMap;

    public YambChallenge() {
    }

    public YambChallenge(Set<User> users, Set<Yamb> yambs) {
        this.yambs = yambs;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<Yamb> getYambs() {
        return yambs;
    }

    public void setYambs(Set<Yamb> yambs) {
        this.yambs = yambs;
    }

    @Override
    public void updateByRequest(YambChallengeRequest objectRequest) {
        if (objectRequest.getYambs() != null) {
            this.setYambs(objectRequest.getYambs());
        }
    }

    @Override
    public boolean hasPermission(UUID userId) {
        for (Yamb yamb : yambs) {
            if (yamb.getUser().getId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

}