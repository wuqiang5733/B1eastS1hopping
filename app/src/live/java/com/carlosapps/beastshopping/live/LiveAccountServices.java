package com.carlosapps.beastshopping.live;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.carlosapps.beastshopping.infrastructure.BeastShoppingApplication;
import com.carlosapps.beastshopping.infrastructure.Utils;
import com.carlosapps.beastshopping.services.AccountServices;
import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.squareup.otto.Subscribe;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;

public class LiveAccountServices extends BaseLiveService {
    public LiveAccountServices(BeastShoppingApplication application) {
        super(application);
    }

    @Subscribe
    public void RegisterUser(final AccountServices.RegisterUserRequest request) {
        AccountServices.RegisteruserResponse response = new AccountServices.RegisteruserResponse();

        if (request.userEmail.isEmpty()) {
            response.setPropertyErrors("email", "Please put in your email.");
        }

        if (request.userName.isEmpty()) {
            response.setPropertyErrors("userName", "Please pur in your name.");
        }

        if (response.didSuceed()) {
//            Toast.makeText(application.getApplicationContext(),"User will be registered shortly",Toast.LENGTH_LONG).show();
            request.progressDialog.show();

            SecureRandom random = new SecureRandom();
            final String randomPassword = new BigInteger(32, random).toString();

            auth.createUserWithEmailAndPassword(request.userEmail, randomPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {  // 如果不成功
                                request.progressDialog.dismiss();
                                Toast.makeText(application.getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            } else {  // 如果成功了的话
                                auth.sendPasswordResetEmail(request.userEmail)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (!task.isSuccessful()) {  // 如果不成功
                                                    request.progressDialog.dismiss();
                                                    Toast.makeText(application.getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                } else {   // 如果成功了的话
                                                    Firebase reference = new Firebase(Utils.FIRE_BASE_USER_REFERENCE + Utils.encodeEmail(request.userEmail));

                                                    HashMap<String, Object> timeJoined = new HashMap<>();
                                                    timeJoined.put("dateJoined", ServerValue.TIMESTAMP);
                                                    // 注意与 entities 下的 User 对应
                                                    reference.child("email").setValue(request.userEmail);
                                                    reference.child("name").setValue(request.userName);
                                                    reference.child("hasLoggedInWithPassword").setValue(false);
                                                    reference.child("timeJoined").setValue(timeJoined);

                                                    Toast.makeText(application.getApplicationContext(), "Please Check Your Email", Toast.LENGTH_LONG)
                                                            .show();

                                                    request.progressDialog.dismiss();

                                                }
                                            }
                                        });
                            } // else
                        }
                    });

        }
        // 对应的处理方法在 RegisterActivity 当中
        bus.post(response);

    }


}
