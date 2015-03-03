package io.vinnie.friarscout.api;

import android.content.Context;
import android.util.Log;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by vmagro on 3/3/15.
 */
public class TheBlueAllianceMgr {

    public static final String TAG = "TheBlueAllianceAPIMgr";
    public static TheBlueAlliance API;
    public static Cache responseCache;
    private static OkHttpClient ok;

    public static void init(Context c) {
        ok = new OkHttpClient();
        try {
            responseCache = new Cache(c.getCacheDir(), 256000000);
            ok.setCache(responseCache);
        } catch (Exception e) {
            Log.d(TAG, "Unable to set http cache");
        }
        ok.setReadTimeout(30, TimeUnit.SECONDS);
        ok.setConnectTimeout(30, TimeUnit.SECONDS);

        RequestInterceptor appIdInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("X-TBA-App-Id", "frc3309:friarscout:1");
            }
        };

        API = new RestAdapter.Builder()
                .setEndpoint("http://www.thebluealliance.com/api/v2/")
                .setClient(new OkClient(ok))
                .setRequestInterceptor(appIdInterceptor)
                .build()
                .create(TheBlueAlliance.class);
    }

    public static OkHttpClient getClient() {
        return ok;
    }

}
