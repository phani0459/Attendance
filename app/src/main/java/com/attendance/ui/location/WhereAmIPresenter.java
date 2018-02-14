package com.attendance.ui.location;

import com.attendance.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by KANDAGATLAs on 15-02-2018.
 */

public class WhereAmIPresenter<V extends WhereAmIView> extends BasePresenter<V> implements WhereAmIMVPPresenter<V> {
    @Inject
    public WhereAmIPresenter() {

    }


    @Override
    public void getLocationPermission() {
        getMvpView().showPermissionDialog();
    }

    @Override
    public void updateLocationUI() {
        getMvpView().showCurrentLocation();
    }

    @Override
    public void getDeviceLocation() {
        getMvpView().getDeviceLocation();
    }
}
