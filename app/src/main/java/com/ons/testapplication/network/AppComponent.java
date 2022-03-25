package com.ons.testapplication.network;

import com.ons.testapplication.MainActivity;
import com.ons.testapplication.utils.UtilsModule;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(MainActivity mainActivity);

}
