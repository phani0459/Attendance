package com.attendance.di.component;

import android.app.Application;
import android.content.Context;

import com.attendance.AttendanceApp;
import com.attendance.di.ApplicationContext;
import com.attendance.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    void inject(AttendanceApp app);

    Application application();

//    DataManager getDataManager();
}