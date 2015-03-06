package io.vinnie.friarscout;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.vinnie.friarscout.model.Team;

/**
 * Created by vmagro on 3/6/15.
 */
public class TeamFragment extends Fragment {

    private static final String ARG_TEAM = "team";
    private Team team;

    @InjectView(R.id.team_card)
    TeamCard teamCard;

    public static TeamFragment newInstance(Team team) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TEAM, team);
        TeamFragment frag = new TeamFragment();
        frag.setArguments(args);
        return frag;
    }

    public TeamFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            team = (Team) getArguments().getSerializable(ARG_TEAM);
        } else {
            team = (Team) savedInstanceState.getSerializable(ARG_TEAM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);

        ButterKnife.inject(this, view);

        teamCard.setTeam(team);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(ARG_TEAM, team);
    }

}
