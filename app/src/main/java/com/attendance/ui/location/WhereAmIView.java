package com.attendance.ui.location;

import com.attendance.ui.base.BaseView;

/**
 * Created by KANDAGATLAs on 15-02-2018.
 */

public interface WhereAmIView extends BaseView {

    void showPermissionDialog(boolean show);
    void showCurrentLocation();

    void getDeviceLocation();
}
