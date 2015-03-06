package io.vinnie.friarscout;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.vinnie.friarscout.event.SelectTeam;
import io.vinnie.friarscout.model.Team;

/**
 * Created by vmagro on 3/6/15.
 */
public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.ViewHolder> {

    private ArrayList<Team> teams = new ArrayList<Team>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TeamCard card;

        public ViewHolder(TeamCard v) {
            super(v);
            card = v;
        }
    }

    public TeamListAdapter() {
        for (int i = 0; i < 10; i++) {
            teams.add(new Team(3309, "Friarbots", "http://peetahzee.com/me.jpg"));
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TeamListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        TeamCard v = new TeamCard(parent.getContext());

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TeamListAdapter.ViewHolder holder, int position) {
        final Team team = teams.get(position);
        holder.card.setTeam(team);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FSBus.bus.post(new SelectTeam(team));
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return teams.size();
    }
}
