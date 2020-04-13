package com.example.daggerswagger.di;

import android.app.Application;

import com.example.daggerswagger.BaseApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(
        //here are all the modules that you'll need to do what I want you to do
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class
        })

public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{

        //@BindsInstance can be used when we want to bind an instance of an object to
        //the component at the time of construction
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
