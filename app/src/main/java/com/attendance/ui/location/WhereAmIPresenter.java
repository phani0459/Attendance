package com.attendance.ui.location;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.attendance.di.ApplicationContext;
import com.attendance.ui.base.BasePresenter;

import javax.inject.Inject;

import static com.attendance.utils.Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;

/**
 * Created by KANDAGATLAs on 15-02-2018.
 */

public class WhereAmIPresenter<V extends WhereAmIView> extends BasePresenter<V> implements WhereAmIMVPPresenter<V> {
    private final Context context;

    @Inject
    public WhereAmIPresenter(@ApplicationContext Context context) {
        this.context = context;
    }


    @Override
    public void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getMvpView().showPermissionDialog(true);
        } else {
            getMvpView().showPermissionDialog(false);
        }
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
