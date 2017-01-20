package com.carlosapps.beastshopping.infrastructure;

import android.app.Application;

import com.squareup.otto.Bus;

public class BeastShoppingApplication extends Application {
    private Bus bus;

    public BeastShoppingApplication() {
        bus = new Bus();
    }


    @Override
    public void onCreate() {
        super.onCreate();

    }

    public Bus getBus() {
        return bus;
    }
}
