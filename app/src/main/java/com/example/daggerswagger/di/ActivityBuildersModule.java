package com.example.daggerswagger.di;

import com.example.daggerswagger.AuthActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    //this is all that is needed to declare AuthActivity as a potential client
    //no annotations needed in the AuthActivity
    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();

    //declares a dependency that can then be injected into AuthActivity
    @Provides
    static String someString(){
        return "this is a test string";
    }
}