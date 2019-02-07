package com.example.notificeuserapp.view.interfaces;

import com.example.notificeuserapp.model.data.Notice;

import java.util.List;

public interface INoticeView extends IView {
    void showErrorLoading();
    void loadListNotice(List<Notice> noticeList);
}
