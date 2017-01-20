package com.carlosapps.beastshopping.dialog;

import android.app.DialogFragment;
import android.os.Bundle;

import com.carlosapps.beastshopping.infrastructure.BeastShoppingApplication;
import com.squareup.otto.Bus;

public class BaseDialog extends DialogFragment {
    protected BeastShoppingApplication application;
    protected Bus bus;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (BeastShoppingApplication) getActivity().getApplication();
        bus  = application.getBus();
        bus.register(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}
