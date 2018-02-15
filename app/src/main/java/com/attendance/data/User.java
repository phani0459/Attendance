package com.attendance.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by KANDAGATLAs on 15-02-2018.
 */

public class User {

    @NonNull
    private final String mId;

    private final boolean isPresent;

    @Nullable
    private final String mDate;

    public User(@Nullable String id, @Nullable String mDate, boolean isPresent) {
        mId = id;
        mDate = mDate;
        isPresent = isPresent;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @Nullable
    public String getDate() {
        return mDate;
    }

    public boolean isCompleted() {
        return isPresent;
    }

    @Override
    public String toString() {
        return "User with date " + mDate;
    }
}
