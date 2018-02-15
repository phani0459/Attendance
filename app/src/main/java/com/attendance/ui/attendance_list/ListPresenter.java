package com.attendance.ui.attendance_list;

import android.content.Context;

import com.attendance.database.DatabaseCallBack;
import com.attendance.database.LocalCacheManager;
import com.attendance.database.User;
import com.attendance.di.ActivityContext;
import com.attendance.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

public class ListPresenter<V extends ListMvpView> extends BasePresenter<V> implements ListMvpPresenter<V>, DatabaseCallBack {

    private final Context context;

    @Inject
    public ListPresenter(@ActivityContext Context context) {
        this.context = context;
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        LocalCacheManager.getInstance(context).getUsers(this);
    }

    @Override
    public void onUsersLoaded(List<User> users) {
        if (users != null && users.size() > 0) {
            getMvpView().showExistDates();
            getMvpView().updateList(users);
        } else {
            getMvpView().showNotExistDates();
        }
        getMvpView().hideLoading();
    }

    @Override
    public void onUserAdded() {

    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public void singleUser(User user) {

    }
}
