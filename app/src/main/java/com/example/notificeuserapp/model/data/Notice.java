package com.example.notificeuserapp.model.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.notificeuserapp.utils.Constants;

@Entity(tableName = Constants.TABLE_NOTICE)
public class Notice {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.ID_NOTICE)
    private int idNotice;

    @ColumnInfo(name = Constants.TEXT_NOTICE)
    private String textNotice;

    @ColumnInfo(name = Constants.USER_ID_NOTICE)
    private String userId;

    public Notice(int idNotice, String textNotice, String userId) {
        this.idNotice = idNotice;
        this.textNotice = textNotice;
        this.userId = userId;
    }

    public int getIdNotice() {
        return idNotice;
    }

    public void setIdNotice(int idNotice) {
        this.idNotice = idNotice;
    }

    public String getTextNotice() {
        return textNotice;
    }

    public void setTextNotice(String textNotice) {
        this.textNotice = textNotice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
