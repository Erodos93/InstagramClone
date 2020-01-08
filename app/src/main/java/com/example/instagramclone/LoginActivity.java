package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.service.autofill.UserData;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnLogin, btnSignUp;
    private EditText edtLoginEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btnLoginLoginActivity);
        btnSignUp = (Button) findViewById(R.id.btnSignUpLoginActivity);
        edtLoginEmail = (EditText) findViewById(R.id.edtLoginEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSignUp.setOnClickListener(LoginActivity.this);
        btnLogin.setOnClickListener(LoginActivity.this);
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnLogin);
                }
                return false;
            }
        });
        if (ParseUser.getCurrentUser() != null) {
//            ParseUser.getCurrentUser().logOut();
            translationToSocialMediaActivity();
        }

    }


    @Override
    public void onClick(View v) {

        if (edtLoginEmail.getText().equals("") || edtPassword.getText().equals("")) {
            FancyToast.makeText(LoginActivity.this, "User name,Password,Email is needed", Toast.LENGTH_LONG, FancyToast.INFO, true).show();

        } else {


            switch (v.getId()) {
                case R.id.btnLoginLoginActivity:
                    ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Logged in");
                    progressDialog.show();
                    ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {

                                FancyToast.makeText(LoginActivity.this, ParseUser.getCurrentUser().getUsername() + " is logged  ssuccess.", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                translationToSocialMediaActivity();
                            } else {
                                FancyToast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                            }
                        }
                    });
                    progressDialog.dismiss();
                    break;
                case R.id.btnSignUpLoginActivity:
                    finish();
            }

        }
    }

    public void loginLayoutTapped(View view) {

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public void translationToSocialMediaActivity() {

        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}
