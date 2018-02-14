package com.attendance.ui.launcher;

import com.attendance.di.PerActivity;
import com.attendance.ui.base.BasePresenter;
import com.attendance.ui.base.MvpPresenter;

/**
 * Created by KANDAGATLAs on 14-02-2018.
 */

@PerActivity
public interface LauncherMVPPresenter<V extends LauncherView> extends MvpPresenter<V> {
    void onShowAttendanceClick();

    void onGiveAttendanceClick();

    void onWhereAmIClick();
}
