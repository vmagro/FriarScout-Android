package io.vinnie.friarscout.api;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by vmagro on 3/3/15.
 */
public interface TheBlueAlliance {

    @GET("/event/{code}")
    public Event getEvent(@Path("code") String code);

    @GET("/event/{code}")
    public void getEvent(@Path("code") String code, Callback<Event> callback);

    @GET("/event/{code}/teams")
    public List<Team> getEventTeams(@Path("code") String code);

    @GET("/event/{code}/teams")
    public void getEventTeams(@Path("code") String code, Callback<List<Team>> callback);
}
