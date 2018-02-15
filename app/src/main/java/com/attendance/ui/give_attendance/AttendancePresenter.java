package com.attendance.ui.give_attendance;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.attendance.database.DatabaseCallBack;
import com.attendance.database.LocalCacheManager;
import com.attendance.database.User;
import com.attendance.di.ActivityContext;
import com.attendance.di.ApplicationContext;
import com.attendance.ui.base.BasePresenter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by KANDAGATLAs on 15-02-2018.
 */

public class AttendancePresenter<V extends AttendanceView> extends BasePresenter<V> implements AttendanceMVPPresenter<V>, DatabaseCallBack {

    private final Context context;
    private final AppCompatActivity activity;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    @Inject
    public AttendancePresenter(@ActivityContext Context context, AppCompatActivity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onSubmitAttendanceClick(String date, int isPresent) {
        LocalCacheManager.getInstance(context).addUser(this, date, isPresent);
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
    public void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getMvpView().showPermissionDialog(true);
        } else {
            getMvpView().showPermissionDialog(false);
        }
    }

    @Override
    public void onGetCurrentLocation() {
        getMvpView().showLoading();
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

        try {
            Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(activity, new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful()) {
                        // Set the map's camera position to the current location of the device.
                        Location mLastKnownLocation = task.getResult();
                        getMvpView().changeAttendance(mLastKnownLocation);
                    } else {
                        Log.d("TAG", "Current location is null. Using defaults.");
                        Log.e("TAG", "Exception: %s", task.getException());
                        getMvpView().changeAttendance(null);
                    }

                    getMvpView().hideLoading();
                }
            });
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void checkTodaysAttendance(String todayDate) {
        LocalCacheManager.getInstance(context).getUserByDate(this, todayDate);
    }

    @Override
    public void onUsersLoaded(List<User> users) {

    }

    @Override
    public void onUserAdded() {

    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public void singleUser(User user) {
        getMvpView().isTodayAttendanceGiven(user != null);
    }
}
