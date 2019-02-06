package com.example.notificeuserapp.Model.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.notificeuserapp.Data.Notice;
import com.example.notificeuserapp.Utils.Constants;

@Database(entities = {Notice.class}, version = Constants.DATABASE_VERSION, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    public abstract NoticeDao noticeDao();
}
