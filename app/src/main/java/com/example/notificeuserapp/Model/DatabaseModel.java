package com.example.notificeuserapp.Model;

import android.content.Context;

import com.example.notificeuserapp.Data.Notice;
import com.example.notificeuserapp.Model.Room.RoomDB;
import com.example.notificeuserapp.Model.Room.RoomInstance;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class DatabaseModel {
    private Context context;
    private RoomDB database;

    public DatabaseModel(Context context) {
        this.context = context;
        database = RoomInstance.getInstance(this.context);
    }

    public Single<List<Notice>> getUserNotice(String userId){
        return database.noticeDao().getAllNoticesUser(userId);
    }

    public void addUserNotice(Notice notice){
        Single.fromCallable(() -> {
            database.noticeDao().insertNotice(notice);
            return 0;
        })
        .subscribeOn(Schedulers.io())
        .subscribe();
    }

    public void updateUserNotice(Notice notice){
        Single.fromCallable(() -> {
            database.noticeDao().updateNotice(notice);
            return 0;
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void deleteUserNotice(Notice notice){
        Single.fromCallable(() -> {
            database.noticeDao().deleteNotice(notice);
            return 0;
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
