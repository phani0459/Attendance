package com.attendance.ui.give_attendance;

import com.attendance.ui.base.BasePresenter;
import com.attendance.ui.launcher.LauncherMVPPresenter;
import com.attendance.ui.launcher.LauncherView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

/**
 * Created by KANDAGATLAs on 15-02-2018.
 */

public class AttendancePresenter<V extends AttendanceView> extends BasePresenter<V> implements AttendanceMVPPresenter<V> {

    @Inject
    public AttendancePresenter() {

    }

    @Override
    public void onSubmitAttendanceClick() {
        //TODO: submit attendance in database
        getMvpView().submitAttendance();
    }

    @Override
    public void onGetTodaysDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);
        getMvpView().setTodayDate(formattedDate);
    }

    @Override
    public void onGetCurrentLocation() {
        getMvpView().changeAttendance();
    }
}
