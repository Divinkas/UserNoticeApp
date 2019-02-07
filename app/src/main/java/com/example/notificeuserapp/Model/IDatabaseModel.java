package com.example.notificeuserapp.Model;

import com.example.notificeuserapp.Data.Notice;

import java.util.List;

import io.reactivex.Single;

public interface IDatabaseModel {
    Single<List<Notice>> getUserNotice(String userId);

    void addUserNotice(Notice notice);
    void updateUserNotice(Notice notice);
    void deleteUserNotice(Notice notice);

}
