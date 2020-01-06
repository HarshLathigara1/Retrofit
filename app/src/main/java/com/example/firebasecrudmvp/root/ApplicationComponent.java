package com.example.firebasecrudmvp.root;


import com.example.firebasecrudmvp.MainActivity;
import com.example.firebasecrudmvp.http.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {Applicationmodule.class, ApiModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);
}
