package com.example.firebasecrudmvp.root;

import android.app.Application;

import com.example.firebasecrudmvp.http.ApiModule;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationmodule(new Applicationmodule(this))
                .apiModule(new ApiModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }


}
