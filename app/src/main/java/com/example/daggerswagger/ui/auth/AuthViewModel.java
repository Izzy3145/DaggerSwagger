package com.example.daggerswagger.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.daggerswagger.di.network.auth.AuthApi;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel is working...");

        if(authApi == null) {
            Log.d(TAG, "AuthApi is NULL");
        } else {
            Log.d(TAG, "AuthApi is not NULL");
        }
    }
}
