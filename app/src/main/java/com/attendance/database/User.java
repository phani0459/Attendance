package com.attendance.database;

import android.arch.persistence.room.ColumnInfo;

import android.arch.persistence.room.Entity;

import android.arch.persistence.room.PrimaryKey;

 

import com.attendance.utils.Constants;

@Entity(tableName = Constants.USER_TABLE_NAME)
public class User {
    @PrimaryKey(autoGenerate = true)

    private int id;

 

    @ColumnInfo(name="date")

    private String date;

 

    // 1 if present

    //0 if absent

    @ColumnInfo(name = "isPresent")

    private int isPresent;

 

    public User(String date, int isPresent) {

        this.date = date;

        this.isPresent = isPresent;

    }

 

    public int getId() {

        return id;

    }

 

    public void setId(int id) {

        this.id = id;

    }

 

    public String getDate() {

        return date;

    }

 

    public void setDate(String date) {

        this.date = date;

    }

 

    public int getIsPresent() {

        return isPresent;

    }

 

    public void setIsPresent(int isPresent) {

        this.isPresent = isPresent;

    }

}