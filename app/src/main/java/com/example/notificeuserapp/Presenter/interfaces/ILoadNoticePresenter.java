package com.example.notificeuserapp.Presenter.interfaces;

import com.example.notificeuserapp.Data.Notice;

public interface ILoadNoticePresenter {
    void loadMyNotices(String userId);
    void deleteNotice(Notice notice);
}
