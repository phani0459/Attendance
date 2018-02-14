package com.attendance.ui.give_attendance;

import com.attendance.ui.base.BaseView;

/**
 * Created by KANDAGATLAs on 15-02-2018.
 */

public interface AttendanceView extends BaseView {

    void setTodayDate(String todayDate);

    void submitAttendance();

    void changeAttendance();
}
