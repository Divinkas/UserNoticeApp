package com.example.notificeuserapp.model.room;

import com.example.notificeuserapp.model.data.Notice;
import com.example.notificeuserapp.model.room.base.RoomDB;

import java.util.List;
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
    public void sendQuery(IntConsumer method){
        Single.fromCallable(() -> method)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }


//    public RoomNoticeModel(Context context){
//        database = ((MyApplication)context.getApplicationContext()).getRoomInstance();
//    }


//    @Override
//    public void addUserNotice(Notice notice){
//        Single.fromCallable(() -> {
//            database.noticeDao().insertNotice(notice);
//            return 0;
//        })
//        .subscribeOn(Schedulers.io())
//        .subscribe();
//    }
//
//    @Override
//    public void updateUserNotice(Notice notice){
//        Single.fromCallable(() -> {
//            database.noticeDao().updateNotice(notice);
//            return 0;
//        })
//                .subscribeOn(Schedulers.io())
//                .subscribe();
//    }
//
//    @Override
//    public void deleteUserNotice(Notice notice){
//        Single.fromCallable(() -> {
//            database.noticeDao().deleteNotice(notice);
//            return 0;
//        })
//                .subscribeOn(Schedulers.io())
//                .subscribe();
//    }

}
