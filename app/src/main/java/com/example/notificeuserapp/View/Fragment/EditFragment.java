package com.example.notificeuserapp.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notificeuserapp.Presenter.NoticePresenter;
import com.example.notificeuserapp.Utils.Constants;

public class EditFragment extends BaseFragment {
    private NoticePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert savedInstanceState != null;
        int idNotice = savedInstanceState.getInt(Constants.ID_NOTICE);
        String userId = savedInstanceState.getString(Constants.USER_ID_NOTICE);
        String textNotice = savedInstanceState.getString(Constants.TEXT_NOTICE);

        presenter = new NoticePresenter(context);
        //presenter.updateNotice();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }
}
