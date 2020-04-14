package com.example.daggerswagger.ui.auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.example.daggerswagger.R;
import com.example.daggerswagger.ui.main.MainActivity;
import com.example.daggerswagger.ui.User;
import com.example.daggerswagger.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

//extend activities that depend on Dagger by DaggerAppCompatActivity
public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener{

    private static final String TAG = "AuthActivity";

    private EditText userId;
    private ProgressBar progressBar;

    private AuthViewModel viewModel;
    @Inject
    ViewModelProviderFactory providerFactory;
    //as long as the return type is the same as @Provides, the name doesn't matter
    @Inject
    Drawable logo;

    @Inject
    RequestManager glideInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        userId = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.login_button).setOnClickListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        setLogo();

        subscribeObservers();
    }

    private void subscribeObservers(){
        viewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch(userAuthResource.status){
                        case LOADING: {
                            showProgressBar(true);
                            break;
                        }
                        case AUTHENTICATED: {
                            showProgressBar(false);
                            Log.d(TAG, "onChanged LOGIN SUCCESS" + userAuthResource.data.getUsername());
                            onLoginSuccess();
                            break;
                        }
                        case ERROR: {
                            showProgressBar(false);
                            Log.d(TAG, "onChanged LOGIN ERROR" + userAuthResource.message);

                            break;
                        }
                        case NOT_AUTHENTICATED: {
                            showProgressBar(false);
                            Log.d(TAG, "onChanged LOGIN FAILURE");

                            break;
                        }

                    }
                }
            }
        });
    }

    private void onLoginSuccess(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProgressBar(boolean isVisible){
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
    private void setLogo(){
        glideInstance
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.login_button:{
                attemptLogin();
                break;
            }
        }
    }

    private void attemptLogin(){
        if(TextUtils.isEmpty(userId.getText().toString())){
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
    }
}
