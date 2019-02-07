package com.example.notificeuserapp.view.callback;

import com.example.notificeuserapp.model.data.Notice;
import com.example.notificeuserapp.view.fragment.BaseFragment;

public interface IListNoticeCallback {
    void deleteNotice(Notice notice);
    void openFragment(BaseFragment fragment);
}
