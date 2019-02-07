package com.example.notificeuserapp.model.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.notificeuserapp.model.data.Notice;
import com.example.notificeuserapp.utils.Constants;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface NoticeDao {
    @Query("select * from " + Constants.TABLE_NOTICE + " where " + Constants.USER_ID_NOTICE + " = :userId")
    Single<List<Notice>> getAllNoticesUser(String userId);

    @Insert
    void insertNotice(Notice newNotice);

    @Update
    void updateNotice(Notice newNotice);

    @Delete
    void deleteNotice(Notice notice);

}
