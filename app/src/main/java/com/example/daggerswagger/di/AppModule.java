package com.example.daggerswagger.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.daggerswagger.R;
import com.example.daggerswagger.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Modules are re-usable, interchangable classes that provide dependencies
@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit providesRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

    //we can get the application object, since it is bound to the AppComponent upon creation
    //and we can get the RequestOptions object since it is created in a module that is listed in the same Component (i.e. this one)
    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable provideAppDrawable(Application application){
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }

    /**
     //declares a dependency that can then be injected into AuthActivity
     //always use STATIC when declaring dependencies using @Provides
     //@Provides
     static String someString(){
     return "this is a test string";
     }

     //since the app was bound to the component at creation (using @BindsInstance),
     //the application object is available to this module
     @Provides
     static boolean getApp(Application application){
     return application == null;
     } **/
}
