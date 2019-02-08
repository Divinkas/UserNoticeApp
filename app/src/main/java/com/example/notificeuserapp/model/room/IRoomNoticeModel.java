package com.example.notificeuserapp.model.room;

import com.example.notificeuserapp.model.data.Notice;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.IntConsumer;

import io.reactivex.Single;

public interface IRoomNoticeModel {
    Single<List<Notice>> getUserNotice(String userId);
    void sendQuery(Callable<Integer> callable);

    void addUserNotice(Notice notice);
    void updateUserNotice(Notice notice);
    void deleteUserNotice(Notice notice);
}
