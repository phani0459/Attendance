package com.attendance.utils;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by KANDAGATLAs on 14-02-2018.
 */

public class Constants {

    //17.441325, 78.381354
    public static final LatLng BUILDING_9_LAT_LNG = new LatLng(17.441110, 78.380463);
    // Keys for storing activity state.
    public static final String KEY_CAMERA_POSITION = "camera_position";
    public static final String KEY_LOCATION = "location";

    public static final String DB_NAME = "attendance_mgmt.db";
    public static final String TIMESTAMP_FORMAT = "dd-MM-yyyy";
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    public static final String USER_TABLE_NAME = "userTable";
}
