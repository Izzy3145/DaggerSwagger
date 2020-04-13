package com.example.daggerswagger.di;

import com.example.daggerswagger.di.auth.AuthModule;
import com.example.daggerswagger.di.auth.AuthViewModelsModule;
import com.example.daggerswagger.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    //this is all that is needed to declare AuthActivity as a potential client
    //no annotations needed in the AuthActivity
    //when using @ContributesAndroidInjector, method and class must be abstract
    @ContributesAndroidInjector(
            //adding modules here, to an activity module means the module is scoped to the life of that activity
            //only the AuthActivity sub-component can use the AuthViewModelsModule
            modules = {AuthViewModelsModule.class,
                    AuthModule.class}
    )
    public abstract AuthActivity contributeAuthActivity();


}