package com.attendance.database;

import android.arch.persistence.room.Database;

import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AttendanceDB extends RoomDatabase {
    public abstract UserDao userDao();
}

 