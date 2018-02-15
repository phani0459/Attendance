package com.attendance.database;

import java.util.List;

public interface DatabaseCallBack {
    void onUsersLoaded(List<User> users);

    void onUserAdded();

    void onDataNotAvailable();

    void singleUser(User user);

}