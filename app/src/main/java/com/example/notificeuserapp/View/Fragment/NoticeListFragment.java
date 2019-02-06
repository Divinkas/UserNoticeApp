package com.example.notificeuserapp.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.notificeuserapp.Data.Notice;
import com.example.notificeuserapp.Presenter.NoticePresenter;
import com.example.notificeuserapp.R;
import com.example.notificeuserapp.Utils.Constants;
import com.example.notificeuserapp.View.Contract.NoticesContract;

import java.util.List;

public class NoticeListFragment  extends BaseFragment implements NoticesContract {
    private NoticePresenter presenter;
    private String userId;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
        setTitle(R.string.app_name);

        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.rvListNotices);

        //check userId
        assert savedInstanceState != null;
        userId = savedInstanceState.getString(Constants.USER_ID_NOTICE);

        presenter = new NoticePresenter(context, this);
        presenter.loadMyNotices(userId);
    }

    @Override
    public void showErrorLoading() {
        Toast.makeText(context, "Error loading!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadListNotice(List<Notice> noticeList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //recyclerView.setAdapter();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }
}
