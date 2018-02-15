package com.attendance.ui.give_attendance;

import com.attendance.ui.base.MvpPresenter;

/**
 * Created by KANDAGATLAs on 15-02-2018.
 */

public interface AttendanceMVPPresenter<V extends AttendanceView> extends MvpPresenter<V> {

    void onSubmitAttendanceClick(String date, int isPresent);

    void onGetTodaysDate();

    void getLocationPermission();

    void onGetCurrentLocation();

    void checkTodaysAttendance(String date);
}
