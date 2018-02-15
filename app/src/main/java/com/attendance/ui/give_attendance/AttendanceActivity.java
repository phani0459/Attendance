package com.attendance.ui.give_attendance;


import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.attendance.R;
import com.attendance.ui.base.BaseActivity;
import com.attendance.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AttendanceActivity extends BaseActivity implements AttendanceView {

    @BindView(R.id.btn_attendance)
    public Button attendanceButton;

    @BindView(R.id.btn_submit)
    public Button submitButton;

    @BindView(R.id.tv_todays_date)
    public TextView dateTextView;

    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    // The entry point to the Fused Location Provider.
    @Inject
    AttendanceMVPPresenter<AttendanceView> mPresenter;
    private boolean attendanceGiven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        try {
            getSupportActionBar().setTitle(getString(R.string.app_name));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(AttendanceActivity.this);
        mPresenter.onGetTodaysDate();

        mPresenter.getLocationPermission();

    }


    @OnClick(R.id.btn_submit)
    public void submit(View view) {
        if (attendanceGiven) {
            showMessage("Toady's Attendance has already been submitted");
        } else {
            mPresenter.onSubmitAttendanceClick(dateTextView.getText().toString(), attendanceButton.getText().toString().equalsIgnoreCase("Present") ? 1 : 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showPermissionDialog(boolean isGranted) {
        if (isGranted) {
            mPresenter.onGetCurrentLocation();
        } else {
            requestPermissionsSafely(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void isTodayAttendanceGiven(boolean disable) {
        attendanceGiven = disable;
    }

    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPresenter.onGetCurrentLocation();
                }
            }
        }
    }

    @Override
    public void setTodayDate(String todayDate) {
        dateTextView.setText(todayDate);
        mPresenter.checkTodaysAttendance(todayDate);
    }


    @Override
    public void submitAttendance() {
        showMessage("Attendance Added");
    }


    public boolean checkDistance(Location currentPosition) {
        Location buildingLocation = new Location(LocationManager.GPS_PROVIDER);
        buildingLocation.setLatitude(Constants.BUILDING_9_LAT_LNG.latitude);
        buildingLocation.setLongitude(Constants.BUILDING_9_LAT_LNG.longitude);

        float dist = buildingLocation.distanceTo(currentPosition);
        return (dist < 10);
    }

    @Override
    public void changeAttendance(Location mLastKnownLocation) {
        if (mLastKnownLocation != null) {
            if (checkDistance(mLastKnownLocation)) {
                attendanceButton.setBackground(getResources().getDrawable(R.drawable.attendance_btn_bg));
                attendanceButton.setText("PRESENT");
            } else {
                attendanceButton.setBackground(getResources().getDrawable(R.drawable.attendance_btn_grey_bg));
                attendanceButton.setText("ABSENT");
            }
        } else {
            showMessage("Your Current Location cannot be fetched.");
        }
    }

}