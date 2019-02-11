package com.example.notificeuserapp.view.callback;

import com.example.notificeuserapp.model.data.Notice;

public interface IListNoticeCallback {
    void deleteNotice(Notice notice);
    void openDetail(Notice notice);
}
