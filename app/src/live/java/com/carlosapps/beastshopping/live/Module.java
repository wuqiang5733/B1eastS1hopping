package com.carlosapps.beastshopping.live;

import com.carlosapps.beastshopping.infrastructure.BeastShoppingApplication;

public class Module {

    public static void Register(BeastShoppingApplication application){
        // BeastShoppingApplication's onCreate
        new LiveAccountServices(application);
//        new LiveShoppingListService(application);
//        new LiveItemService(application);
//        new LiveUsersService(application);
    }
}
