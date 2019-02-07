package com.example.notificeuserapp.presenter.interfaces;

import com.example.notificeuserapp.model.data.Notice;

public interface ILoadNoticePresenter {
    void loadMyNotices(String userId);
    void deleteNotice(Notice notice);
}
