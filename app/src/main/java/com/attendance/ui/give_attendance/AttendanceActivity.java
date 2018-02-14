package com.attendance.ui.give_attendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.attendance.R;
import com.attendance.ui.base.BaseActivity;
import com.attendance.ui.launcher.LauncherActivity;
import com.attendance.ui.launcher.LauncherMVPPresenter;
import com.attendance.ui.launcher.LauncherView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AttendanceActivity extends BaseActivity implements AttendanceView {

    @BindView(R.id.btn_attendance)
    public Button attendanceButton;
    @BindView(R.id.tv_todays_date)
    public TextView dateTextView;

    @Inject
    AttendanceMVPPresenter<AttendanceView> mPresenter;

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
    }

    @OnClick(R.id.btn_submit)
    public void submit(View view) {
        mPresenter.onSubmitAttendanceClick();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTodayDate(String todayDate) {
        dateTextView.setText(todayDate);
    }

    @Override
    public void submitAttendance() {

    }

    @Override
    public void changeAttendance() {

    }
}
