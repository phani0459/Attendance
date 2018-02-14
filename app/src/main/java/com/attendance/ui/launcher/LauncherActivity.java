package com.attendance.ui.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.attendance.R;
import com.attendance.ui.give_attendance.AttendanceActivity;
import com.attendance.ui.location.WhereAmIActivity;
import com.attendance.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LauncherActivity extends BaseActivity implements LauncherView{

    @Inject
    LauncherMVPPresenter<LauncherView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(LauncherActivity.this);
    }

    @OnClick(R.id.btn_where_am_i)
    public void whereAmI(View view) {
        mPresenter.onWhereAmIClick();
    }

    @OnClick(R.id.btn_give_attendance)
    public void giveAttendance(View view) {
        mPresenter.onGiveAttendanceClick();
    }

    @OnClick(R.id.btn_show_attendance)
    public void showAttendance(View view) {
        mPresenter.onShowAttendanceClick();
    }

    @Override
    public void openGiveAttendance() {
        Intent intent = new Intent(this, AttendanceActivity.class);
        startActivity(intent);
    }

    @Override
    public void openshowAttendance() {
/*Intent intent = new Intent(LauncherActivity.class);
        startActivity(intent);*/
    }

    @Override
    public void openWhereAmI() {
        Intent intent = new Intent(this, WhereAmIActivity.class);
        startActivity(intent);
    }
}
