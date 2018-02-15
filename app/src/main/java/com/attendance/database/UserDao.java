package com.attendance.database;


import android.arch.persistence.room.Dao;

import android.arch.persistence.room.Insert;

import android.arch.persistence.room.Query;


import com.attendance.utils.Constants;


import java.util.List;


import io.reactivex.Flowable;

import io.reactivex.Maybe;


@Dao

public interface UserDao {

    @Query("SELECT * FROM " + Constants.USER_TABLE_NAME)
    Maybe<List<User>> getAllUsers();

    @Query("SELECT * FROM " + Constants.USER_TABLE_NAME + " WHERE date IN (:dates)")
    Flowable<List<User>> loadByDates(String[] dates);

    @Query("SELECT * FROM " + Constants.USER_TABLE_NAME + " WHERE date IN (:date)")
    Flowable<User> getUserByDate(String date);

    @Insert
    void insert(User user);

}