package io.vinnie.friarscout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.vinnie.friarscout.model.Team;

/**
 * Created by vmagro on 3/6/15.
 */
public class TeamCard extends RelativeLayout {

    @InjectView(R.id.team_image)
    ImageView image;

    @InjectView(R.id.team_number)
    TextView teamNumber;

    @InjectView(R.id.team_name)
    TextView teamName;

    public TeamCard(Context context) {
        this(context, null);
    }

    public TeamCard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TeamCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.team_card, this, true);

        ButterKnife.inject(this, this);
    }

    public void setTeam(Team team) {
        Picasso.with(getContext()).load(team.getImage()).into(image);

        teamNumber.setText(String.valueOf(team.getNumber()));
        teamName.setText(team.getName());
    }

}
