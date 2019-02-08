package com.example.notificeuserapp.model.room;

import com.example.notificeuserapp.model.data.Notice;
import com.example.notificeuserapp.model.room.base.RoomDB;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.IntConsumer;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class RoomNoticeModel implements IRoomNoticeModel {
    private RoomDB database;

    public RoomNoticeModel(RoomDB roomDB) {
        database = roomDB;
    }

    @Override
    public Single<List<Notice>> getUserNotice(String userId){
        return database.noticeDao().getAllNoticesUser(userId);
    }

    @Override
    public void sendQuery(Callable<Integer> callable){
        Single.fromCallable(callable)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }


    @Override
    public void addUserNotice(Notice notice){
        sendQuery(() -> {
            database.noticeDao().insertNotice(notice);
            return 0;
        });
    }

    @Override
    public void updateUserNotice(Notice notice){
        sendQuery( () -> {
            database.noticeDao().updateNotice(notice);
            return 0;
        });
    }

    @Override
    public void deleteUserNotice(Notice notice){
        sendQuery(() -> {
            database.noticeDao().deleteNotice(notice);
            return 0;
        });
    }

}
