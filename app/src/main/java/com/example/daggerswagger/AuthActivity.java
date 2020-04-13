package com.example.daggerswagger;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

//extend activities that depend on Dagger by DaggerAppCompatActivity
public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "AuthActivity";

    //as long as the return type is the same as @Provides, the name doesn't matter
    @Inject
    Drawable logo;

    @Inject
    RequestManager glideInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        setLogo();
    }

    private void setLogo(){
        glideInstance
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }
}
