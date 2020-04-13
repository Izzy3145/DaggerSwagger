package com.example.daggerswagger.di;

import com.example.daggerswagger.AuthActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    //this is all that is needed to declare AuthActivity as a potential client
    //no annotations needed in the AuthActivity
    //when using @ContributesAndroidInjector, method and class must be abstract
    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();


}