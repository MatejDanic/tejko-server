package matej.tejkogames.models.general;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import org.hibernate.annotations.GenericGenerator;
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
    @Column
    private UUID id;

    @JsonIncludeProperties({ "user", "challenge" })
    @OneToMany(mappedBy = "challenge")
    private Set<UserYambChallenge> userYambChallenges;

    public YambChallenge() {
    }

    public YambChallenge(Set<UserYambChallenge> userYambChallenges) {
        this.userYambChallenges = userYambChallenges;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<UserYambChallenge> getUserYambChallenges() {
        return userYambChallenges;
    }

    public void setUserYambChallenges(Set<UserYambChallenge> userYambChallenges) {
        this.userYambChallenges = userYambChallenges;
    }

    @Override
    public void updateByRequest(YambChallengeRequest objectRequest) {
        if (objectRequest.getUserYambChallenges() != null) {
            this.setUserYambChallenges(objectRequest.getUserYambChallenges());
        }
    }

    @Override
    public boolean hasPermission(UUID userId) {
        for (UserYambChallenge userYambChallenge : userYambChallenges) {
            if (userYambChallenge.getUser().getId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

}