package com.example.notificeuserapp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.notificeuserapp.model.data.Notice;
import com.example.notificeuserapp.presenter.NoticePresenter;
import com.example.notificeuserapp.presenter.interfaces.ILoadNoticePresenter;
import com.example.notificeuserapp.R;
import com.example.notificeuserapp.view.adapter.NoticeAdapter;
import com.example.notificeuserapp.view.activity.BaseFragmentActivity;
import com.example.notificeuserapp.view.callback.IListNoticeCallback;
import com.example.notificeuserapp.view.interfaces.INoticeView;

import java.util.ArrayList;
import java.util.List;

public class ListNoticeFragment extends BaseFragment implements INoticeView, IListNoticeCallback {
    private ILoadNoticePresenter presenter;
    private String userId;

    private SwipeRefreshLayout refreshLayout;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private NoticeAdapter adapter;

    private BaseFragmentActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new NoticePresenter(context);
        ((NoticePresenter) presenter).setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_notice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
        setTitle(R.string.app_name);

        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.rvListNotices);
        refreshLayout = view.findViewById(R.id.refreshLayout);

        activity = (BaseFragmentActivity) getActivity();
        assert activity != null;
        adapter = new NoticeAdapter(new ArrayList<>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(() -> {
            refreshLayout.setRefreshing(false);
            presenter.loadMyNotices(userId);
        });

        userId = activity.getCurrentUser().getUid();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadMyNotices(userId);
    }

    @Override
    public void showErrorLoading() {
        Toast.makeText(context, R.string.error_loading, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadListNotice(List<Notice> noticeList) {
        List<Notice> list = new ArrayList<>();
        if(noticeList!=null){ list = noticeList; }
        adapter.setList(list);
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
    public void deleteNotice(Notice notice) {
        presenter.deleteNotice(notice);
    }

    @Override
    public void openFragment(BaseFragment fragment) {
        activity.showFragment(fragment);
    }
}
