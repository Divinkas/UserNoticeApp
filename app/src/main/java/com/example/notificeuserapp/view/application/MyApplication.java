package com.example.notificeuserapp.view.application;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.example.notificeuserapp.model.room.base.RoomDB;
import com.example.notificeuserapp.utils.Constants;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class MyApplication extends MultiDexApplication {
    private static RoomDB room;
    private static FirebaseAuth firebaseAuth;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());
    }

    public static RoomDB getRoomInstance(Context context){
        if(room == null){
            room = Room.databaseBuilder(context, RoomDB.class, Constants.DATABASE_NAME).build();
        }
        return room;
    }

    public static void initFirebase(){
        if(!isFirebaseAuth())
            firebaseAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseAuth getFirebaseAuth(){
        if(isFirebaseAuth())
            return firebaseAuth;
        else
            return null;
    }
    private static boolean isFirebaseAuth(){
        return firebaseAuth != null;
    }
}
