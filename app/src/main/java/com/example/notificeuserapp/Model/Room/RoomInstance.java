package com.example.notificeuserapp.Model.Room;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.notificeuserapp.Utils.Constants;

public class RoomInstance {
    private static RoomDB room;

    private RoomInstance() { }

    public static RoomDB getInstance(Context context){
        if(room == null){
            room = Room.databaseBuilder(context, RoomDB.class, Constants.DATABASE_NAME).build();
        }
        return room;
    }
}
