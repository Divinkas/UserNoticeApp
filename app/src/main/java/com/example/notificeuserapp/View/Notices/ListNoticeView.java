package com.example.notificeuserapp.View.Notices;

import com.example.notificeuserapp.Base.IView;
import com.example.notificeuserapp.Data.Notice;

import java.util.List;

public interface ListNoticeView extends IView {
    void showListNotice(List<Notice> notices);
}
