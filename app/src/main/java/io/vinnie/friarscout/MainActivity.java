package io.vinnie.friarscout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import butterknife.ButterKnife;
import io.vinnie.friarscout.event.SelectTeam;
import io.vinnie.friarscout.model.User;


public class MainActivity extends Activity implements LoginFragment.OnLoginListener, MainFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(getApplicationContext());
        FSBus.bus = new Bus();

        FSBus.bus.register(this);

        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    //.add(R.id.container, MainFragment.newInstance(), "main")
                    .add(R.id.container, TeamListFragment.newInstance(), "teamList")
                    .addToBackStack("teamList")
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LoginFragment.RC_SIGN_IN) {
            getFragmentManager().findFragmentByTag("login").onActivityResult(requestCode, resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onLoggedIn(final String accountName, final String displayName) {
        final Firebase userFirebase = new Firebase("https://friarscout.firebaseio.com/users/");
        userFirebase.child(Util.emailToKey(accountName)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User u = dataSnapshot.getValue(User.class);
                    getFragmentManager().beginTransaction()
                            .addToBackStack("main")
                            .replace(R.id.container, MainFragment.newInstance(), "main")
                            .commit();
                } else {
                    userFirebase.child(Util.emailToKey(accountName)).setValue(new User(accountName, displayName));
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Subscribe
    public void onTeamSelected(SelectTeam event) {
        getFragmentManager().beginTransaction()
                .addToBackStack("team")
                .replace(R.id.container, TeamFragment.newInstance(event.getTeam()), "team")
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
