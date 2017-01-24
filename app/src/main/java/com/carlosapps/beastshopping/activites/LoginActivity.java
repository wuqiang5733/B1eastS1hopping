package com.carlosapps.beastshopping.activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.carlosapps.beastshopping.R;
import com.carlosapps.beastshopping.services.AccountServices;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.activity_login_linear_layout)
    LinearLayout linearLayout;

    @BindView(R.id.activity_login_registerButton)
    Button registerButton;

    @BindView(R.id.activity_login_loginButton)
    Button loginButton;

    @BindView(R.id.activity_login_userEmail)
    EditText userEmail;

    @BindView(R.id.activity_login_userPassword)
    EditText userPassword;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        linearLayout.setBackgroundResource(R.drawable.background_screen_two);
        FirebaseAuth.getInstance().signOut();  // 运行这个 Activity 必须是退出状态

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Loading....");
        mProgressDialog.setMessage("Attempting Log In");
        mProgressDialog.setCancelable(false);
    }

    @OnClick(R.id.activity_login_registerButton)
    public void setRegisterButton() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    @OnClick(R.id.activity_login_loginButton)
    public void setLoginButton(){
        // 接收程序在 LiveAccountServices 当中
        bus.post(new AccountServices.LogUserInRequest(userEmail.getText().toString(),
                userPassword.getText().toString(),mProgressDialog));
    }
    @Subscribe
    public void LogUserIn(AccountServices.LogUserInResponse response){
        if (!response.didSuceed()){
            userEmail.setError(response.getPropertyError("email"));
            userPassword.setError(response.getPropertyError("password"));
        }
    }

}
