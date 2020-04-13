package com.example.daggerswagger.di.auth;

import androidx.lifecycle.ViewModel;

import com.example.daggerswagger.di.ViewModelKey;
import com.example.daggerswagger.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);

}
