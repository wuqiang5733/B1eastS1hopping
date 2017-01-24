package com.carlosapps.beastshopping.services;

import android.app.ProgressDialog;

import com.carlosapps.beastshopping.infrastructure.ServiceResponse;
import com.facebook.AccessToken;

public class AccountServices {
    private AccountServices() {
    }

    public static class RegisterUserRequest{
        public String userName;
        public String userEmail;
        public ProgressDialog progressDialog;

        public RegisterUserRequest(String userName, String userEmail, ProgressDialog progressDialog) {
            this.userName = userName;
            this.userEmail = userEmail;
            this.progressDialog = progressDialog;
        }
    }

    public static class RegisteruserResponse extends ServiceResponse{

    }
/*********************************************************************************************************/

public static class LogUserInRequest{
    public String userEmail;
    public String userPassword;
    public ProgressDialog progressDialog;
//    public SharedPreferences sharedPreferences;

    public LogUserInRequest(String userEmail, String userPassword, ProgressDialog progressDialog) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.progressDialog = progressDialog;
//        this.sharedPreferences = sharedPreferences;
    }
}

    public static class LogUserInResponse extends ServiceResponse{

    }

    /*********************************************************************************************************/

    public static class LogUserInFacebookRequest{
        public AccessToken accessToken;
        public ProgressDialog progressDialog;
        public String userName;
        public String userEmail;
//        public SharedPreferences sharedPreferences;


        public LogUserInFacebookRequest(AccessToken accessToken, ProgressDialog progressDialog, String userName, String userEmail) {
            this.accessToken = accessToken;
            this.progressDialog = progressDialog;
            this.userName = userName;
            this.userEmail = userEmail;
//            this.sharedPreferences = sharedPreferences;
        }
    }
    /*********************************************************************************************************/

}
