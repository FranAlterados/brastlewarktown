package com.fduranortega.brastlewarktown.app;

import android.app.Application;

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

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
        REST_CLIENT = new RestClient();

        Realm.init(this);
        REALM = Realm.getDefaultInstance();
    }
}
