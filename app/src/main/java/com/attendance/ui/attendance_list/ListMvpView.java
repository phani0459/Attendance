package com.attendance.ui.attendance_list;

import com.attendance.database.User;
import com.attendance.ui.base.BaseView;

import java.util.List;

public interface ListMvpView extends BaseView {

    void updateList(List<User> userList);

    void showLoading();

    void hideLoading();

    void showExistDates();

    void showNotExistDates();

    void showFilteredList(String selectedDate);
}
