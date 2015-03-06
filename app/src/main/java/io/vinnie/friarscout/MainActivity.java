package io.vinnie.friarscout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import butterknife.ButterKnife;
import io.vinnie.friarscout.model.Team;
import io.vinnie.friarscout.model.User;


public class MainActivity extends Activity implements LoginFragment.OnLoginListener, TeamListFragment.OnTeamListListener, MainFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        Firebase.setAndroidContext(getApplicationContext());

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    //.add(R.id.container, MainFragment.newInstance(), "main")
                    .add(R.id.container, TeamListFragment.newInstance(), "teamList")
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

    @Override
    public void onTeamSelected(Team team) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
