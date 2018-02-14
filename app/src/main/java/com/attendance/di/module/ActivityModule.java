package com.attendance.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.attendance.di.ActivityContext;
import com.attendance.di.PerActivity;
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

   /* @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    AboutMvpPresenter<AboutMvpView> provideAboutPresenter(
            AboutPresenter<AboutMvpView> presenter) {
        return presenter;
    }*/

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

   /* @Provides
    @Singleton
    GoogleApiClient providesGoogleApiClient(Context context) {
        return new GoogleApiClient.Builder(context)
                .addApi(Places.GEO_DATA_API)
                .addApi(LocationServices.API)
                .build();
    }*/

    /*@Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    RatingDialogMvpPresenter<RatingDialogMvpView> provideRateUsPresenter(
            RatingDialogPresenter<RatingDialogMvpView> presenter) {
        return presenter;
    }

    @Provides
    FeedMvpPresenter<FeedMvpView> provideFeedPresenter(
            FeedPresenter<FeedMvpView> presenter) {
        return presenter;
    }

    @Provides
    OpenSourceMvpPresenter<OpenSourceMvpView> provideOpenSourcePresenter(
            OpenSourcePresenter<OpenSourceMvpView> presenter) {
        return presenter;
    }

    @Provides
    BlogMvpPresenter<BlogMvpView> provideBlogMvpPresenter(
            BlogPresenter<BlogMvpView> presenter) {
        return presenter;
    }

    @Provides
    FeedPagerAdapter provideFeedPagerAdapter(AppCompatActivity activity) {
        return new FeedPagerAdapter(activity.getSupportFragmentManager());
    }*/

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
