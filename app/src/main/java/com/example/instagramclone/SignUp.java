package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.parse.ParseException;

import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUserName, edtPassword, edtEmail;
    private Button btnLogin, btnSignUp;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Sign Up");
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtUserName = (EditText) findViewById(R.id.edtLoginEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        btnLogin = (Button) findViewById(R.id.btnLogIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUpLoginActivity);
        btnLogin.setOnClickListener(SignUp.this);
        btnSignUp.setOnClickListener(SignUp.this);
        if(ParseUser.getCurrentUser() != null){
            translationToSocialMediaActivity();
        }
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnSignUp);
                }
                return false;
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignUpLoginActivity:
                final ParseUser signUser = new ParseUser();
                signUser.setEmail(edtEmail.getText().toString());
                signUser.setUsername(edtUserName.getText().toString());
                signUser.setPassword(edtPassword.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
                progressDialog.setMessage("Signing up" + edtUserName.getText().toString());
                progressDialog.show();
                if (edtUserName.getText().equals("") ||
                        edtPassword.getText().equals("") ||
                        edtEmail.getText().equals("")) {
                    FancyToast.makeText(SignUp.this,
                            "User name,Password,Email is needed",
                            Toast.LENGTH_LONG, FancyToast.INFO, true).show();


                } else {
                    signUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this, signUser.getUsername() + " is sign success.", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                translationToSocialMediaActivity();
                            } else {
                                FancyToast.makeText(SignUp.this, " There was an error " + e.getMessage(), Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                            }

                            progressDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.btnLogIn:
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
                break;
        }

    }
    public void rootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.getStackTrace();
        }

    }
    public void translationToSocialMediaActivity(){

        Intent intent=new Intent(SignUp.this,SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}
