package com.example.firebasecrudmvp.root;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class Applicationmodule {
    private Application application;

    public Applicationmodule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    public Context providesContext(){

        return application;
        }
}
