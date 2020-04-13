package com.example.daggerswagger.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.daggerswagger.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

    /** the above with @Binds is the same as:
    @Provides
    static ViewModelProvider.Factory bindFactory(ViewModelProviderFactory factory){
        return factory;
    }
    **/
}
