package com.carlosapps.beastshopping.live;

import android.widget.Toast;

import com.carlosapps.beastshopping.infrastructure.BeastShoppingApplication;
import com.carlosapps.beastshopping.services.AccountServices;
import com.squareup.otto.Subscribe;

public class LiveAccountServices extends BaseLiveService {
    public LiveAccountServices(BeastShoppingApplication application) {
        super(application);
    }
    @Subscribe
    public void RegisterUser(final AccountServices.RegisterUserRequest request){
        AccountServices.RegisteruserResponse response = new AccountServices.RegisteruserResponse();

        if (request.userEmail.isEmpty()){
            response.setPropertyErrors("email","Please put in your email.");
        }

        if (request.userName.isEmpty()){
            response.setPropertyErrors("userName","Please pur in your name.");
        }

        if (response.didSuceed()){
            Toast.makeText(application.getApplicationContext(),"User will be registered shortly",Toast.LENGTH_LONG).show();
        }
        // 对应的处理方法在 RegisterActivity 当中
        bus.post(response);
    }
}
