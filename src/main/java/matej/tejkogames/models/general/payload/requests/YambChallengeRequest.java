package matej.tejkogames.models.general.payload.requests;

import java.util.Set;

import matej.tejkogames.models.general.Yamb;

public class YambChallengeRequest {

    private Set<Yamb> yambs;

    public YambChallengeRequest() {
    }

    public Set<Yamb> getYambs() {
        return yambs;
    }

    public void setYambs(Set<Yamb> yambs) {
        this.yambs = yambs;
    }

}
