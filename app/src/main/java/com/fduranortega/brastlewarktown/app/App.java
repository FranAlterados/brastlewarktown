package com.fduranortega.brastlewarktown.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.fduranortega.brastlewarktown.rest.RestClient;

import io.realm.Realm;

/**
 * Created by FranAlterados on 6/9/17.
 */
public class App extends Application {

    public static App INSTANCE;
    private static RestClient REST_CLIENT;
    private static Realm REALM;

    public static RestClient getRestClient() {
        return REST_CLIENT;
    }

    public static Realm getRealm() {
        return REALM;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
        REST_CLIENT = new RestClient();

        Realm.init(this);
        REALM = Realm.getDefaultInstance();
    }
}
