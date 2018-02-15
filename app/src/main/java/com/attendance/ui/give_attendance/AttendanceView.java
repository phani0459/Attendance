package com.attendance.ui.give_attendance;

import android.location.Location;

import com.attendance.ui.base.BaseView;

/**
 * Created by KANDAGATLAs on 15-02-2018.
 */

public interface AttendanceView extends BaseView {

    void setTodayDate(String todayDate);

    void submitAttendance();

    void changeAttendance(Location mLastKnownLocation);

    void showPermissionDialog(boolean show);

    void showLoading();

    void hideLoading();

    void isTodayAttendanceGiven(boolean enable);
}
