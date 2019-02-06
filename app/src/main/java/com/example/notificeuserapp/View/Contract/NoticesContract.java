package com.example.notificeuserapp.View.Contract;

import com.example.notificeuserapp.Data.Notice;

import java.util.List;

public interface NoticesContract {
    void showErrorLoading();
    void loadListNotice(List<Notice> noticeList);
    void showLoading();
    void hideLoading();
}
