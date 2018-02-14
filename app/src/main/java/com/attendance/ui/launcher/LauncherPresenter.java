package com.attendance.ui.launcher;

import com.attendance.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by KANDAGATLAs on 14-02-2018.
 */

public class LauncherPresenter<V extends LauncherView> extends BasePresenter<V> implements LauncherMVPPresenter<V>{


    @Inject
    public LauncherPresenter() {

    }

    @Override
    public void onShowAttendanceClick() {
        getMvpView().openshowAttendance();
    }

    @Override
    public void onGiveAttendanceClick() {
        getMvpView().openGiveAttendance();
    }

    @Override
    public void onWhereAmIClick() {
        getMvpView().openWhereAmI();
    }
}
