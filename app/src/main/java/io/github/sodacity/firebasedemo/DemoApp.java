package io.github.sodacity.firebasedemo;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Project: GDGSodacityFirebaseDemo
 * Package: io.github.sodacity.firebasedemo
 * Created by drew.heavner on 11/16/16.
 */

public class DemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
