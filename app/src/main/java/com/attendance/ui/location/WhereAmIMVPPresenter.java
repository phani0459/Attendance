package com.attendance.ui.location;

import com.attendance.ui.base.MvpPresenter;

/**
 * Created by KANDAGATLAs on 15-02-2018.
 */

public interface WhereAmIMVPPresenter<V extends WhereAmIView> extends MvpPresenter<V> {

    void getLocationPermission();

    void updateLocationUI();

    void getDeviceLocation();
}
