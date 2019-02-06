package com.example.notificeuserapp.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notificeuserapp.Base.BaseFragment;
import com.example.notificeuserapp.Presenter.EditNoticePresenter;
import com.example.notificeuserapp.Presenter.NoticeListPresenter;
import com.example.notificeuserapp.Utils.Constants;
import com.example.notificeuserapp.View.Notices.EditNoticeView;

public class EditNoticeFragment extends BaseFragment implements EditNoticeView {

    private EditNoticePresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        presenter = new EditNoticePresenter(getContext());
        presenter.onAttach(this);
    }

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
    }

    @Override
    protected void onDestroyFragment() {
        presenter.onDetach();
    }
}
