package com.attendance.ui.attendance_list;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.attendance.database.DatabaseCallBack;
import com.attendance.database.LocalCacheManager;
import com.attendance.database.User;
import com.attendance.di.ActivityContext;
import com.attendance.ui.base.BasePresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class ListPresenter<V extends ListMvpView> extends BasePresenter<V> implements ListMvpPresenter<V>, DatabaseCallBack, OnDateSetListener {

    private final Context context;
    private DatePickerDialog datePickerDialog;
    private boolean isMonth;

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
    public void showFilteredList(String filterdString) {
        getMvpView().showLoading();
        LocalCacheManager.getInstance(context).getUsers(this, filterdString);
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
    public void dismissDialog() {
        if (datePickerDialog != null) {
            datePickerDialog.dismiss();
        }
    }

    @Override
    public void showDatePicker(boolean isMonth) {
        this.isMonth = isMonth;
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(context, android.R.style.Theme_Holo_Dialog, this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        try {
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            java.lang.reflect.Field[] datePickerDialogFields = datePickerDialog.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(datePickerDialog);

                    int daySpinnerId = Resources.getSystem().getIdentifier("day", "id", "android");
                    if (daySpinnerId != 0) {
                        View daySpinner = datePicker.findViewById(daySpinnerId);
                        if (daySpinner != null) {
                            daySpinner.setVisibility(View.GONE);
                        }
                    }

                    if (!isMonth) {
                        int monthSpinnerId = Resources.getSystem().getIdentifier("month", "id", "android");
                        if (monthSpinnerId != 0) {
                            View monthSpinner = datePicker.findViewById(monthSpinnerId);
                            if (monthSpinner != null) {
                                monthSpinner.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            }

            datePickerDialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        SimpleDateFormat dateFormatter;
        if (isMonth) {
            dateFormatter = new SimpleDateFormat("MM-yyyy");
        } else {
            dateFormatter = new SimpleDateFormat("yyyy");
        }
        Calendar newDate = Calendar.getInstance();
        newDate.set(year, monthOfYear, dayOfMonth);
        getMvpView().showFilteredList(dateFormatter.format(newDate.getTime()));
    }
}
