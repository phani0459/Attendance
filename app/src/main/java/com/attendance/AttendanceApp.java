package com.attendance;

import android.app.Application;

import com.attendance.di.component.ApplicationComponent;
import com.attendance.di.component.DaggerApplicationComponent;
import com.attendance.di.module.ApplicationModule;

/**
 * Created by KANDAGATLAs on 14-02-2018.
 */

public class AttendanceApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

}
