package matej.tejkogames.models.general.payload.requests;

import java.util.Set;

import matej.tejkogames.models.general.Score;
import matej.tejkogames.models.general.User;
import matej.tejkogames.models.yamb.Yamb;

public class YambChallengeRequest {

    private Set<User> users;

    private Set<Score> scores;

    private Set<Yamb> yambs;

    public YambChallengeRequest() {
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Set<Yamb> getYambs() {
        return yambs;
    }

    public void setYambs(Set<Yamb> yambs) {
        this.yambs = yambs;
    }
    
}
