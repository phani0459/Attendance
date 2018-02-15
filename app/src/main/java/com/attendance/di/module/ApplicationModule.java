package com.attendance.di.module;

import android.app.Application;
import android.content.Context;

import com.attendance.BuildConfig;
import com.attendance.database.LocalCacheManager;
import com.attendance.di.ApplicationContext;
import com.attendance.di.DatabaseInfo;
import com.attendance.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return Constants.DB_NAME;
    }

}
