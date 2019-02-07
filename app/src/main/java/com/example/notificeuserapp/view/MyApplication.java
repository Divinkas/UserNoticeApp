package com.example.notificeuserapp.view;

import android.arch.persistence.room.Room;
import android.support.multidex.MultiDexApplication;

import com.example.notificeuserapp.model.room.base.RoomDB;
import com.example.notificeuserapp.utils.Constants;

public class MyApplication extends MultiDexApplication {
    private static RoomDB room;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public RoomDB getRoomInstance(){
        if(room == null){
            room = Room.databaseBuilder(getApplicationContext(), RoomDB.class, Constants.DATABASE_NAME).build();
        }
        return room;
    }
}
