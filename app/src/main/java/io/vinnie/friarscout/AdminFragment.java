package io.vinnie.friarscout;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.vinnie.friarscout.model.User;

/**
 * Created by vmagro on 3/3/15.
 */
public class AdminFragment extends Fragment {

    public static AdminFragment newInstance() {
        return new AdminFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.promote)
    public void promote() {
        final Firebase usersRef = new Firebase("https://friarscout.firebaseio.com/users/");
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final HashMap<String, User> users = new HashMap<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    users.put(child.getValue(User.class).getName(), child.getValue(User.class));
                }
                final CharSequence[] names = new ArrayList<CharSequence>(users.keySet()).toArray(new CharSequence[users.keySet().size()]);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Promote user(s)")
                        .setMultiChoiceItems(names, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                User u = users.get(names[which]);
                                u.setAdmin(isChecked);
                            }

                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (User u : users.values()) {
                                    u.setAdmin(true);
                                    usersRef.child(Util.emailToKey(u.getEmail())).setValue(u);
                                }
                            }
                        });
                builder.show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
