package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.parse.LogInCallback;
import com.parse.ParseException;


import com.parse.ParseUser;

import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private Button btnLogin, btnSignUp;
    private EditText edtUserLogin, edtPasswordLogin, edtUserSignUp, edtPasswordSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtUserSignUp = (EditText) findViewById(R.id.edtUserNameSignup);
        edtPasswordSignUp = (EditText) findViewById(R.id.edtPasswordSignup);
        edtUserLogin = (EditText) findViewById(R.id.edtUserNameLogin);
        edtPasswordLogin = (EditText) findViewById(R.id.edtPasswordLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Intent intent = new Intent(SignUpLoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                            FancyToast.makeText(SignUpLoginActivity.this, "Sign Up is Success", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true);
                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this, "Sign up is false.Error is " + e.getMessage(), FancyToast.ERROR, Toast.LENGTH_SHORT, true).show();

                        }
                    }
                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtUserLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            Intent intent = new Intent(SignUpLoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                            FancyToast.makeText(SignUpLoginActivity.this, "Logged in is success.", FancyToast.SUCCESS, Toast.LENGTH_SHORT, true).show();

                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this, "Logged in is false.Error is " + e.getMessage(), FancyToast.ERROR, Toast.LENGTH_SHORT, true).show();

                        }
                    }
                });


            }
        });
    }

}

