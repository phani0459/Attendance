package com.attendance.ui.attendance_list;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.attendance.R;
import com.attendance.database.User;
import com.attendance.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KANDAGATLAs on 15-02-2018.
 */

public class AttendanceListActivity extends BaseActivity implements ListMvpView{

    @Inject
    ListMvpPresenter<ListMvpView> mPresenter;

    @Inject
    ListAdapter mListAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.rvAttendanceList)
    RecyclerView mRecyclerView;
    @BindView(R.id.llAttendanceNotExist)
    View llAttendanceNotExist;
    @BindView(R.id.llAttendanceExist)
    View llAttendanceExist;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);

        try {
            getSupportActionBar().setTitle("Attendance List");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(AttendanceListActivity.this);

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mListAdapter);

        mPresenter.onViewPrepared();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.attendance_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        if (item.getItemId() == R.id.by_month) {
            mPresenter.showDatePicker(true);
        }

        if (item.getItemId() == R.id.by_year) {
            mPresenter.showDatePicker(false);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showExistDates() {
        llAttendanceExist.setVisibility(View.VISIBLE);
        llAttendanceNotExist.setVisibility(View.GONE);
    }

    @Override
    public void showNotExistDates() {
        llAttendanceExist.setVisibility(View.GONE);
        llAttendanceNotExist.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFilteredList(String selectedDate) {
        Log.e("TAGTAG", "showFilteredList: " + selectedDate );
        mPresenter.showFilteredList(selectedDate);
        mPresenter.dismissDialog();
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
    public void updateList(List<User> userList) {
        mListAdapter.removeAll();
        mListAdapter.addItems(userList);
    }
}
