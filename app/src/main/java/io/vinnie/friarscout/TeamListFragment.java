package io.vinnie.friarscout;

import android.app.Activity;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.vinnie.friarscout.api.Team;
import io.vinnie.friarscout.api.TheBlueAllianceMgr;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by vmagro on 3/3/15.
 */
public class TeamListFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TeamListFragment.
     */
    public static TeamListFragment newInstance() {
        return new TeamListFragment();
    }

    public TeamListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        TheBlueAllianceMgr.API.getEventTeams("2015calb", new Callback<List<Team>>() {
            @Override
            public void success(List<Team> teams, Response response) {
                if (isAdded()) {
                    Collections.sort(teams, new Comparator<Team>() {
                        @Override
                        public int compare(Team lhs, Team rhs) {
                            return ((Integer) lhs.getTeamNumber()).compareTo(rhs.getTeamNumber());
                        }
                    });
                    setListAdapter(new TeamListAdapter(getActivity(), teams));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("TeamList", error.getMessage());
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
