package com.attendance.database;


import android.arch.persistence.room.Room;

import android.content.Context;


import java.util.List;


import io.reactivex.Completable;

import io.reactivex.CompletableObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.Disposable;

import io.reactivex.functions.Action;

import io.reactivex.functions.Consumer;

import io.reactivex.schedulers.Schedulers;


public class LocalCacheManager {
    private static final String DB_NAME = "database-name";
    private Context context;
    private static LocalCacheManager _instance;
    private AttendanceDB db;

    public static LocalCacheManager getInstance(Context context) {
        if (_instance == null) {
            _instance = new LocalCacheManager(context);
        }
        return _instance;
    }

    public LocalCacheManager(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, AttendanceDB.class, DB_NAME).build();
    }

    public void getUserByDate(final DatabaseCallBack databaseCallback, final String date) {
        db.userDao().getUserByDate(date).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<User>() {

            @Override
            public void accept(@io.reactivex.annotations.NonNull User user) throws Exception {
                databaseCallback.singleUser(user);
            }

        });
    }

    public void getUsers(final DatabaseCallBack databaseCallback) {
        db.userDao().getAllUsers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<User>>() {

            @Override

            public void accept(@io.reactivex.annotations.NonNull List<User> users) throws Exception {
                databaseCallback.onUsersLoaded(users);
            }

        });
    }

    public void addUser(final DatabaseCallBack databaseCallback, final String date, final int isPresent) {
        Completable.fromAction(new Action() {

            @Override

            public void run() throws Exception {
                User user = new User(date, isPresent);
                db.userDao().insert(user);
            }

        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                databaseCallback.onUserAdded();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }

}