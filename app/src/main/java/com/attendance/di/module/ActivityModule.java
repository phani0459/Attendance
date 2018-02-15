package com.attendance.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.attendance.database.User;
import com.attendance.di.ActivityContext;
import com.attendance.di.PerActivity;
import com.attendance.ui.attendance_list.ListAdapter;
import com.attendance.ui.attendance_list.ListMvpPresenter;
import com.attendance.ui.attendance_list.ListMvpView;
import com.attendance.ui.attendance_list.ListPresenter;
import com.attendance.ui.give_attendance.AttendanceMVPPresenter;
import com.attendance.ui.give_attendance.AttendancePresenter;
import com.attendance.ui.give_attendance.AttendanceView;
import com.attendance.ui.launcher.LauncherMVPPresenter;
import com.attendance.ui.launcher.LauncherPresenter;
import com.attendance.ui.launcher.LauncherView;
import com.attendance.ui.location.WhereAmIMVPPresenter;
import com.attendance.ui.location.WhereAmIPresenter;
import com.attendance.ui.location.WhereAmIView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    LauncherMVPPresenter<LauncherView> provideLauncherPresenter(LauncherPresenter<LauncherView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    WhereAmIMVPPresenter<WhereAmIView> provideWhereAmIPresenter(WhereAmIPresenter<WhereAmIView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AttendanceMVPPresenter<AttendanceView> provideAttendancePresenter(AttendancePresenter<AttendanceView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ListMvpPresenter<ListMvpView> provideListPresenter(ListPresenter<ListMvpView> presenter) {
        return presenter;
    }

    @Provides
    ListAdapter provideListAdapter(AppCompatActivity activity) {
        return new ListAdapter(new ArrayList<User>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
