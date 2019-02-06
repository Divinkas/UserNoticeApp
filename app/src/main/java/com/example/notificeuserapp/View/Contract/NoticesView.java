package com.example.notificeuserapp.View.Contract;

import com.example.notificeuserapp.Base.IView;
import com.example.notificeuserapp.Data.Notice;

import java.util.List;

public interface NoticesView extends IView {
    void showListNotice(List<Notice> noticeList);
}
