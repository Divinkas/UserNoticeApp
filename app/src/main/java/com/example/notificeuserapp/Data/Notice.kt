package com.example.notificeuserapp.Data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.example.notificeuserapp.Utils.Constants

@Entity(tableName = Constants.TABLE_NOTICE)
data class Notice constructor(

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.ID_NOTICE)
    var idNotice:Int = 0,

    @ColumnInfo(name = Constants.TEXT_NOTICE)
    var textNotice:String?=null,

    @ColumnInfo(name = Constants.USER_ID_NOTICE)
    var userId:String?=null
)
