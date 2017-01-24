package com.carlosapps.beastshopping.activites;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.carlosapps.beastshopping.infrastructure.BeastShoppingApplication;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.otto.Bus;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BaseActivity extends AppCompatActivity {
    protected BeastShoppingApplication application;
    protected Bus bus;
    protected FirebaseAuth.AuthStateListener authStateListener;
    protected FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (BeastShoppingApplication) getApplication();
        bus = application.getBus();
        bus.register(this);
//        printKeyHash();
        auth = FirebaseAuth.getInstance();

        if (!((this instanceof LoginActivity) || (this instanceof RegisterActivity) || (this instanceof SplashScreenActivity))) {
            authStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null) { // 如果用户没有登陆
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                }
            };
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!((this instanceof LoginActivity) || (this instanceof RegisterActivity) || (this instanceof SplashScreenActivity))) {
            auth.addAuthStateListener(authStateListener);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
        if (!((this instanceof LoginActivity) || (this instanceof RegisterActivity) || (this instanceof SplashScreenActivity))) {
            auth.removeAuthStateListener(authStateListener);
        }
    }

    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("KeyHash:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("jk", "Exception(NameNotFoundException) : " + e);
        } catch (NoSuchAlgorithmException e) {
            Log.e("mkm", "Exception(NoSuchAlgorithmException) : " + e);
        }
    }
}
