package com.example.daggerswagger.di;

import com.example.daggerswagger.di.auth.AuthModule;
import com.example.daggerswagger.di.auth.AuthViewModelsModule;
import com.example.daggerswagger.di.main.MainFragmentBuildersModule;
import com.example.daggerswagger.di.main.MainModule;
import com.example.daggerswagger.di.main.MainViewModelsModule;
import com.example.daggerswagger.ui.main.MainActivity;
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
            //only the AuthActivity sub-component can use these modules
            modules = {AuthViewModelsModule.class,
                    AuthModule.class}
                    //we can inject attributes from AuthModule all over AuthViewModelsModule and AuthActivity
    )
    public abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class,
                    MainViewModelsModule.class,
                    MainModule.class}
    )
    abstract MainActivity contributeMainActivity();
}