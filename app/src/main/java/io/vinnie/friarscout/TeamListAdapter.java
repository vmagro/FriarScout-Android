package io.vinnie.friarscout;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.vinnie.friarscout.api.Team;

/**
 * Created by vmagro on 3/3/15.
 */
public class TeamListAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final List<Team> teams;

    public TeamListAdapter(Activity context, List<Team> teams) {
        this.teams = teams;
        inflater = context.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return teams.size();
    }

    @Override
    public Object getItem(int position) {
        return teams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return teams.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if (convertView != null) {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.team_list_item, parent, false);
            holder = new ViewHolder();
            ButterKnife.inject(holder, view);
            view.setTag(holder);
        }

        Team team = teams.get(position);
        holder.teamNumber.setText("" + team.getTeamNumber());
        holder.teamName.setText(team.getNickname());

        return view;
    }

    class ViewHolder {
        @InjectView(R.id.team_number)
        public TextView teamNumber;

        @InjectView(R.id.team_name)
        public TextView teamName;
    }
}
