package com.attendance.ui.attendance_list;

import com.attendance.ui.base.MvpPresenter;

public interface ListMvpPresenter<V extends ListMvpView> extends MvpPresenter<V> {

    void onViewPrepared();
}


