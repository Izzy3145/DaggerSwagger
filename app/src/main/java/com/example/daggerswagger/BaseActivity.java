package com.example.daggerswagger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.daggerswagger.ui.User;
import com.example.daggerswagger.ui.auth.AuthActivity;
import com.example.daggerswagger.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();
    }

    private void subscribeObservers(){
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch(userAuthResource.status){
                        case LOADING: {
                            break;
                        }
                        case AUTHENTICATED: {
                            Log.d(TAG, "onChanged LOGIN SUCCESS" + userAuthResource.data.getUsername());
                            break;
                        }
                        case ERROR: {
                            Log.d(TAG, "onChanged LOGIN ERROR" + userAuthResource.message);

                            break;
                        }
                        case NOT_AUTHENTICATED: {
                            Log.d(TAG, "onChanged LOGIN FAILURE");
                            navLoginScreen();
                            break;
                        }

                    }
                }
            }
        });
    }

    private void navLoginScreen(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
