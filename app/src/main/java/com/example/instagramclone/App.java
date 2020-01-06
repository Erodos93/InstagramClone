package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("BdeP8C9o8QQM79Uq5luyGtXxrTh5iyN2spwlKnKZ")
                .clientKey("3m190xhjAAsK9W3fOyFpXD4CnTuyK1Kw7wAeNLdK")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
