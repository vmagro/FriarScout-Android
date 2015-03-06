package io.vinnie.friarscout.event;

import io.vinnie.friarscout.model.Team;

/**
 * Created by vmagro on 3/6/15.
 */
public class SelectTeam {
    private final Team team;

    public SelectTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }
}
