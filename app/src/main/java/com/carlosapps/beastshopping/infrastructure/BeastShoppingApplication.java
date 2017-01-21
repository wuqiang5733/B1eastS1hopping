package com.carlosapps.beastshopping.infrastructure;

import android.app.Application;

import com.carlosapps.beastshopping.live.Module;
import com.firebase.client.Firebase;
import com.squareup.otto.Bus;

public class BeastShoppingApplication extends Application {
    private Bus bus;

    public BeastShoppingApplication() {
        bus = new Bus();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Module.Register(this);
    }

    public Bus getBus() {
        return bus;
    }
}
