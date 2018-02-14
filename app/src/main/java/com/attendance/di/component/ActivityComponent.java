package com.attendance.di.component;

import com.attendance.di.PerActivity;
import com.attendance.di.module.ActivityModule;
import com.attendance.ui.give_attendance.AttendanceActivity;
import com.attendance.ui.location.WhereAmIActivity;
import com.attendance.ui.launcher.LauncherActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(LauncherActivity activity);

    void inject(AttendanceActivity activity);

    void inject(WhereAmIActivity activity);

}
