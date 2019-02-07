package com.example.notificeuserapp.model.room.base;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.notificeuserapp.model.data.Notice;
import com.example.notificeuserapp.model.room.dao.NoticeDao;
import com.example.notificeuserapp.utils.Constants;

@Database(entities = {Notice.class}, version = Constants.DATABASE_VERSION, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    public abstract NoticeDao noticeDao();
}
